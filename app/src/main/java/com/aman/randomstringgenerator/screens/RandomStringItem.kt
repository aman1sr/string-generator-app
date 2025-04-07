package com.aman.randomstringgenerator.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aman.randomstringgenerator.data.local.RandomStringEntity
import com.aman.randomstringgenerator.ui.theme.RandomStringGeneratorTheme

@Composable
fun RandomStringItem(entity: RandomStringEntity, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "String: ${entity.value}")
                Text(text = "Length: ${entity.length}")
                Text(text = "Created: ${entity.created}")
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RandomStringItemPreview() {
    RandomStringGeneratorTheme {
        RandomStringItem(
            entity = RandomStringEntity(
                id = 1,
                value = "Test String",
                length = 11,
                created = "2024-03-20"
            ),
            onDelete = {}
        )
    }
}