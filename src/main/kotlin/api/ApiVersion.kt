package api

import api.ApiVersion.values


public enum class ApiVersion(public val isLatest: Boolean = false) {
    v1,
    v2(true),
    latest;

    public companion object {
        public val latestVersion: ApiVersion = entries.first { it.isLatest }
    }
}