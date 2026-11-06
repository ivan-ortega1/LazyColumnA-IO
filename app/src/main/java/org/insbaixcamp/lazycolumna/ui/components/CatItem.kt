package org.insbaixcamp.lazycolumna.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.insbaixcamp.lazycolumna.model.Cat

@Composable
fun CatItem(cat: Cat) {
    Column(Modifier.fillMaxWidth()) {
        Card(Modifier.padding(4.dp)) {
            Text(text = cat.name)
            Text(text = cat.age.toString())
        }
    }
}
