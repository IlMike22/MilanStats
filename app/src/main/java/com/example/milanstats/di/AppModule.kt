package com.example.milanstats.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.milanstats.common.API_KEY
import com.example.milanstats.common.BASE_URI
import com.example.milanstats.common.KEY_VALUE
import com.example.milanstats.db.ICountryDao
import com.example.milanstats.db.ILeagueDao
import com.example.milanstats.detail.data.repository.DetailRepository
import com.example.milanstats.detail.domain.GetTeamDetailsBySeasonUseCase
import com.example.milanstats.detail.domain.repository.IDetailRepository
import com.example.milanstats.detail.domain.use_case.GetLeagueByCountryCodeUseCase
import com.example.milanstats.detail.domain.use_case.GetTableInformationUseCase
import com.example.milanstats.overview.data.IFootballApi
import com.example.milanstats.overview.data.repository.OverviewRepository
import com.example.milanstats.overview.domain.repository.IOverviewRepository
import com.example.milanstats.overview.domain.use_case.GetCountriesUseCase
import com.example.milanstats.overview.domain.use_case.GetLeaguesUseCase
import com.example.milanstats.overview.domain.use_case.GetTeamByNameUseCase
import com.example.milanstats.overview.domain.use_case.GetTeamStatisticsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val okHttpBuilder = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun provideApi(@ApplicationContext context: Context): IFootballApi {
        okHttpBuilder.addInterceptor(
            BasicAuthInterceptor(API_KEY)
        )
        okHttpBuilder.addInterceptor(ChuckerInterceptor(context))
        return Retrofit.Builder()
            .baseUrl(BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideOverviewRepository(
        api: IFootballApi,
        countryDao: ICountryDao,
        leagueDao: ILeagueDao
    ): IOverviewRepository {
        return OverviewRepository(api, countryDao, leagueDao)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(
        api: IFootballApi
    ): IDetailRepository {
        return DetailRepository(api)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(repo: IOverviewRepository): GetCountriesUseCase {
        return GetCountriesUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetLeagueUseCase(repo: IOverviewRepository): GetLeaguesUseCase {
        return GetLeaguesUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTeamsUseCase(repo: IOverviewRepository): GetTeamByNameUseCase {
        return GetTeamByNameUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTeamStatisticsUseCase(repo: IOverviewRepository): GetTeamStatisticsUseCase {
        return GetTeamStatisticsUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTeamDetailsBySeasonUseCase(repo: IOverviewRepository): GetTeamDetailsBySeasonUseCase {
        return GetTeamDetailsBySeasonUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTableInformationUseCase(repo: IDetailRepository): GetTableInformationUseCase {
        return GetTableInformationUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetLeagueByCountryCodeUseCase(repo: IDetailRepository): GetLeagueByCountryCodeUseCase {
        return GetLeagueByCountryCodeUseCase(repo)
    }
}

class BasicAuthInterceptor(apiKey: String) : Interceptor {
    private val apiKey = apiKey

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .header(KEY_VALUE, apiKey).build()
        return chain.proceed(authenticatedRequest)
    }
}