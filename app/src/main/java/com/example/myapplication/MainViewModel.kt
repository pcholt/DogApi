package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel(private val random : TheCatApi) : ViewModel() {
    val cat = MutableLiveData<CatApiCat>()

    private val job = SupervisorJob()
    private val mainScope = CoroutineScope(Dispatchers.Main + job)

    fun fetch2() {
        mainScope.launch {
            cat.value = random.search().firstOrNull()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
