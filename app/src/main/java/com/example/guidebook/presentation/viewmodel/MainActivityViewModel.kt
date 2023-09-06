package com.example.guidebook.presentation.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guidebook.domain.model.DataDB
import com.example.guidebook.domain.model.ResponseData
import com.example.guidebook.domain.usecase.AddDataDBUseCase
import com.example.guidebook.domain.usecase.GetDataUseCase
import com.example.guidebook.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val addDataUseCase: AddDataDBUseCase
): ViewModel() {
    private val _dataLiveData = MutableLiveData<Resource<ResponseData>>()
    val dataLiveData: LiveData<Resource<ResponseData>> = _dataLiveData

    fun getData() = viewModelScope.launch(Dispatchers.IO) {
        _dataLiveData.postValue(Resource.Loading())
        val resource = getResponseData(getDataUseCase.invoke())
        _dataLiveData.postValue(resource)
    }

    fun addDataDb(dataDB: DataDB) = viewModelScope.launch(Dispatchers.IO) {
        addDataUseCase.invoke(dataDB)
    }

    private fun getResponseData(response: Response<ResponseData>): Resource<ResponseData>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error("no data")
    }
}