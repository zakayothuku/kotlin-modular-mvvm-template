# Kotlin Modularized MVVM Template
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.72-blue.svg)](http://kotlinlang.org/) [![License](https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg)](http://www.apache.org/licenses/LICENSE-2.0)

This is a template to get up and started with a modularized Android application with an MVVM Clean Architecture at it's core with a modern 2020 approach to Android application development with Kotlin and the latest tech-stack.

### Code style

To maintain the style and quality of the code, are used the bellow static analysis tools. All of them use properly configuration and you find them in the project root directory.

| Tools                                                   | Config file                                                                                     | Check command             | Fix command               |
|---------------------------------------------------------|------------------------------------------------------------------------------------------------:|---------------------------|---------------------------|
| [detekt](https://github.com/arturbosch/detekt)          | [/.detekt](https://github.com/zakayothuku/kotlin-modular-mvvm-template/tree/master/.detekt)     | `./gradlew detekt`        | -                         |
| [ktlint](https://github.com/pinterest/ktlint)           | -                                                                                               | `./gradlew ktlint`        | `./gradlew ktlintFormat`  |
| [spotless](https://github.com/diffplug/spotless)        | [/.spotless](https://github.com/zakayothuku/kotlin-modular-mvvm-template/tree/master/.spotless) | `./gradlew spotlessCheck` | `./gradlew spotlessApply` |
| [lint](https://developer.android.com/studio/write/lint) | [/.lint](https://github.com/zakayothuku/kotlin-modular-mvvm-template/tree/master/.lint)         | `./gradlew lint`          | -                         |

All these tools are integrated in [pre-commit git hook](https://git-scm.com/book/en/v2/Customizing-Git-Git-Hooks), in order
ensure that all static analysis and tests passes before you can commit your changes. To skip them for specific commit add this option at your git command:

```properties
git commit --no-verify
```

The pre-commit git hooks have exactly the same checks as [CircleCI](https://circleci.com/) and are defined in this [script](https://github.com/zakayothuku/kotlin-modular-mvvm-template/blob/master/scripts/git-hooks/pre-commit.sh).
This step ensures that all commits comply with the established rules. However the continuous integration will ultimately be validated that the changes are correct.

## Design

App [support different screen sizes](https://developer.android.com/training/multiscreen/screensizes) and the content has been adapted to fit for mobile devices and tablets. To do that, it has been created a flexible layout using one or more of the following concepts:
-   [Use constraintLayout](https://developer.android.com/training/multiscreen/screensizes#ConstraintLayout)
-   [Avoid hard-coded layout sizes](https://developer.android.com/training/multiscreen/screensizes#TaskUseWrapMatchPar)
-   [Create alternative layouts](https://developer.android.com/training/multiscreen/screensizes#alternative-layouts)
-   [Use the smallest width qualifier](https://developer.android.com/training/multiscreen/screensizes#TaskUseSWQuali)
-   [Use the available width qualifier](https://developer.android.com/training/multiscreen/screensizes#available-width)
-   [Add orientation qualifiers](https://developer.android.com/training/multiscreen/screensizes#TaskUseOriQuali)

Design follows these recommendations [android material design](https://developer.android.com/guide/topics/ui/look-and-feel), a comprehensive guide for visual, motion, and interaction design across platforms and devices.
Granting the project in this way a great user experience (UX) and user interface (UI). For more info about UX best practices visit [link](https://developer.android.com/topic/google-play-instant/best-practices/apps).

## Architecture

The architecture of the application is based, apply and strictly complies with the following:

-   A single-activity architecture, using the [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) to manage fragment operations.
-   [Android architecture components](https://developer.android.com/topic/libraries/architecture/), part of Android Jetpack for give to project a robust design, testable and maintainable.
-   Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (MVVM) facilitating a [separation](https://en.wikipedia.org/wiki/Separation_of_concerns) of development of the graphical user interface.
-   [S.O.L.I.D](https://en.wikipedia.org/wiki/SOLID) design principles intended to make software designs more understandable, flexible and maintainable.
-   [Modular app architecture](https://proandroiddev.com/build-a-modular-android-app-architecture-25342d99de82) allows to be developed features in isolation, independently from other features.

### Modules

Modules are collection of source files and build settings that allow you to divide a project into discrete units of functionality. In this case apart from dividing by functionality/responsibility, existing the following dependence between them:

The above graph shows the app modularisation:
-   `:app` depends on `:core` and indirectly depends on `:features` by dynamic-features.
-   `:features` modules depends on `:commons`, `:core`, `:libraries` and `:app`.
-   `:core` and `:commons` only depends for possible utils on `:libraries`.
-   `:libraries` don’t have any dependency.

#### App module

The `:app` module is an [com.android.application](https://developer.android.com/studio/build/), which is needed to create the app bundle.  It is also responsible for initiating the [dependency graph](https://github.com/google/dagger), [play core](https://developer.android.com/reference/com/google/android/play/core/release-notes) and another project global libraries, differentiating especially between different app environments.

#### Core module

The `:core` module is an [com.android.library](https://developer.android.com/studio/projects/android-library)  for serving network requests or accessing to the database. Providing the data source for the many features that require it.

#### Features modules

The `:features` module are an [com.android.dynamic-feature](https://developer.android.com/studio/projects/dynamic-delivery) is essentially a gradle module which can be downloaded independently from the base application module. It can hold code and resources and include dependencies, just like any other gradle module.

#### Commons modules

The `:commons` modules are an [com.android.library](https://developer.android.com/studio/projects/android-library) only contains code and resources which are shared between feature modules. Reusing this way resources, layouts, views, and components in the different features modules, without the need to duplicate code.

#### Libraries modules

The `:libraries` modules are an [com.android.library](https://developer.android.com/studio/projects/android-library), basically contains different utilities that can be used by the different modules.

### Architecture components

Ideally, ViewModels shouldn’t know anything about Android. This improves testability, leak safety and modularity. ViewModels have different scopes than activities or fragments. While a ViewModel is alive and running, an activity can be in any of its lifecycle states. Activities and fragments can be destroyed and created again while the ViewModel is unaware.

Passing a reference of the View (activity or fragment) to the ViewModel is a serious risk. Lets assume the ViewModel requests data from the network and the data comes back some time later. At that moment, the View reference might be destroyed or might be an old activity that is no longer visible, generating a memory leak and, possibly, a crash.

The communication between the different layers follow the above diagram using the reactive paradigm, observing changes on components without need of callbacks avoiding leaks and edge cases related with them.

### Best practices

Avoid reinventing the wheel by following these guidelines:

-   [Google best practices](https://developer.android.com/distribute/best-practices)
-   [Android development best practices](https://github.com/futurice/android-best-practices)

## Contributions

All contributions are welcome!
Please feel free to post questions, recommendations, ideas, bugs by create [new issue](https://github.com/zakayothuku/kotlin-modular-mvvm-template/issues/new) following the template or if you want create directly [new pull request](https://github.com/zakayothuku/kotlin-modular-mvvm-template/compare).

## Resources

### Projects

This is project is a sample, to inspire you and should handle most of the common cases, but obviously not all. If you need to take a look at additional resources to find solutions for your project, visit these interesting projects:

-   [iosched](https://github.com/google/iosched) (by [google](https://github.com/google)) - official Android application from google IO 2019.
-   [plaid](https://github.com/android/plaid) (by [android](https://github.com/android)) - app which provides design news & inspiration, being an example of implementing material design.
-   [kotlin-sample-app](https://github.com/VMadalin/kotlin-sample-app) (by [android](https://github.com/android)) - Android Components Architecture in a Modular Word sample project.
-   [architecture-sample](https://github.com/android/architecture-samples) (by [android](https://github.com/android)) - collection of samples to discuss and showcase different architectural tools and patterns for Android apps.
-   [android-clean-architecture-boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate) (by [bufferapp](https://github.com/bufferapp)) - an android boilerplate project using clean architecture
-   [android-clean-architecture-boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate) (by [bufferapp](https://github.com/bufferapp)) - an android boilerplate project using clean architecture
-   [android-kotlin-clean-architecture](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture) (by [sanogueralorenzo](https://github.com/sanogueralorenzo)) - android sample Clean Architecture app written in Kotlin.
-   [modularization-example](https://github.com/JeroenMols/ModularizationExample) (by [JeroenMols](https://github.com/JeroenMols)) - easy to understand real-life example of a modularized Android app.

### License

```
    Copyright 2020 Zakayo Thuku.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
