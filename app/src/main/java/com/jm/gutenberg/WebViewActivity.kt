package com.jm.gutenberg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.jm.gutenberg.base.BaseActivity
import com.jm.gutenberg.databinding.ActivityWebViewBinding

class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {
    override fun createBinding(): ActivityWebViewBinding = ActivityWebViewBinding.inflate(layoutInflater);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.openUrl.setWebViewClient(WebViewClient())
        binding.openUrl.loadUrl(intent.getStringExtra("page").toString())
    }

}