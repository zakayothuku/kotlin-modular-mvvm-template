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

package com.schatzdesigns.features.navigation.di

import com.schatzdesigns.core.di.CoreComponent
import com.schatzdesigns.core.di.scopes.FeatureScope
import com.schatzdesigns.features.navigation.NavigationFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [NavigationModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [NavigationModule::class]
)
interface NavigationComponent {

    /**
     * Inject dependencies on component.
     *
     * @param fragment NavigationFragment.
     */
    fun inject(fragment: NavigationFragment)
}
