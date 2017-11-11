package com.googlejobapp.chethase.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.googlejobapp.chethase.R
import com.googlejobapp.chethase.SharedPrefs
import com.googlejobapp.snoopin.OauthRedditApi
import com.googlejobapp.snoopin.createOauthRedditApi
import com.googlejobapp.snoopin.deviceId
import com.googlejobapp.snoopin.redditApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import kotlinx.android.synthetic.main.activity_main.message
import kotlinx.android.synthetic.main.activity_main.navigation

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val disposables = CompositeDisposable()
    lateinit var oauthApi: OauthRedditApi


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)

                disposables.add(
                        oauthApi.hotAndroiddev()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    val list = it.data.children.map { it.data }
                                    val titles = list.map { it.title }.joinToString()
                                    Log.d(TAG, "AndroidDev $titles")
                                }, {
                                    throw OnErrorNotImplementedException("ugh", it)
                                })
                )

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)

                disposables.add(
                        oauthApi.hotPics()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    val list = it.data.children.map { it.data }
                                    val titles = list.map { it.title }.joinToString()
                                    Log.d(TAG, "Pics $titles")
                                }, {
                                    throw OnErrorNotImplementedException("ugh", it)
                                })
                )

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)

                disposables.add(
                        oauthApi.popularSubreddits()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    val list = it.data.children.map { it.data }
                                    val titles = list.map { it.title }.joinToString()
                                    Log.d(TAG, "Subreddits: $titles")
                                }, {
                                    throw OnErrorNotImplementedException("ugh", it)
                                })
                )

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPrefs = SharedPrefs(this)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val deviceId = deviceId()
        Log.d(TAG, "onCreate: Calling RedditAPI deviceId=$deviceId")
        sharedPrefs.deviceId = deviceId

        disposables.add(
                redditApi.accessToken(deviceId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Log.d(TAG, "success, got $it")
                            sharedPrefs.oauthToken = it.access_token
                            oauthApi = createOauthRedditApi(it.access_token)
                        }, {
                            throw OnErrorNotImplementedException("ugh", it)
                        })
        )

    }


    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

}
