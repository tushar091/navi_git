package com.example.navi_git.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.navi_git.models.GithubPullRequest

class GitRepoPagedListAdapter : BasePagedListAdapter<GithubPullRequest>(BreedDiffUtil) {
    object BreedDiffUtil : DiffUtil.ItemCallback<GithubPullRequest>() {
        override fun areItemsTheSame(oldItem: GithubPullRequest, newItem: GithubPullRequest): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: GithubPullRequest, newItem: GithubPullRequest): Boolean {
            return oldItem == newItem
        }
    }


    override fun registerItemClickListener(viewHolder: ViewHolder) {
        //do nothing now
    }
}