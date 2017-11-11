package com.googlejobapp.snoopin

import okhttp3.Authenticator
import okhttp3.Credentials
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber


// True if we've failed 3 times
// https://github.com/square/okhttp/wiki/Recipes
fun Response.giveUp(): Boolean {
    var responseCount = 0
    var res: Response? = this
    while (res != null) {
        responseCount += 1
        res = res.priorResponse()
    }
    return responseCount >= 3
}

class ApplicationOnlyOAuth : Authenticator {
    override fun authenticate(route: Route, response: Response): Request? {
        if (response.sameCredentials()) {
            Timber.d("sameCredentials returns true")
            return null
        }
        if (response.giveUp()) {
            Timber.d("giveUp returns true")
            return null
        }
        return response.request().newBuilder()
                .header(AuthorizationHeader, credentials)
                .build()
    }
}

const val AuthorizationHeader = "Authorization"

fun Response.sameCredentials() = credentials == request().header(AuthorizationHeader)


// https://www.reddit.com/prefs/apps
const val CLIENT_ID = "4Gg_1JUp5dT70g"
// Android apps are Reddit's "Installed App" type, which do not have client secrets
const val CLIENT_SECRET = ""

val credentials: String = Credentials.basic(CLIENT_ID, CLIENT_SECRET)

