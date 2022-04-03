package com.example.navi_git.network

import com.example.navi_git.models.GithubPullRequest
import retrofit2.Response
import javax.inject.Inject

class GithubPRNetworkRepository @Inject constructor(private val githubPrService: GithubPRService) {

    companion object{
        const val CLOSED_PR = "closed"
    }

    suspend fun getClosedPullRequests(pageNo: Int): Response<List<GithubPullRequest>> {
        return githubPrService.getPullRequests(status = CLOSED_PR,pageNo = pageNo)
    }
}