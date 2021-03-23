package com.yogenp.mvidemo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.yogenp.mvidemo.model.Blog
import com.yogenp.mvidemo.repository.MainRepository
import com.yogenp.mvidemo.util.DataState
import dagger.assisted.Assisted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){

                is MainStateEvent.GetBlogEvents -> {
                    mainRepository.getBlog()
                        .onEach {
                            _dataState.value = it
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {
                }
            }
        }
    }
}

sealed class MainStateEvent {

    object GetBlogEvents : MainStateEvent()
    object None : MainStateEvent()
}