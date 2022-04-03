package com.example.navi_git.network

import com.example.navi_git.models.GithubPullRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubPRService {

    companion object {
        const val BREED_ENDPOINT = "android/wear-os-samples/pulls"
        const val QUERY_STATUS = "state"
        const val QUERY_PAGE_NO = "page"
        const val QUERY_PAGE_LIMIT = "limit"
    }

    @GET(BREED_ENDPOINT)
    suspend fun getPullRequests(
        @Query(QUERY_STATUS) status: String,
        @Query(QUERY_PAGE_NO) pageNo: Int
    ): Response<List<GithubPullRequest>>
}