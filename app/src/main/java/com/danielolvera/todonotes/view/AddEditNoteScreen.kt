package com.danielolvera.todonotes.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.danielolvera.todonotes.viewmodel.NoteViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danielolvera.todonotes.R
import com.danielolvera.todonotes.intent.NoteIntent
import com.danielolvera.todonotes.model.local.Note
import kotlin.math.log

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    navHostController: NavHostController,
    noteId: Int,
    viewModel: NoteViewModel = viewModel()
) {
    var title by remember {
        mutableStateOf("")
    }

    var  description by remember {
        mutableStateOf("")
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                Text(text = if (noteId == -1) "Add New Note" else "Edit Note")
            },
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val note = Note(id = if (noteId == -1) 0 else noteId, title = title, description = description)
                if (noteId == -1) {
                    viewModel.handleIntent(NoteIntent.AddNote(title, description))
                } else {
                    viewModel.handleIntent(NoteIntent.UpdateNote(note))
                }
                navHostController.popBackStack()
            }) {
                Icon(painter = painterResource(id = R.drawable.baseline_save_24), contentDescription = "Save")
            }
        }
    ) { _ ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it},
                label = { Text(text = "Title")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = description,
                onValueChange = {description = it},
                label = { Text(text = "Description")},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}