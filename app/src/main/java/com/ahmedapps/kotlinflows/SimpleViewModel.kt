package com.ahmedapps.kotlinflows

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * @author Ahmed Guedmioui
 */
class SimpleViewModel : ViewModel() {

    private val _textState = MutableStateFlow("")
    val textState = _textState.asStateFlow()

    fun changeText(text: String) {
        _textState.update { text }
    }

}










