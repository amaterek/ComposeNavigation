package com.amaterek.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.amaterek.app.navigation.AppNavigationHost
import com.amaterek.app.screen.splash.SplashDestination
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    @Inject
    internal lateinit var appNavigationHost: AppNavigationHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            appNavigationHost.buildAppNavigation(
                navController = rememberNavController(),
                startDestination = SplashDestination
            )
        }
    }
}
