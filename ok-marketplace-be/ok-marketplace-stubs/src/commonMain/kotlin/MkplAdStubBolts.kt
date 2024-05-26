package ru.otus.otuskotlin.marketplace.stubs

import ru.otus.otuskotlin.marketplace.common.models.*

object MkplAdStubBolts {
    val AD_DEMAND_BOLT1: MkplAd
        get() = MkplAd(
            id = MkplAdId("666"),
            title = "Требуется болт",
            description = "Требуется болт 100x5 с шестигранной шляпкой",
            ownerId = MkplUserId("user-1"),
            adType = MkplDealSide.DEMAND,
            visibility = MkplVisibility.VISIBLE_PUBLIC,
            lock = MkplAdLock("123-234-abc-ABC"),
            permissionsClient = mutableSetOf(
                MkplAdPermissionClient.READ,
                MkplAdPermissionClient.UPDATE,
                MkplAdPermissionClient.DELETE,
            )
        )
    val AD_SUPPLY_BOLT1 = AD_DEMAND_BOLT1.copy(adType = MkplDealSide.SUPPLY)
}
