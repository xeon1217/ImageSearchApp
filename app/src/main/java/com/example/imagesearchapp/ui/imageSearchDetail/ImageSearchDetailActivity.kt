package com.example.imagesearchapp.ui.imageSearchDetail

import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.imagesearchapp.R
import com.example.imagesearchapp.data.repository.imageSearch.model.ImageSearchDocumentVO
import com.example.imagesearchapp.databinding.ActivitImageSearchDetailBinding
import com.example.imagesearchapp.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 카카오 이미지 검색 API를 이용한 이미지 검색 액티비티에서
 * 이미지를 선택했을 때 전체화면으로 보여주게 될 액티비티
 */
class ImageSearchDetailActivity : BaseActivity<ActivitImageSearchDetailBinding>() {

    private val imageSearchDetailViewModel: ImageSearchDetailViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.activit_image_search_detail
    }

    override fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setTitle(R.string.title_image_search_detail)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun bindingAfter() {
        initImageSearchDetailViewModel()
    }

    private fun initImageSearchDetailViewModel() {
        binding.imageSearchDetailViewModel = imageSearchDetailViewModel
        val owner = this

        imageSearchDetailViewModel.apply {

            (intent.getSerializableExtra("document") as ImageSearchDocumentVO).let { receivedDocument ->
                imageSearchDetailViewModel.putDocument(receivedDocument)
            }

            message.observe(owner, Observer { msg ->
                showToast(msg)
            })
        }
    }
}