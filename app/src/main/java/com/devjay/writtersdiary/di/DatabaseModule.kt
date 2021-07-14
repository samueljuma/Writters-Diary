package com.devjay.writtersdiary.di

import android.content.Context
import com.devjay.writtersdiary.data.database.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase (@ApplicationContext context: Context): AppDatabase{
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideWriterDao(appDatabase: AppDatabase): WriterDao{
        return appDatabase.writerDao
    }

    @Provides
    fun provideWriterTaskDao(appDatabase: AppDatabase): WriterTaskDao{
        return appDatabase.writerTaskDao
    }

    @Provides
    fun provideClientDao(appDatabase: AppDatabase): ClientDao{
        return appDatabase.clientDao
    }

    @Provides
    fun provideClientTaskDao(appDatabase: AppDatabase): ClientTaskDao{
        return appDatabase.clientTaskDao
    }

}