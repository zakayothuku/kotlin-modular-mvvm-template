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

package com.schatzdesigns.features.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.schatzdesigns.commons.ui.base.BaseViewModel

val NAV_FRAGMENTS_ID = setOf(
    R.id.dashboard,
    R.id.settings
)

/**
 * View model responsible for preparing and managing the data for [NavigationFragment].
 *
 * @see ViewModel
 */
class NavigationViewModel : BaseViewModel() {

    private val _state = MutableLiveData<NavigationViewState>()
    val state: LiveData<NavigationViewState>
        get() = _state

    /**
     * Navigation controller add destination changed listener.
     *
     * @param navController Navigation manager.
     */
    fun navigationControllerChanged(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (NAV_FRAGMENTS_ID.contains(destination.id)) {
                _state.postValue(NavigationViewState.NavigationScreen)
            } else {
                _state.postValue(NavigationViewState.FullScreen)
            }
        }
    }
}
