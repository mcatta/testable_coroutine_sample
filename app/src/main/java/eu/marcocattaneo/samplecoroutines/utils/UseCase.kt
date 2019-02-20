package eu.marcocattaneo.samplecoroutines.utils

abstract class UseCase<T, Params> {

    suspend abstract fun execute(params: Params) : T

}