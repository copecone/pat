package io.github.copecone.pat.util

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.github.monun.tap.util.GitHubSupport.generateUrlGitHubLatestRelease
import io.github.monun.tap.util.compareVersion
import io.github.monun.tap.util.httpRequest
import java.net.URL

@Suppress("unused")
object GitHubSupport {
    private const val KEY_TAG_NAME = "tag_name"
    private const val REQUEST_ACCEPT = "application/vnd.github.v3+json"

    fun isUpdateExists(
        owner: String,
        project: String,
        version: String
    ): Boolean {

        val latestReleaseURL = URL(generateUrlGitHubLatestRelease(owner, project))
        val releaseInfo = latestReleaseURL.httpRequest {
            requestMethod = "GET"
            addRequestProperty("Accept", REQUEST_ACCEPT)
            inputStream.bufferedReader().use { JsonParser.parseReader(it) as JsonObject }
        }

        var updateExists: Boolean
        releaseInfo[KEY_TAG_NAME].asString.also {
            updateExists = version.compareVersion(it) < 0
        }

        return updateExists
    }
}