package com.example.lab_4.ui.screens.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lab_4.data.local.Note
import com.example.lab_4.ui.theme.Lab_4Theme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddEditNoteScreen(
    noteId: Int?,
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val state = viewModel.currentNote.collectAsState()

    LaunchedEffect(noteId) {
        if (noteId != null) {
            viewModel.getNoteById(noteId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Заметки") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (noteId == null) {
                            viewModel.addNote(
                                Note(
                                    title = state.value?.title ?: "Без названия",
                                    content = state.value?.content ?: ""
                                )
                            )
                        } else {
                            viewModel.updateNote(
                                Note(
                                    id = noteId,
                                    title = state.value?.title ?: "Без названия",
                                    content = state.value?.content ?: ""
                                )
                            )
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Сохранить"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
        ) {
            OutlinedTextField(
                value = state.value?.title ?: "",
                onValueChange = { viewModel.updateTitleState(it) },
                label = { Text("Имя") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.value?.content ?: "",
                onValueChange = { viewModel.updateContentState(it) },
                label = { Text("Напишите что-нибудь") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddEditNoteScreenPreview() {
    Lab_4Theme(darkTheme = true) {
        val navController = rememberNavController()
        AddEditNoteScreen(null, navController)
    }
}