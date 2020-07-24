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

package com.schatzdesigns.template

import android.content.Context
import android.widget.Toast
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.schatzdesigns.core.di.CoreComponent
import com.schatzdesigns.core.di.DaggerCoreComponent
import com.schatzdesigns.core.di.modules.ContextModule
import com.schatzdesigns.core.utils.ThemeUtils
import com.schatzdesigns.template.di.DaggerApplicationComponent
import javax.inject.Inject
import timber.log.Timber

class TemplateApp : SplitCompatApplication() {

    lateinit var coreComponent: CoreComponent

    companion object {

        /**
         * Obtain core dagger component.
         *
         * @param context The application context
         */
        @JvmStatic
        fun coreComponent(context: Context) = (context.applicationContext as? TemplateApp)?.coreComponent
    }

    /**
     * Called when the application is starting, before any activity, service, or receiver objects
     * (excluding content providers) have been created.
     *
     * @see SplitCompatApplication.onCreate
     */
    override fun onCreate() {
        super.onCreate()
        initTimberDebugTree()
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }

    /**
     * Initialize Timber debug tree.
     */
    private fun initTimberDebugTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
     * Initialize core dependency injection component.
     */
    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    /**
     * Initialize app dependency injection component.
     */
    private fun initAppDependencyInjection() {
        DaggerApplicationComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }
}
