# DogApi
Integrating Retrofit 2.6.0, coroutines, Android Architecture Components, Koin modules, Koin and coroutine testing, Koin android ViewModel injection, Mockito Kotlin, LiveData testing.

## Not just for dogs
This actually lets me try out all sorts of APIs for cat pictures too.
https://github.com/public-apis/public-apis#animals

## Modules in Koin
This is a demonstration application for all the technologies we are going to be using in the later versions of our commercial application. The testing framework will make unit tests much faster, because they can be run from the development machine without deploying APK to a simulator. The tests in this project run in less than two seconds.

## Android Architecture Components
The viewmodel in the project is subject to two unit tests:

### 1
 - GIVEN a mocked CatApi retrofit implementation, injected into the viewmodel by Koin
 - WHEN we call the viewmodel with the method called by clicking the FAB on the UI
 - THEN we wait for the LiveData component to return, with the data returned by the mocked CatApi
 
### 2
 - GIVEN a mocked CatApi retrofit implementation, injected into the viewmodel by Koin, *which always throws an Exception*
 - WHEN we call the viewmodel with the method called by clicking the FAB on the UI
 - THEN we wait for two LiveData components
    - An error message in `.error`
    - No data returned in the `.message` live data 
