package eu.marcocattaneo.samplecoroutines.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import eu.marcocattaneo.samplecoroutines.R
import eu.marcocattaneo.samplecoroutines.data.GithubApiDatasource
import eu.marcocattaneo.samplecoroutines.data.GithubApiService
import eu.marcocattaneo.samplecoroutines.data.Repository
import eu.marcocattaneo.samplecoroutines.domain.GetRepositoriesUseCase
import eu.marcocattaneo.samplecoroutines.utils.LiveDataResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val githubService: GithubApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainViewModel.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(GithubApiService::class.java)
    }

    private val mainViewModelFactory: MainViewModelFactory = MainViewModelFactory(Dispatchers.Main, Dispatchers.IO, GetRepositoriesUseCase(GithubApiDatasource(this.githubService)))

    private val mainViewModel: MainViewModel by lazy { ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java) }

    private val dataObserver = Observer<LiveDataResult<List<Repository>>> {
        // User data
        when (it?.status) {
            LiveDataResult.STATUS.ERROR -> {

            }
            LiveDataResult.STATUS.SUCCESS -> {

            }
            LiveDataResult.STATUS.LOADING -> {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mainViewModel.repositoriesLiveData.observe(this, this.dataObserver)

        process.setOnClickListener {
            this.mainViewModel.fetchRepositories("mcatta")
        }

    }
}
