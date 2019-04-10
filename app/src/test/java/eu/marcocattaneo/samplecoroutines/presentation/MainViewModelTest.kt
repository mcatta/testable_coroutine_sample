package eu.marcocattaneo.samplecoroutines.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import eu.marcocattaneo.samplecoroutines.data.Repository
import eu.marcocattaneo.samplecoroutines.domain.GetRepositoriesUseCase
import eu.marcocattaneo.samplecoroutines.utils.LiveDataResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    lateinit var mainViewModel: MainViewModel

    val dispatcher = Dispatchers.Unconfined

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(dispatcher, dispatcher, getRepositoriesUseCase)
    }

    @Test
    fun testFetchRepositories_Positive() {
        coEvery { getRepositoriesUseCase.execute(any()) } returns listOf(
            Repository("a", "name"),
            Repository("b", "name"),
            Repository("c", "name")
        )

        mainViewModel.repositoriesLiveData.observeForever {}

        mainViewModel.fetchRepositories("mcatta")

        assert(mainViewModel.repositoriesLiveData.value != null)
        assert(mainViewModel.repositoriesLiveData.value!!.status == LiveDataResult.STATUS.SUCCESS)
    }

    @Test
    fun testFetchRepositories_Negative() {
        coEvery { getRepositoriesUseCase.execute(any()) } coAnswers { throw Exception("No network") }

        mainViewModel.repositoriesLiveData.observeForever {}

        mainViewModel.fetchRepositories("mcatta")

        assert(mainViewModel.repositoriesLiveData.value != null)
        assert(mainViewModel.repositoriesLiveData.value!!.status == LiveDataResult.STATUS.ERROR)
    }

}