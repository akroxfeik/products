package com.sparkly.productslist.ui.screens

import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.Gson
import com.sparkly.productslist.R
import com.sparkly.productslist.data.model.Product
import com.sparkly.productslist.ui.LoadingBar
import com.sparkly.productslist.viewmodel.ProductListViewModel

@Composable
fun ProductList(
    viewModel: ProductListViewModel = hiltViewModel(),
    onItemClicked: (json: String) -> Unit
) {
    Box {
        Column {
            when {
                viewModel.state.isLoading -> LoadingBar()
                !viewModel.state.isConnected -> Text(
                    text = stringResource(R.string.not_connected),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                )
            }
            LazyColumn (Modifier.fillMaxSize()) {
                items(viewModel.state.list) { item ->
                    CardProduct(item, onItemClicked = onItemClicked)
                }
            }
        }
    }
}

@Composable
fun CardProduct(item: Product, onItemClicked: (json: String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .animateContentSize()
            .fillMaxSize()
            .clickable {
                onItemClicked(Uri.encode(Gson().toJson(item))) }
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
        ) {
            Text(
                text = item.title,
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                minLines = 1,
                fontWeight = FontWeight.Bold
            )
            Row {
                Icon(
                    imageVector = getImage(item.rating),
                    contentDescription = item.title,
                )
                Text(
                    text = "Rating: ${item.rating}"
                )
            }

        }
    }
}

fun getImage(value: Double): ImageVector {
    return when {
        value < 3 -> Icons.Default.Clear
        value in 3.0..3.99 -> Icons.Default.Done
        value > 4 -> Icons.Default.Star
        else -> Icons.Default.Refresh
    }
}