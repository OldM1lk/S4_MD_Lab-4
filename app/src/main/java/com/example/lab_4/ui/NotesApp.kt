package com.example.lab_4.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab_4.ui.screens.edit.AddEditNoteScreen
import com.example.lab_4.ui.screens.notes.NotesScreen

@Composable
fun NotesApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "notes"
    ) {
        composable("notes") {
            NotesScreen(navController = navController)
        }
        composable(
            route = "edit_note/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            AddEditNoteScreen(noteId = noteId, navController = navController)
        }
        composable("add_note") {
            AddEditNoteScreen(noteId = null, navController = navController)
        }
    }
}