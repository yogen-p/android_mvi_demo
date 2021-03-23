package com.yogenp.mvidemo.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.yogenp.mvidemo.R
import com.yogenp.mvidemo.model.Blog
import com.yogenp.mvidemo.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer {
            when(it){
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitle(it.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(it.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?){
        if (message.isNullOrEmpty()){
            txtView.text = message
        } else{
            txtView.text = "Unknown Error!"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progressBar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitle(blogs: List<Blog>){
        val sb = StringBuilder()
        for(blog in blogs){
            sb.append(blog.title + "\n")
        }
        txtView.text = sb.toString()
    }
}