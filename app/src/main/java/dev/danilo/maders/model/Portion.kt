package dev.danilo.maders.model

import com.google.gson.annotations.SerializedName

data class Portion(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("imageURL")
    var imageUrl: String?,
    @SerializedName("price")
    var price: Double?
) {
    companion object {
        fun mockItems(): List<Portion> {
            return listOf(
                Portion(
                    "1",
                    "Ração",
                    "https://petsplace.s3.sa-east-1.amazonaws.com/2511448.jpg",
                    123.89
                ),
                Portion(
                    "1",
                    "Ração 2",
                    "https://petsplace.s3.sa-east-1.amazonaws.com/2511448.jpg",
                    123.89
                ),
                Portion(
                    "1",
                    "Ração 3",
                    "https://petsplace.s3.sa-east-1.amazonaws.com/2511448.jpg",
                    123.89
                ),
                Portion(
                    "1",
                    "Ração 4",
                    "https://petsplace.s3.sa-east-1.amazonaws.com/2511448.jpg",
                    123.89
                ),
                Portion(
                    "1",
                    "Ração 5",
                    "https://petsplace.s3.sa-east-1.amazonaws.com/2511448.jpg",
                    123.89
                ),
                Portion(
                    "1",
                    "Ração 6",
                    "https://petsplace.s3.sa-east-1.amazonaws.com/2511448.jpg",
                    123.89
                ),
                Portion(
                    "1",
                    "Ração 7",
                    "https://petsplace.s3.sa-east-1.amazonaws.com/2511448.jpg",
                    123.89
                )
            )
        }
    }
}
