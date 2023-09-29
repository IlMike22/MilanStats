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
import com.example.milanstats.goalscorer.data.repository.GoalScorerRepository
import com.example.milanstats.goalscorer.domain.repository.IGoalScorerRepository
import com.example.milanstats.goalscorer.domain.use_case.GetGoalScorerUseCase
import com.example.milanstats.home.data.IFootballApi
import com.example.milanstats.home.data.repository.HomeRepository
import com.example.milanstats.home.domain.repository.IHomeRepository
import com.example.milanstats.home.domain.use_case.GetCountriesUseCase
import com.example.milanstats.home.domain.use_case.GetLeaguesUseCase
import com.example.milanstats.home.domain.use_case.GetTeamByNameUseCase
import com.example.milanstats.home.domain.use_case.GetTeamStatisticsUseCase
import com.example.milanstats.injury.data.InjuryRepository
import com.example.milanstats.injury.domain.GetPlayerInjuryUseCase
import com.example.milanstats.injury.domain.IInjuryRepository
import com.example.milanstats.table.data.repository.TableRepository
import com.example.milanstats.table.domain.repository.ITableRepository
import com.example.milanstats.table.domain.usecase.GetTableInformationUseCase
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
    ): IHomeRepository {
        return HomeRepository(api, countryDao, leagueDao)
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
    fun provideInjuryRepository(
        api: IFootballApi
    ): IInjuryRepository {
        return InjuryRepository(api)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(repo: IHomeRepository): GetCountriesUseCase {
        return GetCountriesUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetLeagueUseCase(repo: IHomeRepository): GetLeaguesUseCase {
        return GetLeaguesUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTeamsUseCase(repo: IHomeRepository): GetTeamByNameUseCase {
        return GetTeamByNameUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTeamStatisticsUseCase(repo: IHomeRepository): GetTeamStatisticsUseCase {
        return GetTeamStatisticsUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTeamDetailsBySeasonUseCase(repo: IHomeRepository): GetTeamDetailsBySeasonUseCase {
        return GetTeamDetailsBySeasonUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTableInformationUseCase(repo: ITableRepository): GetTableInformationUseCase {
        return GetTableInformationUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetLeagueByCountryCodeUseCase(repo: IDetailRepository): GetLeagueByCountryCodeUseCase {
        return GetLeagueByCountryCodeUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetPlayerInjuriesUseCase(repo: IInjuryRepository): GetPlayerInjuryUseCase {
        return GetPlayerInjuryUseCase(repo)
    }

    /**
     * Table components
     */
    @Provides
    @Singleton
    fun provideTableRepository(
        api: IFootballApi
    ): ITableRepository {
        return TableRepository(api)
    }

    /**
     * GoalScorer components
     */
    @Provides
    @Singleton
    fun provideGoalScorerRepository(
        api: IFootballApi
    ): IGoalScorerRepository {
        return GoalScorerRepository(api)
    }
    @Provides
    @Singleton
    fun provideGetGoalScorerUseCase(repo: IGoalScorerRepository): GetGoalScorerUseCase {
        return GetGoalScorerUseCase(repo)
    }
}

/**
 * Basic stuff
 */

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