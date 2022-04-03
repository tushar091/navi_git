package com.example.navi_git.injection

import android.content.Context
import com.example.navi_git.models.GithubPullRequest
import com.example.navi_git.network.GithubPRNetworkRepository
import com.example.navi_git.network.GithubPRService
import com.example.navi_git.network.RetrofitInstance
import com.example.navi_git.repository.GitPullRequestListRepository
import com.example.navi_git.repository.PagedDataSourceFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module(includes = [MainVMModule::class])
class GithubRepoModule(private val context: Context) {

    @Provides
    fun provideGithubService(): GithubPRService {
        return RetrofitInstance.getRetrofit().create(GithubPRService::class.java)
    }

    @Provides
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    fun providePagedSourceFactory(githubPRNetworkRepository: GithubPRNetworkRepository): PagedDataSourceFactory<Int, GithubPullRequest> {
        return PagedDataSourceFactory(provideGitPullRequestListRepository(githubPRNetworkRepository))
    }

    @Provides
    fun provideGitPullRequestListRepository(githubPRNetworkRepository: GithubPRNetworkRepository): GitPullRequestListRepository {
        return GitPullRequestListRepository(githubPRNetworkRepository, providesGson())
    }

    @Provides
    fun providePRRepository(gitHubService: GithubPRService): GithubPRNetworkRepository {
        return GithubPRNetworkRepository(
            gitHubService
        )
    }
}