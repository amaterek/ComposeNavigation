package com.amaterek.app.screen.splash

import com.amaterek.ui.navigation.compose.DestinationRouteWithBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SplashNavigationModule {

    @Provides
    @Singleton
    @IntoSet
    fun providesSplashDestination(): DestinationRouteWithBuilder<*> = SplashDestination
}
