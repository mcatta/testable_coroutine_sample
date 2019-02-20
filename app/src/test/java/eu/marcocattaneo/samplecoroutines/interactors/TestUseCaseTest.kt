package eu.marcocattaneo.samplecoroutines.interactors

import eu.marcocattaneo.samplecoroutines.data.DataRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestUseCaseTest {

    lateinit var useCase: TestUseCase

    @MockK
    lateinit var repository: DataRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        this.useCase = TestUseCase(repository)
    }

    @Test
    fun `Test execution positive`() = runBlocking {

        val result = "Hello worldciaoHello World"

        coEvery { repository.getString() }.returns("Hello world")
        coEvery { repository.duplicateString(any(), 1) }.returns(result)


        val r = useCase.execute(1)
        assertEquals(result, r)
    }

}