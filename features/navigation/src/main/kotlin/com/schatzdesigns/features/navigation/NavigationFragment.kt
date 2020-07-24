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

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.ui.NavigationUI
import com.schatzdesigns.commons.ui.base.BaseFragment
import com.schatzdesigns.commons.ui.extensions.setupWithNavController
import com.schatzdesigns.features.navigation.databinding.FragmentNavigationBinding
import com.schatzdesigns.features.navigation.di.DaggerNavigationComponent
import com.schatzdesigns.features.navigation.di.NavigationModule
import com.schatzdesigns.template.TemplateApp

/**
 * Navigation principal view containing
 * bottom navigation bar with different tabs.
 *
 * @see BaseFragment
 */
class NavigationFragment : BaseFragment<FragmentNavigationBinding, NavigationViewModel>(
    layoutId = R.layout.fragment_navigation
) {

    private val navGraphIds = listOf(
        R.navigation.navigation_dashboard_graph,
        R.navigation.navigation_settings_graph
    )

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    /**
     * Called when all saved state has been restored into the view hierarchy of the fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     * this is the state.
     * @see BaseFragment.onViewStateRestored
     */
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupBottomNavigationBar()
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerNavigationComponent.builder()
            .coreComponent(TemplateApp.coreComponent(requireContext()))
            .navigationModule(NavigationModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    /**
     * Configure app custom support action bar.
     */
    private fun setupToolbar() {
        setHasOptionsMenu(true)
        requireCompatActivity().setSupportActionBar(viewBinding.toolbar)
    }

    /**
     * Configure app bottom bar via navigation graph.
     */
    private fun setupBottomNavigationBar() {

        val navController = viewBinding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )

        navController.observe(viewLifecycleOwner, Observer {
            viewModel.navigationControllerChanged(it)
            NavigationUI.setupActionBarWithNavController(requireCompatActivity(), it)
        })
    }
}
