package com.example.imagesearchapp.ui.imageSearchDetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imagesearchapp.R
import com.example.imagesearchapp.data.repository.imageSearch.model.ImageSearchDocumentVO
import com.example.imagesearchapp.ui.base.BaseViewModel

class ImageSearchDetailViewModel(application: Application) : BaseViewModel(application) {
    private val _document = MutableLiveData<ImageSearchDocumentVO>()
    val document: LiveData<ImageSearchDocumentVO> = _document

    fun putDocument(receivedDocument: ImageSearchDocumentVO?) {
        receivedDocument?.let { document ->
            _document.value = document
        } ?: run {
            showMessage(R.string.error_unknown)
        }
    }
}