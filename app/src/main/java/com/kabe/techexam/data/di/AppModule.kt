package com.kabe.techexam.data.di

import android.content.Context
import com.kabe.techexam.BuildConfig.DEBUG
import com.kabe.techexam.constant.AppConstants
import com.kabe.techexam.data.base.RetrofitBuilder
import com.kabe.techexam.network.RandomPersonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TIMEOUT = 20000 //20 seconds

    // Retrofit Builder
    @Provides
    @Singleton
    fun provideClient(@ApplicationContext appContext: Context): OkHttpClient {
        return OkHttpClient.Builder().apply {

            connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)

            addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            })

            addInterceptor(RetrofitBuilder.NoInternetInterceptor(appContext))

        }.build()
    }

    @Provides
    @Singleton
    fun createRetrofitInstance(client: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(AppConstants.BASE_URL)
        client(client)
    }.build()

    @Provides
    @Singleton
    fun provideTriviaService(retrofit: Retrofit): RandomPersonService {
        return retrofit.create(RandomPersonService::class.java)
    }
}