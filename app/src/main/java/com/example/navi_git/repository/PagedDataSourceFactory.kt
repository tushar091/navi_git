package com.example.navi_git.repository

import androidx.paging.DataSource

class PagedDataSourceFactory<K, V>(val repository: BaseRepository<K, V>) :
    DataSource.Factory<K, V>() {
    override fun create(): DataSource<K, V> {
        return repository.createDataSource()
    }

    fun reloadData() {
        repository.reload()
    }

    fun onClear() {
        repository.onClear()
    }
}