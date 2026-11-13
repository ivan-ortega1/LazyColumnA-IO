package org.insbaixcamp.lazycolumna

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import org.insbaixcamp.lazycolumna.model.Cat
import org.insbaixcamp.lazycolumna.ui.components.CatItem
import org.insbaixcamp.lazycolumna.ui.theme.LazyColumnATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val TAG = "MainActivity"
    var kittens by remember { mutableStateOf<List<Cat>>(emptyList()) }
    val db = Firebase.firestore
    var castanya: Cat by remember {
        mutableStateOf(
    Cat(
                id = "1",
                name = "Castanya",
                age = 1,
                breed = "",
                color = "Gray",
                sex = "Female",
                weight = 1.0
            )
        )
    }

    db.collection("cats").add(castanya).addOnSuccessListener {
        Log.i(TAG, "Escriu correcte")
    }.addOnFailureListener {
        Log.e(TAG, "Ha donat un error al escriure a la base de dades")
    }

    db.collection("cats").addSnapshotListener { result, exception ->
        var kittensTmp: ArrayList<Cat> = arrayListOf()
        if (result != null) {
            for (tmpKitten in result) {
                var kittenTmp: Cat = tmpKitten.toObject(Cat::class.java)
                kittensTmp.add(kittenTmp)
                Log.i(TAG, "Document: ${tmpKitten.data}")
            }
        }
        kittens = kittensTmp
    }

    Column() {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize().fillMaxWidth()
        ) {
            items(
                items = kittens,
                key = { cat -> cat.id }
            ){cat -> CatItem(cat)}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyColumnATheme {
        Greeting("Android")
    }
}