package com.example.navi_git.injection

import android.content.Context
import com.example.navi_git.ui.MainActivity
import dagger.Component

@Component(modules = [GithubRepoModule::class])
interface GithubRepoComponent {
    fun inject(mainActivity: MainActivity)

    companion object{
        fun init(context: Context):GithubRepoComponent{
            return DaggerGithubRepoComponent.builder().githubRepoModule(GithubRepoModule(context)).build()
        }
    }
}