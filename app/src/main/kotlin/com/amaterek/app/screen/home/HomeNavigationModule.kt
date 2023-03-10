package com.amaterek.app.screen.home

import com.amaterek.ui.compose.navigation.DestinationRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object HomeNavigationModule {

    @Provides
    @Singleton
    @IntoSet
    fun providesHomeDestination(): DestinationRoute<*> = HomeDestination
}
