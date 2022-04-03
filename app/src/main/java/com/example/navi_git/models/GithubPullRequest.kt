package com.example.navi_git.models

import android.content.Context
import com.example.navi_git.R
import com.example.navi_git.ui.adapter.IRecyclerViewItem
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class GithubPullRequest(
    @SerializedName("url") var url: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("node_id") var nodeId: String? = null,
    @SerializedName("html_url") var htmlUrl: String? = null,
    @SerializedName("diff_url") var diffUrl: String? = null,
    @SerializedName("patch_url") var patchUrl: String? = null,
    @SerializedName("issue_url") var issueUrl: String? = null,
    @SerializedName("number") var number: Int? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("locked") var locked: Boolean? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("user") var user: User? = User(),
    @SerializedName("body") var body: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("closed_at") var closedAt: String? = null,
    @SerializedName("merged_at") var mergedAt: String? = null,
    @SerializedName("merge_commit_sha") var mergeCommitSha: String? = null,
    @SerializedName("assignee") var assignee: String? = null,
    @SerializedName("assignees") var assignees: ArrayList<String> = arrayListOf(),
    @SerializedName("requested_teams") var requestedTeams: ArrayList<String> = arrayListOf(),
    @SerializedName("labels") var labels: ArrayList<String> = arrayListOf(),
    @SerializedName("milestone") var milestone: String? = null,
    @SerializedName("draft") var draft: Boolean? = null,
    @SerializedName("commits_url") var commitsUrl: String? = null,
    @SerializedName("review_comments_url") var reviewCommentsUrl: String? = null,
    @SerializedName("review_comment_url") var reviewCommentUrl: String? = null,
    @SerializedName("comments_url") var commentsUrl: String? = null,
    @SerializedName("statuses_url") var statusesUrl: String? = null,
    @SerializedName("author_association") var authorAssociation: String? = null,
    @SerializedName("auto_merge") var autoMerge: String? = null,
    @SerializedName("active_lock_reason") var activeLockReason: String? = null
) : IRecyclerViewItem {
    override fun getLayoutId(): Int {
        return R.layout.item_pull_request
    }

    fun getCreatedDate(context: Context): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("yyyy-MM-dd")
        val parsedDate = inputFormat.parse(createdAt?.substring(0, 10))
        return context.getString(R.string.created_at, outputFormat.format(parsedDate))
    }

    fun getPrClosedAt(context: Context): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("yyyy-MM-dd")
        val parsedDate = inputFormat.parse(closedAt?.substring(0, 10))
        return context.getString(R.string.closed_at, outputFormat.format(parsedDate))
    }
}

data class User(

    @SerializedName("login") var login: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("node_id") var nodeId: String? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null,
    @SerializedName("gravatar_id") var gravatarId: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("html_url") var htmlUrl: String? = null,
    @SerializedName("followers_url") var followersUrl: String? = null,
    @SerializedName("following_url") var followingUrl: String? = null,
    @SerializedName("gists_url") var gistsUrl: String? = null,
    @SerializedName("starred_url") var starredUrl: String? = null,
    @SerializedName("subscriptions_url") var subscriptionsUrl: String? = null,
    @SerializedName("organizations_url") var organizationsUrl: String? = null,
    @SerializedName("repos_url") var reposUrl: String? = null,
    @SerializedName("events_url") var eventsUrl: String? = null,
    @SerializedName("received_events_url") var receivedEventsUrl: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("site_admin") var siteAdmin: Boolean? = null

)