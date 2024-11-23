package api

import api.ApiVersion.values


enum class ApiVersion(val isLatest: Boolean = false) {
    v1,
    v2(true),
    latest;

    companion object {
        val latestVersion = values().first { it.isLatest }
    }
}