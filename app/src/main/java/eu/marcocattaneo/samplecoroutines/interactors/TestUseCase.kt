package eu.marcocattaneo.samplecoroutines.interactors

import eu.marcocattaneo.samplecoroutines.data.DataRepository
import eu.marcocattaneo.samplecoroutines.utils.UseCase

class TestUseCase(private val dataRepository: DataRepository): UseCase<String, Int>() {
    override suspend fun execute(params: Int): String {
        var data = dataRepository.getString()

        data += "ciao"

        return dataRepository.duplicateString(data)
    }

}