package api

import io.ktor.client.*
import io.ktor.http.*
import kotlinx.serialization.json.JsonArray

public interface IApiClient {
    public val apiVersion: ApiVersion
    public val apiClientId: String
    public val apiKey: String
    public val apiSecretKey: String
    public val apiClientDetails: ApiClientDetails

    public fun generateHttpClient(): HttpClient
    public fun generateHeaders(): Headers
    public fun generateHeadersAuthorized(body: JsonArray? = null): Headers
    public fun generateSignature(body: JsonArray?): String
    public fun createHmac(data: String, key: String): String
    public fun generateUrl(endpoint: String): String
    public suspend fun executeGetRequest(endpoint: String): JsonArray
    public suspend fun executeGetRequestAuthorized(endpoint: String): JsonArray
    public suspend fun executePostRequest(endpoint: String, body: JsonArray): JsonArray
    public suspend fun executeDeleteRequest(endpoint: String, body: JsonArray? = null): JsonArray
    public suspend fun executePutRequest(endpoint: String, body: JsonArray): JsonArray
}