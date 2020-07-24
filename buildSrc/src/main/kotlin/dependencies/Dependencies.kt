package dependencies

/**
 * Project dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildDependenciesVersions.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${BuildDependenciesVersions.MATERIAL}"
    const val RECYCLE_VIEW = "androidx.recyclerview:recyclerview:${BuildDependenciesVersions.RECYCLE_VIEW}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_DYNAMIC_FEATURES = "androidx.navigation:navigation-dynamic-features-fragment:${BuildDependenciesVersions.NAVIGATION}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${BuildDependenciesVersions.FRAGMENT_KTX}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.CONSTRAINT_LAYOUT}"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${BuildDependenciesVersions.SWIPE_REFRESH_LAYOUT}"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"
    const val COIL = "io.coil-kt:coil:${BuildDependenciesVersions.COIL}"

    const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${BuildDependenciesVersions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.LIFECYCLE}"
    const val CORE_KTX = "androidx.core:core-ktx:${BuildDependenciesVersions.CORE_KTX}"
    const val ROOM = "androidx.room:room-runtime:${BuildDependenciesVersions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${BuildDependenciesVersions.ROOM}"
    const val PAGING = "androidx.paging:paging-runtime-ktx:${BuildDependenciesVersions.PAGING}"

    const val DAGGER = "com.google.dagger:dagger:${BuildDependenciesVersions.DAGGER}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${BuildDependenciesVersions.DAGGER}"
    const val DAGGER_ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:${BuildDependenciesVersions.DAGGER}"

    const val TIMBER = "com.jakewharton.timber:timber:${BuildDependenciesVersions.TIMBER}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.RETROFIT}"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.OKHTTP_LOGGING}"

    const val PLAY_CORE = "com.google.android.play:core:${BuildDependenciesVersions.PLAY_CORE}"
    const val PLAY_CORE_KTX = "com.google.android.play:core-ktx:${BuildDependenciesVersions.PLAY_CORE_KTX}"
    const val PLAY_AUTH = "com.google.android.gms:play-services-auth:${BuildDependenciesVersions.PLAY_AUTH}"

    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${BuildDependenciesVersions.FIREBASE_BOM}"
    const val FIREBASE_AUTH = "com.google.firebase:firebase-auth"
    const val FIREBASE_FIRESTORE = "com.google.firebase:firebase-firestore"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics"
    const val FIREBASE_PERFORMANCE_MONITOR = "com.google.firebase:firebase-perf"
}
