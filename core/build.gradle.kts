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

import dependencies.Dependencies

plugins {
    id("commons.android-library")
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.schatzdesigns.core.annotations.OpenClass")
}

android {

}

dependencies {
    implementation(platform(Dependencies.FIREBASE_BOM))
    implementation(Dependencies.FIREBASE_FIRESTORE)
    implementation(Dependencies.FIREBASE_ANALYTICS)
}
