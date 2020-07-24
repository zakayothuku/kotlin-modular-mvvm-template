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

package com.schatzdesigns.commons.ui.bindings

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Simplification to check and setup view as visible.
 */
@set:BindingAdapter("visible")
var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

/**
 * Simplification to check and setup view as gone.
 */
@set:BindingAdapter("gone")
var View.gone
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }

/**
 * Simplification to check and setup view as invisible.
 */
@set:BindingAdapter("invisible")
var View.invisible
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }
