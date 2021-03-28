package com.example.imagesearchapp.ui.imageSearch

import android.app.SearchManager
import android.content.Context
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchapp.R
import com.example.imagesearchapp.data.repository.imageSearch.model.ImageSearchDocumentVO
import com.example.imagesearchapp.databinding.ActivityImageSearchBinding
import com.example.imagesearchapp.ui.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_image_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

/**
 * 카카오 이미지 검색 API를 이용한 이미지 검색 액티비티
 */

class ImageSearchActivity : BaseActivity<ActivityImageSearchBinding>() {
    private val imageSearchViewModel: ImageSearchViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.activity_image_search
    }

    override fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun bindingAfter() {
        initImageSearchViewModel()
        initImageRecyclerView()
        initImageSearchView()
    }

    private fun initImageSearchView() {
        val textChange: PublishSubject<String> = PublishSubject.create()

        textChange
            .debounce(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { query -> imageSearchViewModel.searchImage(query) }
            .subscribe()

        binding.searchView.apply {
            setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        imageSearchViewModel.searchImage(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        textChange.onNext(newText)
                        return false
                    }
                }
            )
        }
    }

    private fun initImageSearchViewModel() {
        binding.imageSearchViewModel = imageSearchViewModel
        val owner = this

        imageSearchViewModel.apply {
            message.observe(owner, Observer { msg ->
                showToast(msg)
            })

            //검색 결과를 받게되면 검색바의 포커스와 검색어를 정리함
            documents.observe(owner, Observer {
                binding.searchView.apply {
                    clearFocus();
                    setQuery("", false);
                    isIconified = true;
                }
            })
        }
    }

    private fun initImageRecyclerView() {
        binding.recyclerView.let { recyclerView ->
            recyclerView.adapter = ImageSearchAdapter().apply {}
            recyclerView.layoutManager = GridLayoutManager(this, 3)
            recyclerView.setHasFixedSize(true)
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager
                    val lastVisibleItem =
                        (layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()

                    //화면에 보이는 리스트의 view의 position이 itemCount(리스트 총 갯수)와 같다면 리스트의 끝에 도달했다고 판단
                    if (lastVisibleItem + 1 == layoutManager.itemCount) {
                        imageSearchViewModel.moreImage()
                    }
                }
            })
        }
    }
}