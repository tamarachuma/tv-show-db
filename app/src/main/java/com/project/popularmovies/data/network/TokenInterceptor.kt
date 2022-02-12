package com.project.popularmovies.data.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", "ae20305f42c7fc7180a305e413152439")
            .build()
        val request: Request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}