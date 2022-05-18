package com.example.joblogic.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.example.joblogic.domain.usecases.JobLogicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.example.joblogic.common.Failure
import com.example.joblogic.common.NoParams
import com.example.joblogic.common.SingleLiveEvent

import com.example.joblogic.presentation.list.ItemData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.joblogic.presentation.list.toItemData

@HiltViewModel
class MainViewModel @Inject constructor(
    private val jobLogicUseCase: JobLogicUseCase
) : ViewModel() {

    private val _listType = SingleLiveEvent<ListType>()
    val listType: SingleLiveEvent<ListType> = _listType

    private val _error = SingleLiveEvent<Failure>()
    val error: SingleLiveEvent<Failure> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _listData = SingleLiveEvent<List<ItemData>>()
    val listData: SingleLiveEvent<List<ItemData>> = _listData

    private val typeChangeObserver = Observer<ListType> {
        when (_listType.value) {
            ListType.CALL -> getCallList()
            ListType.BUY -> getBuyList()
            ListType.SELL -> getSellList()
        }
    }

    init {
        _listType.observeForever(typeChangeObserver)
    }

    private fun launchLoading(call: suspend () -> Unit) = viewModelScope.launch {
        _loading.value = true
        call()
        _loading.value = false
    }

    // usecases call-buy-sell list
    private fun getCallList() = launchLoading {
        jobLogicUseCase.getCallList(NoParams).fold(
            this::onFailure
        ) { data -> _listData.value = data.map { it.toItemData() } }
    }

    private fun getBuyList() = launchLoading {
        jobLogicUseCase.getBuyList(NoParams).fold(
            this::onFailure
        ) { data -> _listData.value = data.map { it.toItemData() } }
    }

    private fun getSellList() = launchLoading {
        jobLogicUseCase.getSellList(NoParams).fold(
            this::onFailure
        ) { data -> _listData.value = data.map { it.toItemData() } }
    }

    fun showCallList() {
        _listType.value = ListType.CALL
    }

    fun showBuyList() {
        _listType.value = ListType.BUY
    }

    fun showSellList() {
        _listType.value = ListType.SELL
    }

    private fun onFailure(failure: Failure) {
        _error.value = failure
    }

    override fun onCleared() {
        super.onCleared()
        _listType.removeObserver(typeChangeObserver)
    }
}

enum class ListType {
    CALL, BUY, SELL
}

