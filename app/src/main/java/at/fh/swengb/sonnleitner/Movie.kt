package at.fh.swengb.sonnleitner

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class Movie(
    val id: String,
    val title: String,
    val release: String,
    val reviews: MutableList<Review>,
    val posterImagePath: String,
    val backdropImagePath: String
) {
    fun reviewAverage(): Double {
        return reviews
            .map{review -> review.reviewValue}
            .average()
            .run { if(isNaN()) 0.0 else this }
    }
}
