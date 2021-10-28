package com.ocram.qichwadic.core.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog

@Composable
fun ConfirmDialog(
    open: Boolean = false,
    dismiss: () -> Unit,
    title: String,
    content: String,
    dismissBtnText: String,
    confirmBtnText: String,
    onConfirm: () -> Unit
) {
    val confirmAndDismiss = {
        onConfirm()
        dismiss()
    }
    if(open) {
        MaterialTheme {
            Column {
                AlertDialog(
                    onDismissRequest = dismiss,
                    title = { Text(title) },
                    text = { Text(content) },
                    confirmButton = { Button(onClick = confirmAndDismiss) { Text(confirmBtnText) } },
                    dismissButton = { Button(onClick = dismiss) { Text(dismissBtnText) } }
                )
            }
        }
    }
}

@Composable
fun <T>SelectionListDialog(
    open: Boolean = false,
    items: List<T>,
    onItemSelected: (item: T) -> Unit,
    onDismissRequest: () -> Unit = {},
    titleView: @Composable () -> Unit = {},
    itemView: @Composable (item: T) -> Unit
) {
    if (open) {
        val limitHeightModifier = if (items.size > 10) Modifier.fillMaxHeight(0.8f)
        else Modifier
        MaterialTheme {
            Dialog(
                onDismissRequest = onDismissRequest,
            ) {
                Card(modifier = limitHeightModifier) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        titleView()
                        LazySelectableList(
                            items = items,
                            onItemSelected = {
                                onItemSelected(it)
                                onDismissRequest()
                            },
                            itemView = itemView
                        )
                    }
                }
            }
        }
    }
}