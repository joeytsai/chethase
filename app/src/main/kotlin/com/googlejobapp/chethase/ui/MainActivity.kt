package com.googlejobapp.chethase.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.googlejobapp.chethase.R
import com.googlejobapp.chethase.daggerAppComponent
import com.googlejobapp.chethase.daggerOauthComponent
import com.googlejobapp.chethase.initDaggerOauthComponent
import com.googlejobapp.snoopin.OauthRedditApi
import com.googlejobapp.snoopin.deviceId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import kotlinx.android.synthetic.main.activity_main.message
import kotlinx.android.synthetic.main.activity_main.navigation
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val disposables = CompositeDisposable()


    @Inject lateinit var oauthApi: OauthRedditApi

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
                                    Timber.d("AndroidDev $titles")
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
                                    Timber.d("Pics $titles")
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
                                    Timber.d("Subreddits: $titles")
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
        val sharedPrefs = daggerAppComponent().sharedPrefs()
        val redditApi = daggerAppComponent().redditApi()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val deviceId = deviceId()
        Timber.d("onCreate: Calling RedditAPI deviceId=$deviceId")
        sharedPrefs.deviceId = deviceId

        disposables.add(
                redditApi.accessToken(deviceId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Timber.d("success, got $it")
                            sharedPrefs.oauthToken = it.access_token
                            initDaggerOauthComponent(it.access_token)
                            daggerOauthComponent().inject(this)
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
