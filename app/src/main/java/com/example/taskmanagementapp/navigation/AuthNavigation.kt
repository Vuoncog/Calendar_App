package com.example.taskmanagementapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.ui.screens.auth.LoginContent
import com.example.taskmanagementapp.ui.screens.auth.RecoverPasswordContent
import com.example.taskmanagementapp.ui.screens.auth.RegisterContent

fun NavGraphBuilder.authNavigation(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginContent(
                navigateToHome = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                },
                navigateToRecoverPassword = {
                    navController.navigate(AuthScreen.RecoverPassword.route)
                },
                navigateToRegister = {
                    navController.navigate(AuthScreen.Register.route)
                }
            )
        }
        composable(route = AuthScreen.Register.route) {
            RegisterContent(
                navigateToLogin = {
                    navController.navigate(AuthScreen.Login.route)
                }
            )
        }
        composable(route = AuthScreen.RecoverPassword.route) {
            RecoverPasswordContent()
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "login")
    object Register : AuthScreen(route = "register")
    object RecoverPassword : AuthScreen(route = "recover_password")
}