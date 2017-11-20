package com.googlejobapp.chethase.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.googlejobapp.chethase.R
import com.googlejobapp.chethase.data.SubredditViewModel
import kotlinx.android.synthetic.main.activity_subreddit.fab
import kotlinx.android.synthetic.main.activity_subreddit.toolbar

class SubredditActivity : AppCompatActivity() {

    private lateinit var viewModel: SubredditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subreddit)
        setSupportActionBar(toolbar)



        viewModel = ViewModelProviders.of(this).get(SubredditViewModel::class.java)

        viewModel.init()

//        viewModel.posts.observe(this, Observer { posts ->
//           if (posts == null) throw NullPointerException("SubredditViewModel.posts is null")
//            Timber.v("posts is ${posts.size}")
//        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
