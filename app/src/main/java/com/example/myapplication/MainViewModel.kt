package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel(private val animalSearchManager : AnimalSearchManager) : ViewModel() {
    val animal = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    private val job = SupervisorJob()
    private val mainScope = CoroutineScope(Dispatchers.Main + job)

    fun fetch() {
        mainScope.launch {
            try {
                animal.postValue(animalSearchManager.search())
            }
            catch (t : Throwable) {
                error.postValue(t.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

interface AnimalSearchManager {
    @Throws(Exception::class)
    suspend fun search() : String?
}
