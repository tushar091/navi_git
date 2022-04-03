package com.example.navi_git.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.navi_git.models.GithubPullRequest
import com.example.navi_git.models.State
import com.example.navi_git.network.GithubPRNetworkRepository
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.lang.Exception

class GitPullRequestListRepository(
    private val githubPRNetworkRepository: GithubPRNetworkRepository,
    private val gson: Gson
) : BaseRepository<Int, GithubPullRequest> {

    companion object {
        const val PAGE_INITIAL = 0
    }

    var state: MutableLiveData<State> = MutableLiveData()

    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    lateinit var dataSource: BaseDataSource<Int, GithubPullRequest>

    fun fetchInitial(
        params: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, GithubPullRequest>
    ) {
        scope.launch {
            state.postValue(State.LOADING)
            useNetworkData(PAGE_INITIAL, callback)
        }
    }

    private suspend fun useNetworkData(
        pageNo: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, GithubPullRequest>
    ) {
        try {
            val response = githubPRNetworkRepository.getClosedPullRequests(pageNo)
            if (response.isSuccessful) {
                state.postValue(State.DONE)
                response.body()?.let {
                    callback.onResult(it, null, pageNo + 1)
                } ?: state.postValue(State.ERROR)
            } else {
                state.postValue(State.ERROR)
            }
        } catch (e: Exception) {
            state.postValue(State.ERROR)
        }
    }

    fun fetchAfter(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, GithubPullRequest>
    ) {
        scope.launch {
            val response = githubPRNetworkRepository.getClosedPullRequests(params.key)
            if (response.isSuccessful) {
                response.body()?.let {
                    callback.onResult(it, params.key + 1)
                }
            }
        }
    }

    override fun createDataSource(): BaseDataSource<Int, GithubPullRequest> {
        dataSource = BaseDataSource(::fetchInitial, ::fetchAfter, scope)
        return dataSource
    }

    override fun reload() {
        dataSource.invalidate()
    }

    override fun onClear() {
        scope.cancel()
    }

    override fun getStateLiveData(): LiveData<State> {
        return state
    }
}