@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.catass.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.catass.R
import com.example.catass.utils.Constant

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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxSize(),
                            colors = CardDefaults.cardColors(Color.White),
                            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                            elevation = CardDefaults.cardElevation(6.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                val painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(Constant.BASE_URL_IMAGE + cat._id)
                                        .crossfade(true)
                                        .transformations(CircleCropTransformation())
                                        .build()
                                )
                                Image(
                                    painter = painter,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxWidth().height(500.dp)
                                )
                                Text(buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.DarkGray,
                                            fontSize = 16.sp
                                        )
                                    ) {
                                        append(stringResource(R.string.tags_lbl))
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        )
                                    ) {
                                        cat.tags.forEach {
                                            append("$it, ")
                                        }
                                    }
                                }, lineHeight = 16.sp)
                            }
                        }
                    }
                }
            }
        )
    }
}