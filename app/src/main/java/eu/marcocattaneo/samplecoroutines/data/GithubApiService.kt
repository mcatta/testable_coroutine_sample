package eu.marcocattaneo.samplecoroutines.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("/users/{username}/repos")
    fun getUserRepositories(@Path("username") username: String): Call<List<ApiRepository>>

}