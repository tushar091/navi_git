package com.example.navi_git.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navi_git.ui.viewmodel.GithubRepoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(GithubRepoViewModel::class)
    internal abstract fun bindVm(gitRepoVM: GithubRepoViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}