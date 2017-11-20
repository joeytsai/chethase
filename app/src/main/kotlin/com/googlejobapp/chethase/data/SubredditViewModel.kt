package com.googlejobapp.chethase.data

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.googlejobapp.chethase.AppComponent
import com.googlejobapp.chethase.Chethase
import com.googlejobapp.chethase.room.Post
import com.googlejobapp.snoopin.OauthComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

/**
 * ViewModel for Subreddit operations
 */
class SubredditViewModel(app: Application) : AndroidViewModel(app) {
    @Inject lateinit var repo: SubredditRepo
    private val disposables = CompositeDisposable()

    lateinit var posts: LiveData<List<Post>>


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun init() {
        val sharedPrefs = daggerAppComponent().sharedPrefs()
        val redditApi = daggerAppComponent().redditApi()

        val deviceId = UUID.randomUUID().toString()
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
                            posts = repo.findPosts()
                        }, {
                            throw OnErrorNotImplementedException("ugh", it)
                        })
        )
    }
}

fun AndroidViewModel.daggerAppComponent(): AppComponent = getApplication<Chethase>().appComponent
fun AndroidViewModel.daggerOauthComponent(): OauthComponent = getApplication<Chethase>().oauthComponent
fun AndroidViewModel.initDaggerOauthComponent(token: String) = getApplication<Chethase>().initOauthComponent(token)
