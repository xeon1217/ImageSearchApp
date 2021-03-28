package com.example.imagesearchapp.ui.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 공통된 ViewModel들의 기능들을 모아둔 액티비티
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    protected fun showMessage(@StringRes stringResId: Int) {
        _message.value = getApplication<Application>().getString(stringResId)
    }
}