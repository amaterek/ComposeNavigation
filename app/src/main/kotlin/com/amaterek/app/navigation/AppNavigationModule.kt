package com.amaterek.app.navigation

import com.amaterek.app.screen.home.HomeDestination
import com.amaterek.app.screen.splash.SplashNavigationCallback
import com.amaterek.util.compose.navigation.destination.Destination
import com.amaterek.util.compose.navigation.destination.ReplaceCurrentDestination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.delay

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppNavigationModule {

    @Provides
    fun providesSplashNavigationCallback(): SplashNavigationCallback =
        object : SplashNavigationCallback {
            override suspend fun invoke(): Destination {
                delay(1000)
                return ReplaceCurrentDestination(HomeDestination)
            }
        }
}