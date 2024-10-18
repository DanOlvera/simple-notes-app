package com.danielolvera.todonotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.danielolvera.todonotes.view.AddEditNoteScreen
import com.danielolvera.todonotes.view.NoteListScreen

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "noteList") {
        composable("noteList") {
            NoteListScreen(navHostController)
        }
        composable("addEditNote/{noteId}", arguments = listOf(
            navArgument("noteId") { defaultValue = -1 }
        )) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            AddEditNoteScreen(navHostController, noteId)
        }
    }
}