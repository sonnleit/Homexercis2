package at.fh.swengb.sonnleitner

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieDetail(
    id: String,
    title: String,
    release: String,
    reviews: MutableList<Review>,
    posterImagePath: String,
    backdropImagePath: String,
    val plot: String,
    val genres: List<String>,
    val director: Person,
    val actors: List<Person>
) : Movie(
    id,
    title,
    release,
    reviews,
    posterImagePath,
    backdropImagePath
)