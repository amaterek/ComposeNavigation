package com.amaterek.app.screen.forresult

import com.amaterek.ui.compose.navigation.DestinationRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ScreenForResultNavigationModule {

    @Provides
    @Singleton
    @IntoSet
    fun providesScreenForResultDestination(): DestinationRoute<*> =
        ScreenForResultDestination
}
