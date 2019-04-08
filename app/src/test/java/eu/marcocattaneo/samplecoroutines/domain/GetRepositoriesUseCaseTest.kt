package eu.marcocattaneo.samplecoroutines.domain

import eu.marcocattaneo.samplecoroutines.data.ApiRepository
import eu.marcocattaneo.samplecoroutines.data.GithubRepository
import eu.marcocattaneo.samplecoroutines.data.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception
import java.lang.IllegalStateException

@RunWith(JUnit4::class)
class GetRepositoriesUseCaseTest {

    @MockK
    lateinit var githubRepository: GithubRepository

    lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.getRepositoriesUseCase = GetRepositoriesUseCase(githubRepository)
    }

    @Test
    fun testExecute_Positive() = runBlocking{
        coEvery { githubRepository.fetchRepositories("mcatta") } returns listOf(ApiRepository("a", "aa"), ApiRepository("b", "bb"))

        val list = getRepositoriesUseCase.execute("mcatta")

        assertNotNull(list)
        assert(list[0] is Repository)
        assertEquals("a", list[0].repositoryID)
        assertEquals("aa", list[0].repositoryName)
        assertEquals("b", list[1].repositoryID)
        assertEquals("bb", list[1].repositoryName)
    }

    @Test
    fun testExecute_Negative() = runBlocking {
        coEvery { githubRepository.fetchRepositories("mcatta") } throws IllegalStateException("err")
        try {
            val list = getRepositoriesUseCase.execute("mcatta")
        } catch (e: Exception) {
            assert(e is IllegalStateException)
        }
    }

}