package com.codart.ipoteka.di.module

import android.content.Context
import com.codart.ipoteka.data.local.AppDatabase
import com.codart.ipoteka.data.local.TokenDao
import com.codart.ipoteka.data.local.UserTokenDao
import com.codart.ipoteka.data.remote.*
import com.codart.ipoteka.data.repository.*
import com.codart.ipoteka.utils.Values
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
            .baseUrl(Values.PATH_BASE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(authService: AuthService)= AuthRemoteDataSource(authService)

    @Provides
    fun provideInputService(retrofit: Retrofit): InputService = retrofit.create(InputService::class.java)

    @Singleton
    @Provides
    fun provideInputRemoteDataSource(inputService: InputService)= InputRemoteDataSource(inputService)

    @Provides
    fun provideUserDataService(retrofit: Retrofit): UserDataService = retrofit.create(UserDataService::class.java)

    @Singleton
    @Provides
    fun provideUserDataRemoteDataSource(userDataService: UserDataService)= UserDataRemoteDataSource(userDataService)

    @Provides
    fun provideFirebaseTokenService(retrofit: Retrofit): FirebaseTokenService = retrofit.create(FirebaseTokenService::class.java)

    @Singleton
    @Provides
    fun provideFirebaseTokenRemoteDataSource(firebaseTokenService: FirebaseTokenService)= FirebaseTokenRemoteDataSource(firebaseTokenService)

    @Singleton
    @Provides
    fun provideTokenDao(db: AppDatabase) = db.tokenDao()

    @Singleton
    @Provides
    fun provideUserTokenDao(db: AppDatabase) = db.userTokenDao()

    @Singleton
    @Provides
    fun provideAuthRepository(remoteDataSource: AuthRemoteDataSource,
                          localDataSource: TokenDao, userTokenDao: UserTokenDao) =
            AuthRepository(remoteDataSource, localDataSource, userTokenDao)

    @Singleton
    @Provides
    fun provideInputRepository(remoteDataSource: InputRemoteDataSource) =
            InputRepository(remoteDataSource)
    @Singleton
    @Provides
    fun provideUserDataRepository(remoteDataSource: UserDataRemoteDataSource) =
            UserDataRepository(remoteDataSource)
    @Singleton
    @Provides
    fun provideFirebaseRepository(remoteDataSource: FirebaseTokenRemoteDataSource) =
        FirebaseRepository(remoteDataSource)
    @Singleton
    @Provides
    fun provideAppRepository(authRepository: AuthRepository, inputRepository: InputRepository, useerDataRepository: UserDataRepository, firebaseRepository: FirebaseRepository) =
            AppRepository(authRepository,inputRepository, useerDataRepository,firebaseRepository)



}