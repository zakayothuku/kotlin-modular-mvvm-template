/**
 * Configuration of build modules
 */
object BuildModules {
    const val APP = ":app"
    const val CORE = ":core"

    object Features {
        const val NAVIGATION = ":features:navigation"
        const val DASHBOARD = ":features:dashboard"
        const val SETTINGS = ":features:settings"
    }

    object Commons {
        const val UI = ":commons:ui"
        const val VIEWS = ":commons:views"
    }

    object Libraries {
        const val TEST_UTILS = ":libraries:testutils"
    }
}
