package com.danielolvera.todonotes.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.danielolvera.todonotes.viewmodel.NoteViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danielolvera.todonotes.state.NoteState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    navController: NavHostController,
    viewModel: NoteViewModel = viewModel()
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 56.dp, 16.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val state by viewModel.state.collectAsState()

        when(state) {
            is NoteState.Loading -> CircularProgressIndicator()
            is NoteState.Success -> {
                val notes = (state as NoteState.Success).notes
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            navController.navigate("addEditNote/-1")
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Add Note")
                        }
                    }
                )  { _ ->
                    LazyColumn {
                        items(notes) { note ->
                            NoteItem(
                                note = note,
                                onClick = {
                                    navController.navigate("addEditNote/${note.id}")
                                })
                        }
                    }
                }
            }
            is NoteState.Error -> Text(text = "Error loading notes!")
        }
    }
}