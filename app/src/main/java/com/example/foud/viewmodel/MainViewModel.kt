package com.example.foud.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.example.foud.data.Repository
import com.example.foud.database.FavEntity
import com.example.foud.models.RecipesList
import com.example.foud.models.joke.Joke
import com.example.foud.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    //region ROOM DATABASE

    val getFavList: LiveData<List<FavEntity>> = repository.local.getFav().asLiveData()

    fun insertFav(favEntity: FavEntity)=
        viewModelScope.launch(IO) {
            repository.local.insertFav(favEntity)
    }

    fun deleteOneFav(favEntity: FavEntity){
        viewModelScope.launch(IO) {
            repository.local.deleteOneFav(favEntity)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(IO) {
            repository.local.deleteAll()
        }
    }

    // endregion

    //---------------------------------------------------------
    // region RETROFIT

    var joke: MutableLiveData<Joke> = MutableLiveData()
    fun getJoke(queries: Map<String,String>){
        viewModelScope.launch {
            joke.value = repository.remote.getJoke(queries)
        }
    }


    var recipesListResponse: MutableLiveData<NetworkResult<RecipesList>> = MutableLiveData()
    fun getRecipes(queries: Map<String,String>)=viewModelScope.launch{
        //recipesListResponse.value = repository.remote.getRecipesList(queries)
        recipesListResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()){
            try {
                val response = repository.remote.getRecipesList(queries)
                recipesListResponse.value = handleRecipesList(response)

                //val recipesList = recipesListResponse.value!!.data

            }catch (e: Exception){
                recipesListResponse.value = NetworkResult.Error("no recipes fond")
            }
        }else{
            recipesListResponse.value = NetworkResult.Error("no internet connection")
        }
    }

    private fun handleRecipesList(response: Response<RecipesList>): NetworkResult<RecipesList> {
        when{
            response.message().toString().contains("timeout")->{
                return NetworkResult.Error("Timeout")
            }
            response.code()==402 ->{
                return NetworkResult.Error("API Key is Limited")
            }
            response.body()!!.results.isNullOrEmpty()->{
                return NetworkResult.Error("Recipe not found")
            }
            response.isSuccessful->{
                val recipesList=response.body()
                return NetworkResult.Success(recipesList!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    // ------------------check for net connection
    private fun hasInternetConnection(): Boolean{

        val cm = getApplication<Application>()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetwork?:return false
        val capabilities = cm.getNetworkCapabilities(activeNetwork)?:return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
            else->false
        }
    }
    // endregion


}