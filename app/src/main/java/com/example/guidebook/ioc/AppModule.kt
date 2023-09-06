package com.example.guidebook.ioc

import android.app.Application
import androidx.room.Room
import com.example.guidebook.data.GuideDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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


}