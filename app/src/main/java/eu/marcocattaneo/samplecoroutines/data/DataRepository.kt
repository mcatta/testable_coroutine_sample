package eu.marcocattaneo.samplecoroutines.data

interface DataRepository {

    suspend fun getString() : String

    suspend fun duplicateString(value: String, count: Int = 1) : String

}