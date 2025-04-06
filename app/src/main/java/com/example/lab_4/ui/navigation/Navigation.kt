package com.example.lab_4.ui.navigation

sealed class Screen(val route: String) {
    object Notes : Screen("notes")
    object AddEditNote : Screen("add_edit_note")
}