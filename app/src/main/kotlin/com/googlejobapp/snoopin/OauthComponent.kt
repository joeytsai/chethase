package com.googlejobapp.snoopin

import com.googlejobapp.chethase.ui.MainActivity
import dagger.Subcomponent
import javax.inject.Scope

/**
 * Dagger subcomponent when the client becomes authenticated
 */
@OauthScope
@Subcomponent(modules = arrayOf(OauthModule::class, OauthApiModule::class))
interface OauthComponent {
    fun inject(mainActivity: MainActivity)
}

@Scope
annotation class OauthScope