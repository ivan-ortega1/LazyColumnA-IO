package org.insbaixcamp.lazycolumna.model

import com.google.firebase.firestore.DocumentId

data class Cat (
    @set:DocumentId
    var id: String,
    var name: String,
    var age: Int,
    var breed: String,
    var color: String,
    var sex: String,
    var weight: Double,
)