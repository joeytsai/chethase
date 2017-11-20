package com.googlejobapp.snoopin

import com.googlejobapp.chethase.data.SubredditModule
import com.googlejobapp.chethase.data.SubredditViewModel
import com.googlejobapp.chethase.ui.MainActivity
import dagger.Subcomponent
import javax.inject.Scope

/**
 * Dagger subcomponent when the client becomes authenticated
 */
@OauthScope
@Subcomponent(modules = arrayOf(OauthModule::class, OauthApiModule::class, SubredditModule::class))
interface OauthComponent {
    fun inject(activity: MainActivity)
    fun inject(viewModel: SubredditViewModel)
}

@Scope
annotation class OauthScope