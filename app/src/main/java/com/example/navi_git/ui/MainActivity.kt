package com.example.navi_git.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.navi_git.R
import com.example.navi_git.databinding.ActivityMainBinding
import com.example.navi_git.injection.GithubRepoComponent
import com.example.navi_git.injection.ViewModelProviderFactory
import com.example.navi_git.ui.adapter.GitRepoPagedListAdapter
import com.example.navi_git.ui.viewmodel.GithubRepoViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val prAdapter = GitRepoPagedListAdapter()
    lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var githubRepoViewModel: GithubRepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        GithubRepoComponent.init(this).inject(this)
        githubRepoViewModel =
            ViewModelProvider(this, viewModelProviderFactory)[GithubRepoViewModel::class.java]
        mainActivityBinding.lifecycleOwner = this
        mainActivityBinding.vm = githubRepoViewModel
        githubRepoViewModel.fetchData()
        mainActivityBinding.pullRequestRv.adapter = prAdapter
        githubRepoViewModel.prData.observe(this) {
            prAdapter.submitList(it)
        }
    }
}