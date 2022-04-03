package com.example.navi_git.repository

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BaseDataSource<K, V>(
    private val loadInitialData: (params: LoadInitialParams<K>, callback: LoadInitialCallback<K, V>) -> Unit,
    private val loadAfterData: (params: LoadParams<K>, callback: LoadCallback<K, V>) -> Unit,
    private val scope: CoroutineScope
) : PageKeyedDataSource<K, V>() {

    override fun loadInitial(params: LoadInitialParams<K>, callback: LoadInitialCallback<K, V>) {
        scope.launch { loadInitialData(params, callback) }
    }

    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<K, V>) {
        //do nothing now
    }

    override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<K, V>) {
        scope.launch { loadAfterData(params, callback) }
    }
}