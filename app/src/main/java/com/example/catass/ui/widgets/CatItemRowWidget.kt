package com.example.catass.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.catass.R
import com.example.catass.model.CatItem
import com.example.catass.utils.Constant

@Composable
fun CatItemRowWidget(catItem: CatItem, onClick: (String) -> Unit = {}) {

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onClick(catItem._id) },
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                color = Color.White
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constant.BASE_URL_IMAGE + catItem._id)
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = ""
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                        append(stringResource(R.string.tags_lbl))
                    }
                    withStyle(style = SpanStyle(color = Color.Gray, fontSize = 13.sp)) {
                        catItem.tags.forEach {
                            append("$it, ")
                        }
                    }
                }, lineHeight = 16.sp)
            }

        }
    }
}