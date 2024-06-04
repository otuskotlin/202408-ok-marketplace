# 202408-ok-marketplace

Учебный проект курса
[Kotlin Backend Developer](https://otus.ru/lessons/kotlin/).
Поток курса 2024-08.

Marketplace -- это площадка, на которой пользователи выставляют предложения и потребности. Задача
площадки -- предоставить наиболее подходящие варианты в обоих случаях: для предложения -- набор вариантов с
потребностями, для потребностей -- набор вариантов с предложениями.

## Визуальная схема фронтенда

![Макет фронта](imgs/design-layout.png)

## Документация

1. Маркетинг и аналитика
    1. [Целевая аудитория](./docs/01-biz/01-target-audience.md)
    2. [Заинтересанты](./docs/01-biz/02-stakeholders.md)
    3. [Пользовательские истории](./docs/01-biz/03-bizreq.md)
2. Аналитика:
    1. [Функциональные требования](./docs/02-analysis/01-functional-requiremens.md)
    2. [Нефункциональные требования](./docs/02-analysis/02-nonfunctional-requirements.md)
3. DevOps
    1. [Файлы сборки](./deploy)
4. Архитектура
    1. [ADR](docs/03-architecture/01-adrs.md)
    2. [Описание API](docs/03-architecture/02-api.md)
    3. [Компонентная схема](docs/03-architecture/03-arch.md)
5. Тесты

# Структура проекта

## Подпроекты для занятий по языку Kotlin

1. Модуль 1: Введение в Kotlin
    1. [m1l1-first](m1l1-first) - Вводное занятие, создание первой программы на Kotlin
    2. [m1l2-basic](m1l2-basic) - Основные конструкции Kotlin
    3. [m1l3-func](m1l3-func) - Функциональные элементы Kotlin
    4. [m1l4-oop](m1l4-oop) - Объектно-ориентированное программирование
2. Модуль 2: Расширенные возможности Kotlin
    1. [m2l1-dsl](m2l1-dsl) - Предметно ориентированные языки (DSL)
   2. [m2l2-coroutines](m2l2-coroutines) - Асинхронное и многопоточное программирование с корутинами
    3. [m2l3-flows](m2l3-flows) - Асинхронное и многопоточное программирование с Sequence и Flow
   4. [m2l4-kmp](m2l4-kmp) - Мультиплатформенная разработка
    5. m2l5 - Интероперабельность Kotlin с другими языками
       1. [m2l5-1-interop](m2l5-1-interop) - Интероперабельность Kotlin Native с C
       2. [m2l5-2-jni](m2l5-2-jni) - Интероперабельность Kotlin JVM с C

## Плагины

### Плагины Gradle сборки проекта

1. [build-plugin](build-plugin) Модуль с плагинами
2. [BuildPluginJvm](build-plugin/src/main/kotlin/BuildPluginJvm.kt) Плагин для сборки проектов JVM
2. [BuildPluginMultiplarform](build-plugin/src/main/kotlin/BuildPluginMultiplatform.kt) Плагин для сборки
   мультиплатформенных проектов

## Проектные модули

## Мониторинг и логирование

1. [deploy](deploy) - Инструменты мониторинга и деплоя
2. [ok-marketplace-lib-logging-common](ok-marketplace-libs/ok-marketplace-lib-logging-common) - Общие объявления для
   логирования
3. [ok-marketplace-lib-logging-kermit](ok-marketplace-libs/ok-marketplace-lib-logging-kermit) - Библиотека логирования
   на базе библиотеки
   Kermit
4. [ok-marketplace-lib-logging-logback](ok-marketplace-libs/ok-marketplace-lib-logging-logback) - Библиотека логирования
   на базе библиотеки Logback
5. [ok-marketplace-lib-logging-socket](ok-marketplace-libs/ok-marketplace-lib-logging-socket) - Библиотека логирования
   на базе TCP-сокетов

### Транспортные модели, API

1. [specs](specs) - описание API в форме OpenAPI-спецификаций
2. [ok-marketplace-api-v1-jackson](ok-marketplace-be/ok-marketplace-api-v1-jackson) - Генерация первой версии
3. [ok-marketplace-api-v1-mappers](ok-marketplace-be/ok-marketplace-api-v1-mappers) - Маперы из API v1 во внутренние
   модели
4. [ok-marketplace-api-v2-kmp](ok-marketplace-be/ok-marketplace-api-v2-kmp) - Генерация второй версии транспортных
   моделей с KMP
5. [ok-marketplace-common](ok-marketplace-be/ok-marketplace-common) - модуль с общими классами для модулей проекта. В
   частности, там располагаются внутренние модели и контекст.
6. [ok-marketplace-mappers-log1](ok-marketplace-be/ok-marketplace-api-log1) - Мапер между внутренними моделями и
   моделями логирования первой версии

### Фреймворки и транспорты

1. [ok-marketplace-app-spring](ok-marketplace-be/ok-marketplace-app-spring) - Приложение на Spring Framework
2. [ok-marketplace-app-ktor](ok-marketplace-be/ok-marketplace-app-ktor) - Приложение на Ktor
3. [ok-marketplace-app-rabbit](ok-marketplace-be/ok-marketplace-app-rabbit) - Микросервис на RabbitMQ
4. [ok-marketplace-app-kafka](ok-marketplace-be/ok-marketplace-app-kafka) - Микросервис на Kafka

### Модули бизнес-логики

1. [ok-marketplace-stubs](ok-marketplace-be/ok-marketplace-stubs) - Стабы для ответов сервиса
2. [ok-marketplace-biz](ok-marketplace-be/ok-marketplace-biz) - Модуль бизнес-логики приложения: обслуживание стабов,
   валидация, работа с БД

## Библиотеки

### Мониторинг и логирование

1. [deploy](deploy) - Инструменты мониторинга и деплоя
2. [ok-marketplace-lib-logging-common](ok-marketplace-libs/ok-marketplace-lib-logging-common) - Общие объявления для
   логирования
3. [ok-marketplace-lib-logging-kermit](ok-marketplace-libs/ok-marketplace-lib-logging-kermit) - Библиотека логирования
   на базе библиотеки
   Kermit
4. [ok-marketplace-lib-logging-logback](ok-marketplace-libs/ok-marketplace-lib-logging-logback) - Библиотека логирования
   на базе библиотеки Logback
5. [ok-marketplace-lib-logging-socket](ok-marketplace-libs/ok-marketplace-lib-logging-socket) - Библиотека логирования
   на базе библиотеки Ktor и протокола TCP socket

## Тестирование

### Сквозные/интеграционные тесты

1. [ok-marketplace-e2e-be](ok-marketplace-tests/ok-marketplace-e2e-be) - Сквозные/интеграционные тесты для бэкенда
   системы
