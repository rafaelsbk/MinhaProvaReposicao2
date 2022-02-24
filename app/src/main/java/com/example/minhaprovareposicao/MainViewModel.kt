package com.example.minhaprovareposicao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel: ViewModel() {
    private var _text1 = MutableLiveData<String>()
    var text1:LiveData<String> = _text1

    private var _text2 = MutableLiveData<String>()
    var text2:LiveData<String> = _text2
}