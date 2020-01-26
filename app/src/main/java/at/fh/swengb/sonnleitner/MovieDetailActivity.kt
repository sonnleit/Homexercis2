package at.fh.swengb.sonnleitner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_MOVIE_ID = "MOVIE_ID_EXTRA"
        val ADD_OR_EDITED_RATING_REQUEST = 1;
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getStringExtra(MainActivity.EXTRA_MOVIE_ID)
        showMovie(movieId)

        open_review.setOnClickListener {
            val ratingIntent = Intent(this, MovieRatingActivity::class.java)
            ratingIntent.putExtra(EXTRA_MOVIE_ID, movieId)
            startActivityForResult(ratingIntent, ADD_OR_EDITED_RATING_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_OR_EDITED_RATING_REQUEST && resultCode == Activity.RESULT_OK) {
            val resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
            val movieId = intent.getStringExtra(MainActivity.EXTRA_MOVIE_ID)
            showMovie(movieId)
        }
    }

    private fun showMovie(movieId: String?){

        if(movieId != null)
            MovieRepository.movieById(movieId,
                success = {
                    with(it) {
                        movie_detail_header.text = title
                        movie_detail_director.text = director.name
                        movie_detail_actors.text = actors.joinToString(", ") { actor -> actor.name }
                        movie_detail_genre.text = genres.joinToString(", ")
                        movie_detail_avg_review_bar.rating = reviewAverage().toFloat()
                        movie_detail_avg_review_value.text = (kotlin.math.round(reviewAverage() * 10) / 10).toString()
                        movie_detail_review_count.text = reviews.count().toString()
                        movie_detail_release.text = release
                        movie_detail_plot.text = plot
                        Glide.with(movie_detail_backdrop).load("$backdropImagePath?$id").into((movie_detail_backdrop))
                        Glide.with(image_movie_poster_item).load("$posterImagePath?$id").into((image_movie_poster_item))
                    }
                },
                error = {
                    Log.e("MovieDetailActivity", it)
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                }
            )
        else
            Log.e("MovieDetailActivity","movie id is null")
    }
}
