package ru.otus.otuskotlin.marketplace.common.models

import ru.otus.otuskotlin.marketplace.states.common.statemachine.SMAdStates

data class MkplAd(
    var id: MkplAdId = MkplAdId.NONE,
    var title: String = "",
    var description: String = "",
    var ownerId: MkplUserId = MkplUserId.NONE,
    var adType: MkplDealSide = MkplDealSide.NONE,
    var visibility: MkplVisibility = MkplVisibility.NONE,
    var productId: MkplProductId = MkplProductId.NONE,
    var lock: MkplAdLock = MkplAdLock.NONE,
    val permissionsClient: MutableSet<MkplAdPermissionClient> = mutableSetOf(),

    var adState: SMAdStates = SMAdStates.NONE,
) {
    fun deepCopy(): MkplAd = copy(
        permissionsClient = permissionsClient.toMutableSet(),
    )

    fun isEmpty() = this == NONE

    companion object {
        private val NONE = MkplAd()
    }
}
