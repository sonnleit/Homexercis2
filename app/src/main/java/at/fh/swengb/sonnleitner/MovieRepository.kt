package at.fh.swengb.sonnleitner

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MovieRepository {

    fun movieList(success: (lessonList: List<Movie>) -> Unit, error: (errorMessage: String) -> Unit) {
        MovieApi.retrofitService.movies().enqueue(object: Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                error(t.localizedMessage?.toString() ?: "Call failed")
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }
        })
    }

    fun movieById(id: String, success: (movieDetail: MovieDetail) -> Unit, error: (errorMessage: String) -> Unit){
        MovieApi.retrofitService.movieDetail(id).enqueue(object: Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success(responseBody)
                } else {
                    error("Something went wrong")
                }
            }

        })
    }

    fun rateMovie(id: String, review: Review, success: (msg: String) -> Unit, error: (errorMsg: String) -> Unit){
        MovieApi.retrofitService.reviewMovie(id, review).enqueue(object: Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                error("The call failed")
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    success("Successfully reviewed the movie")
                } else {
                    error("Something went wrong")
                }
            }
        })
    }

}