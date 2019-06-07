package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.net.Dog
import com.example.myapplication.net.RandomDog
import kotlinx.coroutines.*

class MainViewModel(val random : RandomDog) : ViewModel() {
    val dog = MutableLiveData<Dog>()

    val job = SupervisorJob()
    val mainScope = CoroutineScope(Dispatchers.Main+ job)

    fun fetch2() {
        mainScope.launch {
            dog.value = random.randomDog()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
