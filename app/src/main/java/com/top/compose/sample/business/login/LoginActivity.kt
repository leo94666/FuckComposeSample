package com.top.compose.sample.business.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.top.fix.sample.business.ConstantRoute
import dagger.hilt.android.AndroidEntryPoint


fun launchDetailsActivity(context: Context) {
    val intent = Intent(context, LoginActivity::class.java)
    context.startActivity(intent)
}

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Navigation {
                finish()
            }
        }
    }
}

@Composable
fun Navigation(
    finish: () -> Unit = {}
) {
    val rememberNavController = rememberNavController()
    NavHost(
        navController = rememberNavController,
        startDestination = ConstantRoute.LOGIN_SCREEN
    ) {

        composable(ConstantRoute.LOGIN_SCREEN) {
            LoginScreen(rememberNavController) { finish() }
        }
        composable(ConstantRoute.REGISTER_SCREEN) {
            RegisterScreen (rememberNavController){ finish() }
        }
    }
}