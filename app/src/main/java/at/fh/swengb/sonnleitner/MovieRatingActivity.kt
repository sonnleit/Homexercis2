package at.fh.swengb.sonnleitner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import at.fh.swengb.sonnleitner.MovieRepository.rateMovie
import kotlinx.android.synthetic.main.activity_movie_rating.*

class MovieRatingActivity : AppCompatActivity() {

    companion object{
        val EXTRA_MOVIE_ID_RATING_RETURN = "MOVIE_ID_RATING_RETURN_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_rating)

        val movieId = intent.getStringExtra(MovieDetailActivity.EXTRA_MOVIE_ID)

        if (movieId == null) {
            finish()

        } else {

            //output movie title
            MovieRepository.movieById(movieId,
                success = { movie_rating_header.text =  it.title },
                error = {  msg -> Log.e("MovieReviewActivity", msg)})



            rate_movie.setOnClickListener {

                val myMovieReviewObject = Review(
                    movie_rating_bar.rating.toDouble(),
                    movie_ratingtxt.text.toString()
                )



                MovieRepository.rateMovie( movieId, myMovieReviewObject,
                    success = {  msg -> Log.e("MovieReviewActivity", msg)},
                    error = {  msg -> Log.e("MovieReviewActivity", msg)})

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_MOVIE_ID_RATING_RETURN, movieId)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }

        }
    }
}



