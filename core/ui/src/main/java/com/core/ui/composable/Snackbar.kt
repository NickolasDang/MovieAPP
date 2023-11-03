package com.core.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Snackbar(
    snackbarHostState: SnackbarHostState,
    snackbarMessage: String,
    onDismiss: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier.padding(16.dp)
    ) {
        Snackbar(
            action = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("Dismiss")
                }
            }
        ) {
            Text(snackbarMessage)
        }
    }
}