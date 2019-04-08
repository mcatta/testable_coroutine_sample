package eu.marcocattaneo.samplecoroutines.data

interface GithubRepository {

    suspend fun fetchRepositories(username: String) : List<ApiRepository>

}