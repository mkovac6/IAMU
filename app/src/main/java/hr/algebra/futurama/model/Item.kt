package hr.algebra.futurama.model

data class Item(
    var _id: Long?,
    val Species: String,
    val Age: String,
    val Planet: String,
    val PicUrl: String,
    val Name: String,
    var read: Boolean
)
