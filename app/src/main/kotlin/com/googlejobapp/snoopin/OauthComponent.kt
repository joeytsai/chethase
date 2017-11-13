package com.googlejobapp.snoopin

import com.googlejobapp.chethase.ui.MainActivity
import dagger.Subcomponent

/**
 * Dagger subcomponent when the client becomes authenticated
 */
@OauthScope
@Subcomponent(modules = arrayOf(OauthModule::class, OauthApiModule::class))
interface OauthComponent {
    fun inject(mainActivity: MainActivity)
}