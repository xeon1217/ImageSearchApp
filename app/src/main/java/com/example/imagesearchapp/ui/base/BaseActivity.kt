package com.example.imagesearchapp.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * 공통된 Activity들의 기능들을 모아둔 액티비티
 */
abstract class BaseActivity<B: ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initToolbar()
        bindingAfter()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView<B>(this, getLayoutId()).apply {
            lifecycleOwner = this@BaseActivity
        }
    }

    //viewModel에서의 메시지 이벤트를 수신하여 toast로 표현함
    protected fun showToast(message: String?) {
        message?.let { msg ->
            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
        }
    }

    protected abstract fun initToolbar()

    protected abstract fun bindingAfter()

    protected abstract fun getLayoutId(): Int
}