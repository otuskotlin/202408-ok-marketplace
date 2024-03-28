package ru.otus.otuskotlin.markeplace.app.spring.controllers

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import ru.otus.otuskotlin.markeplace.app.spring.config.MkplAppSettings
import ru.otus.otuskotlin.markeplace.app.spring.models.SpringWsSessionV1
import ru.otus.otuskotlin.marketplace.api.v1.apiV1Mapper
import ru.otus.otuskotlin.marketplace.api.v1.models.IRequest
import ru.otus.otuskotlin.marketplace.app.common.controllerHelper
import ru.otus.otuskotlin.marketplace.common.MkplContext
import ru.otus.otuskotlin.marketplace.common.models.MkplCommand
import ru.otus.otuskotlin.marketplace.mappers.v1.fromTransport
import ru.otus.otuskotlin.marketplace.mappers.v1.toTransportAd

@Component
class AdControllerV1Ws(private val appSettings: MkplAppSettings) : WebSocketHandler {
    private val sessions = appSettings.corSettings.wsSessions

    override fun handle(session: WebSocketSession): Mono<Void> = runBlocking {
        val mkplSess = SpringWsSessionV1(session)
        sessions.add(mkplSess)
        val messageObj = process("ws-v1-init") {
            command = MkplCommand.INIT
        }

        val messages = session.receive().asFlow()
            .map { message ->
                process("ws-v1-handle") {
                    val request = apiV1Mapper.readValue(message.payloadAsText, IRequest::class.java)
                    fromTransport(request)
                }
            }

        val output = merge(flowOf(messageObj), messages)
            .onCompletion {
                process("ws-v1-finish") {
                    command = MkplCommand.FINISH
                }
            }
            .map { session.textMessage(apiV1Mapper.writeValueAsString(it)) }
            .asFlux()
        session.send(output)
    }

    private suspend fun process(logId: String, function: MkplContext.() -> Unit) = appSettings.controllerHelper(
        getRequest = function,
        toResponse = MkplContext::toTransportAd,
        clazz = this@AdControllerV1Ws::class,
        logId = logId,
    )
}
