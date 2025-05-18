package com.sparkly.productslist.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sparkly.productslist.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetails(
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.padding(5.dp),
    ) {
        Text(
            text = viewModel.receivedObject.value?.title!!,
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            minLines = 1,
            fontWeight = FontWeight.Bold
        )
        Row {
            Icon(
                imageVector = getImage(viewModel.receivedObject.value?.rating!!),
                contentDescription = viewModel.receivedObject.value?.title!!,
            )
            Text(
                text = "Rating: ${viewModel.receivedObject.value?.rating!!}"
            )
        }
        Text(
            text = "Price: $${viewModel.receivedObject.value?.price!!}"
        )
        Text(
            text = "Discount: ${viewModel.receivedObject.value?.discountPercentage}%"
        )
        Text(
            text = "Stock: ${viewModel.receivedObject.value?.stock}"
        )
    }
}