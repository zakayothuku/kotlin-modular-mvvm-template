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

import androidx.lifecycle.ViewModel
import com.schatzdesigns.commons.ui.extensions.viewModel
import com.schatzdesigns.features.navigation.NavigationFragment
import com.schatzdesigns.features.navigation.NavigationViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.verify
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class NavigationModuleTest {

    @MockK
    lateinit var fragment: NavigationFragment
    lateinit var module: NavigationModule

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeNavigationModule_ShouldSetUpCorrectly() {
        module = NavigationModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedHomeViewModel() {
        mockkStatic("com.schatzdesigns.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<NavigationViewModel>()

        val factoryCaptor = slot<() -> ViewModel>()
        module = NavigationModule(fragment)
        module.providesNavigationViewModel()

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        assertThat(factoryCaptor.captured(), instanceOf(NavigationViewModel::class.java))
    }
}
