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

package com.schatzdesigns.features.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.schatzdesigns.commons.ui.base.BaseFragment
import com.schatzdesigns.features.dashboard.databinding.FragmentDashboardBinding
import com.schatzdesigns.features.dashboard.di.DaggerDashboardComponent
import com.schatzdesigns.features.dashboard.di.DashboardModule
import com.schatzdesigns.template.TemplateApp

/**
 * Cards principal view.
 *
 * @see BaseFragment
 */
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(
    layoutId = R.layout.fragment_dashboard
) {

    override fun onInitDependencyInjection() {
        DaggerDashboardComponent.builder()
            .coreComponent(TemplateApp.coreComponent(requireContext()))
            .dashboardModule(DashboardModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

}
