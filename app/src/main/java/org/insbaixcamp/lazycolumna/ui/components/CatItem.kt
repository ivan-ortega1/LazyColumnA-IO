package org.insbaixcamp.lazycolumna.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import org.insbaixcamp.lazycolumna.model.Cat

@Composable
fun CatItem(cat: Cat) {



    Column(Modifier.fillMaxWidth()) {
        Card(Modifier.fillMaxWidth().padding(4.dp)) {
            Row() {
                Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                    Text(text = "Nom: ${cat.name}")
                    Text(text = "Raça: ${cat.breed}")
                }
                Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                    Text(text = "Edat: ${cat.age}")
                    Text(text = "Color: ${cat.color}")
                }
                Column() {
                    Button(onClick = {
                        var db = Firebase.firestore
                        db.collection("cats").document(cat.id).delete()
                    }) {
                        Text(text = "Borrar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatItemPreview(){
    var cat: Cat = Cat(
        id = "1",
        name = "Castanya",
        age = 1,
        breed = "Gris",
        color = "Gray",
        sex = "Female",
        weight = 1.0
    )
    CatItem(cat)
}