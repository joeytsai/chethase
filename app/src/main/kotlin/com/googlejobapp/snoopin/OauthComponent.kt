package com.googlejobapp.snoopin

import com.googlejobapp.chethase.ui.MainActivity
import dagger.Subcomponent

/**
 * Created by joey on 11/12/17.
 */
@OauthScope
@Subcomponent(modules = arrayOf(OkHttpModule::class, OauthModule::class, OauthRedditApiModule::class))
interface OauthComponent {
    fun inject(mainActivity: MainActivity)
}