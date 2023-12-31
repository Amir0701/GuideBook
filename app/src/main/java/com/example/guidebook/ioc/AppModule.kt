package com.example.guidebook.ioc

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.guidebook.data.GuideDatabase
import com.example.guidebook.data.repository.DataDBRepositoryImpl
import com.example.guidebook.data.repository.DataRepositoryImpl
import com.example.guidebook.domain.repository.DataDBRepository
import com.example.guidebook.domain.repository.DataRepository
import com.example.guidebook.domain.service.ApiService
import com.example.guidebook.domain.usecase.AddDataDBUseCase
import com.example.guidebook.domain.usecase.GetDataUseCase
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
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://guidebook.com/")
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
    fun getOKHttp(okHttpInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(okHttpInterceptor)
            .build()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun dataDbRepository(db: GuideDatabase): DataDBRepository{
        return DataDBRepositoryImpl(db)
    }

    @Singleton
    @Provides
    fun dataRepository(apiService: ApiService): DataRepository{
        return DataRepositoryImpl(apiService)
    }
    @Singleton
    @Provides
    fun provideAddDataDBUseCase(dataDBRepository: DataDBRepository): AddDataDBUseCase{
        return AddDataDBUseCase(dataDBRepository)
    }

    @Singleton
    @Provides
    fun provideGetDataUseCase(dataRepository: DataRepository): GetDataUseCase{
        return GetDataUseCase(dataRepository)
    }
}