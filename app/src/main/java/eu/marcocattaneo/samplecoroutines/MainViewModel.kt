package eu.marcocattaneo.samplecoroutines

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import eu.marcocattaneo.samplecoroutines.data.DataImplementation
import eu.marcocattaneo.samplecoroutines.interactors.TestUseCase
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val job = Job()

    val progressLiveData = MutableLiveData<Boolean>()

    val toastLiveData = MutableLiveData<String>()

    val testUseCase = TestUseCase(DataImplementation())

    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val ioScope = CoroutineScope(Dispatchers.IO + job)

    fun execute() {

        uiScope.launch {
            progressLiveData.value = true

            val data = ioScope.async {
                return@async testUseCase.execute(2)
            }.await()

            log(data)

            progressLiveData.value = false
        }

    }

    private fun log(message: String) {
        this.toastLiveData.value = message
    }

    override fun onCleared() {
        this.job.cancel()
        super.onCleared()
    }

}