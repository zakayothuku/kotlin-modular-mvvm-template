package dependencies

/**
 * Project test android dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object TestAndroidDependencies {
    const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android-instrumentation:${BuildDependenciesVersions.LEAK_CANARY}"
    const val MOCKITO = "com.nhaarman.mockitokotlin2:mockito-kotlin:${BuildDependenciesVersions.MOCKITO}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.ESPRESSO}"
    const val RUNNER = "androidx.test:runner:${BuildDependenciesVersions.TEST}"
    const val RULES = "androidx.test:rules:${BuildDependenciesVersions.TEST}"
    const val JUNIT = "androidx.test.ext:junit:${BuildDependenciesVersions.EXT}"
    const val PLAY_CORE = "com.google.android.play:core:${BuildDependenciesVersions.PLAY_CORE}"
}
