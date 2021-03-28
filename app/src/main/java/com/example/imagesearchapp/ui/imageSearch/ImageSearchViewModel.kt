package com.example.imagesearchapp.ui.imageSearch

import android.accounts.NetworkErrorException
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.imagesearchapp.R
import com.example.imagesearchapp.data.remote.imageSearch.request.ImageSearchRequest
import com.example.imagesearchapp.data.remote.imageSearch.request.ImageSearchRequestSort
import com.example.imagesearchapp.data.repository.error.RepositoryException
import com.example.imagesearchapp.data.repository.imageSearch.ImageSearchRepository
import com.example.imagesearchapp.data.repository.imageSearch.model.ImageSearchDocumentVO
import com.example.imagesearchapp.data.repository.imageSearch.model.ImageSearchMetaVO
import com.example.imagesearchapp.extension.*
import com.example.imagesearchapp.ui.base.BaseViewModel
import com.example.imagesearchapp.ui.common.liveData.NonNullMutableLiveData
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class ImageSearchViewModel(
    application: Application,
    private val imageSearchRepository: ImageSearchRepository
) : BaseViewModel(application) {

    //검색된 이미지 documnet들을 저장할 변수
    private val _documents = MutableLiveData<MutableList<ImageSearchDocumentVO>>()
    val documents: LiveData<List<ImageSearchDocumentVO>> =
        Transformations.map(_documents) { it?.toList() }

    //정렬 방식을 저장할 변수
    private val _imageSearchRequestSort = NonNullMutableLiveData(ImageSearchRequestSort.ACCURACY)
    val imageSearchRequestSort: LiveData<ImageSearchRequestSort> = _imageSearchRequestSort

    //검색어를 저장할 변수
    private var imageSearchKeyword: String? = null

    //검색된 페이지를 기억할 변수
    private var imageSearchMeta: ImageSearchMetaVO? = null

    //현재 페이지를 기억할 변수
    var imageSearchPage: Int = 1

    fun searchImage(query: String) {
        if(query.isNotEmpty()) {
            imageSearchKeyword = query
            imageSearchPage = 1

            searchImageToRepository(
                ImageSearchRequest(
                    query = query,
                    sort = _imageSearchRequestSort.value,
                    page = imageSearchPage,
                    size = 30,
                    isFirst = true
                )
            )
        }
    }

    fun moreImage() {
        imageSearchKeyword?.let { imageSearchKeyword ->
            imageSearchMeta?.let {
                if (!it.isEnd)
                    searchImageToRepository(
                        ImageSearchRequest(
                            query = imageSearchKeyword,
                            sort = _imageSearchRequestSort.value,
                            page = imageSearchPage,
                            size = 30,
                            isFirst = false
                        )
                    )
            }
        }
    }

    private fun searchImageToRepository(request: ImageSearchRequest) {
        imageSearchRepository.getImage(request)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                imageSearchPage += 1
            }
            .doOnError { }
            .subscribe({ response ->
                updateDocuments(request, response.documents)
                updateMetaData(response.meta)
            }, { throwable ->
                imageSearchExceptionHandling(throwable)
                Log.d("searchImage", "$throwable")
            }).addDisposable()
    }

    fun toggleImageSortType(imageSearchRequestSort: ImageSearchRequestSort) {
        _imageSearchRequestSort.value = imageSearchRequestSort
        imageSearchKeyword?.let { keyword ->
            searchImage(keyword)
        }
    }

    private fun updateDocuments(
        request: ImageSearchRequest,
        receivedDocuments: List<ImageSearchDocumentVO>
    ) {
        _documents.apply {
            if (request.isFirst) {
                clear()
            }
            addAll(receivedDocuments)
        }.also { documents ->
            if (documents.isEmpty()) {
                showMessage(R.string.success_image_search_empty_result)
            }
        }
    }

    private fun updateMetaData(receivedMeta: ImageSearchMetaVO) {
        imageSearchMeta = receivedMeta
        if(_documents.isNotEmpty() && receivedMeta.isEnd) {
            showMessage(R.string.success_image_search_last_page)
        }
    }

    private fun imageSearchExceptionHandling(throwable: Throwable) {
        when(throwable) {
            is RepositoryException.NetworkNotConnectingException -> {
                showMessage(R.string.error_network_not_connect)
            }
            else -> {
                showMessage(R.string.error_unknown)
                Log.d(TAG, throwable.message ?: "unknown error")
            }
        }
    }
}