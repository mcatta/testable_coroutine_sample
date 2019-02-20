package eu.marcocattaneo.samplecoroutines.data

class DataImplementation : DataRepository {
    override suspend fun duplicateString(value: String, count: Int): String {
        Thread.sleep(5000)
        var res = ""

        for (i in 0..count) {
            res += value
        }

        return res
    }

    override suspend fun getString(): String {
        Thread.sleep(5000)

        return "Hello world"
    }

}