package com.example.navi_git.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.navi_git.models.GithubPullRequest
import com.example.navi_git.models.State
import com.example.navi_git.repository.PagedDataSourceFactory
import javax.inject.Inject

class GithubRepoViewModel @Inject constructor(private val githubRepoFactory: PagedDataSourceFactory<Int, GithubPullRequest>) : ViewModel() {

    companion object {
        const val PAGE_SIZE = 15
    }

    lateinit var prData: LiveData<PagedList<GithubPullRequest>>

    fun fetchData() {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE).build()
        prData = LivePagedListBuilder(githubRepoFactory, pagedListConfig).build()
    }

    fun reload() {
        githubRepoFactory.reloadData()
    }

    fun getState(): LiveData<State> = githubRepoFactory.repository.getStateLiveData()

    override fun onCleared() {
        super.onCleared()
        githubRepoFactory.onClear()
    }
}