/*
 * Copyright 2020 Zakayo Thuku.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import dependencies.AnnotationProcessorsDependencies
import dependencies.DebugDependencies
import dependencies.Dependencies
import extensions.addTestsDependencies
import extensions.buildConfigBooleanField
import extensions.debugImplementation
import extensions.getLocalProperty
import extensions.implementation
import extensions.kapt

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.KOTLIN_ALLOPEN)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
    id(BuildPlugins.JACOCO)
    id(BuildPlugins.FIREBASE_CRASHLYTICS)
    id(BuildPlugins.FIREBASE_PERFORMANCE_MONITOR)
    id(BuildPlugins.GOOGLE_PLAY_SERVICES)
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.schatzdesigns.core.annotations.OpenClass")
}

android {

    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS
    }

    signingConfigs {
        create(BuildType.RELEASE) {
            keyAlias = getLocalProperty("signing.key.alias")
            keyPassword = getLocalProperty("signing.key.password")
            storeFile = file(getLocalProperty("signing.store.file"))
            storePassword = getLocalProperty("signing.store.password")
        }
    }

    buildTypes {

        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            signingConfig = signingConfigs.getByName(name)

            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
            buildConfigBooleanField("ENABLE_CRASHLYTICS", BuildTypeRelease.isCrashlyticsEnabled)
        }

        getByName(BuildType.DEBUG) {
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
            buildConfigBooleanField("ENABLE_CRASHLYTICS", BuildTypeDebug.isCrashlyticsEnabled)
        }
    }

    flavorDimensions(BuildProductDimensions.ENVIRONMENT)
    productFlavors {
        ProductFlavorDevelop.appCreate(this)
        ProductFlavorQA.appCreate(this)
        ProductFlavorProduction.appCreate(this)
    }

    dynamicFeatures = mutableSetOf(
        BuildModules.Features.NAVIGATION,
        BuildModules.Features.DASHBOARD,
        BuildModules.Features.SETTINGS
    )

    buildFeatures {
        dataBinding = true
        viewBinding = true
        renderScript = true
    }

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }
}

junitJacoco {
    includeNoLocationClasses = true
}

afterEvaluate {
}

dependencies {

    implementation(project(BuildModules.CORE))

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_DYNAMIC_FEATURES)

    implementation(Dependencies.OKHTTP_LOGGING)

    implementation(platform(Dependencies.FIREBASE_BOM))
    implementation(Dependencies.FIREBASE_AUTH)
    implementation(Dependencies.FIREBASE_FIRESTORE)
    implementation(Dependencies.FIREBASE_CRASHLYTICS)
    implementation(Dependencies.FIREBASE_PERFORMANCE_MONITOR)

    implementation(Dependencies.PLAY_CORE)
    implementation(Dependencies.PLAY_CORE_KTX)
    implementation(Dependencies.PLAY_AUTH)

    implementation(Dependencies.DAGGER)
    implementation(Dependencies.DAGGER_ANDROID)
    implementation(Dependencies.DAGGER_ANDROID_SUPPORT)
    kapt(AnnotationProcessorsDependencies.DAGGER_COMPILER)
    kapt(AnnotationProcessorsDependencies.DAGGER_ANDROID_PROCESSOR)

    implementation(Dependencies.TIMBER)
    debugImplementation(DebugDependencies.LEAK_CANARY)

    addTestsDependencies()
}
