package com.amaterek.app.navigation

import com.amaterek.app.screen.splash.SplashNavigationCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object AppNavigationModule {

    @Provides
    fun providesSplashNavigationCallback(): SplashNavigationCallback =
        AppSplashNavigationCallback()
}