package com.amaterek.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.amaterek.app.screen.splash.SplashDestination
import com.amaterek.ui.navigation.compose.DestinationRouteWithBuilder
import com.amaterek.ui.navigation.compose.NavigationHost
import com.amaterek.ui.navigation.rememberNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    @Inject
    internal lateinit var destinations: Set<@JvmSuppressWildcards DestinationRouteWithBuilder<*>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationHost(
                startDestination = SplashDestination,
                navigator = rememberNavigator(),
            ) {
                destinations.forEach { destinationRoute ->
                    destinationRoute.build(this)
                }
            }
        }
    }
}
