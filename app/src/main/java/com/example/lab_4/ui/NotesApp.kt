package com.example.lab_4.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab_4.ui.navigation.Screen
import com.example.lab_4.ui.screens.AddEditNoteScreen
import com.example.lab_4.ui.screens.NotesScreen

@Composable
@SuppressLint("StateFlowValueCalledInComposition")
fun NotesApp() {
    val navController = rememberNavController()
    val notesViewModel: NotesViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Notes.route
    ) {
        composable(Screen.Notes.route) {
            NotesScreen(
                notesViewModel.notes.value,
                onAddNote = {
                    navController.navigate(Screen.AddEditNote.route)
                },
                onDeleteNote = {  }
            )
        }

        composable(Screen.AddEditNote.route) {
            AddEditNoteScreen(
                navController,
                onSave = {

                }
            )
        }
    }
}