package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel(private val animalSearchManager : AnimalSearchManager) : ViewModel() {
    val animal = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    private val job = SupervisorJob()
    private val mainScope = CoroutineScope(Dispatchers.Main + job)

    fun fetch() {
        mainScope.launch {
            try {
                animal.value = animalSearchManager.search()
            }
            catch (t : Throwable) {
                animal.value = null
                error.value = t.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

interface AnimalSearchManager {
    suspend fun search() : String?
}
