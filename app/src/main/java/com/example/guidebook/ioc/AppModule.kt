package com.example.guidebook.ioc

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.guidebook.data.GuideDatabase
import com.example.guidebook.domain.service.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGuideDatabase(app: Application): GuideDatabase {
        return Room.databaseBuilder(
            app,
            GuideDatabase::class.java,
            GuideDatabase.DB_NAME
        ).build()
    }

    @Provides
    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory, gson: Gson): Retrofit {
        Log.i("Sco", "Retro")
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://guidebook.com/service/v2")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun getOKHttp(okHttpInterceptor: Interceptor): OkHttpClient{
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(okHttpInterceptor)
            .build()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}