@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.catass.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.catass.ui.widgets.CatItemRowWidget

@Composable
fun CatsDetailView(navController: NavController, viewModel: CatsViewModel, id: String?) {
    val cat = viewModel.cats.collectAsState().value?.firstOrNull { it._id == id }

    if (cat != null) {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = cat.tags.first()) },
                navigationIcon = {
                    IconButton({
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "menu items"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
            )
        },
            content = { padding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CatItemRowWidget(catItem = cat)
                    }
                }
            }
        )
    }
}