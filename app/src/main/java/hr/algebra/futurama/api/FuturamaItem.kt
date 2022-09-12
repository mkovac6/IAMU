package hr.algebra.futurama.api

import com.google.gson.annotations.SerializedName

data class FuturamaItem(
    @SerializedName("Species") val species: String,
    @SerializedName("Age") val age: String,
    @SerializedName("Planet") val planet: String,
    @SerializedName("Profession") val profession: String,
    @SerializedName("Status") val status: String,
    @SerializedName("FirstAppearance") val firstAppearance: String,
    @SerializedName("PicUrl") val picUrl: String,
    @SerializedName("Relatives") val relatives: String,
    @SerializedName("VoicedBy") val voicedBy: String,
    @SerializedName("Name") val name: String
)
