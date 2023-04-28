package com.jm.gutenberg.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: V
    protected val TAG = this.javaClass.name

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = createBinding()
        this.binding = binding
        setContentView(binding.root)
        hideSystemUI()

    }

    abstract fun createBinding(): V

    fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.peekDecorView()).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    fun showSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(
            window,
            window.peekDecorView()
        ).show(WindowInsetsCompat.Type.systemBars())
    }
}