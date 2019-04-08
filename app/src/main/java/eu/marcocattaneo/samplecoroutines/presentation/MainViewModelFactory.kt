package eu.marcocattaneo.samplecoroutines.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import eu.marcocattaneo.samplecoroutines.domain.GetRepositoriesUseCase
import kotlinx.coroutines.CoroutineDispatcher

class MainViewModelFactory constructor(
    private val mainDispather: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(mainDispather, ioDispatcher, getRepositoriesUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}