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

package com.schatzdesigns.features.settings

import android.view.Menu
import android.view.MenuInflater
import com.schatzdesigns.commons.ui.base.BaseFragment
import com.schatzdesigns.features.settings.databinding.FragmentSettingsBinding
import com.schatzdesigns.features.settings.di.DaggerSettingsComponent
import com.schatzdesigns.features.settings.di.SettingsModule
import com.schatzdesigns.template.TemplateApp

/**
 * Cards principal view.
 *
 * @see BaseFragment
 */
class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>(
    layoutId = R.layout.fragment_settings
) {

    override fun onInitDependencyInjection() {
        DaggerSettingsComponent.builder()
            .coreComponent(TemplateApp.coreComponent(requireContext()))
            .settingsModule(SettingsModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

}
