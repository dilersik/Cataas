@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.catass.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catass.R
import com.example.catass.model.Cats
import com.example.catass.ui.navigation.CatNavigationEnum
import com.example.catass.ui.widgets.CatItemRowWidget
import com.example.catass.utils.showToast

@Composable
fun CatsView(navController: NavController, viewModel: CatsViewModel) {
    val context = LocalContext.current
    val cats = viewModel.cats.collectAsState().value
    val error = viewModel.error.collectAsState().value

    when {
        viewModel.loading.collectAsState().value -> CircularProgressIndicator()
        error != null -> context.showToast(error)
        cats != null -> MainScaffold(navController, cats)
    }
}

@Composable
fun MainScaffold(
    navController: NavController,
    cats: Cats
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier
                            .size(48.dp)
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        imageVector = Icons.Filled.Home,
                        contentDescription = "",
                    )
                    Text(stringResource(R.string.cats_title))
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
        )
    },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                LazyColumn(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                ) {
                    items(items = cats) { catItem ->
                        CatItemRowWidget(
                            catItem = catItem,
                            onClick = {
                                navController.navigate(route = CatNavigationEnum.DETAIL.name + "/$it")
                            }
                        )
                    }
                }
            }
        }
    )
}