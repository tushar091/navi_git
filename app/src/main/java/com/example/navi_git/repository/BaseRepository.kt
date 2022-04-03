package com.example.navi_git.repository

import androidx.lifecycle.LiveData
import com.example.navi_git.models.State

interface BaseRepository<K, V> {
    fun createDataSource(): BaseDataSource<K, V>

    fun reload()

    fun onClear()

    fun getStateLiveData(): LiveData<State>
}