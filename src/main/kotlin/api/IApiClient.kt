package api

import io.ktor.client.*
import io.ktor.http.*
import kotlinx.serialization.json.JsonArray

interface IApiClient {
    val apiVersion: ApiVersion
    val apiClientId: String
    val apiKey: String
    val apiSecretKey: String
    val apiClientDetails: ApiClientDetails

    fun generateHttpClient(): HttpClient
    fun generateHeaders(): Headers
    fun generateHeadersAuthorized(body: JsonArray? = null): Headers
    fun generateSignature(body: JsonArray?): String
    fun createHmac(data: String, key: String): String
    fun generateUrl(endpoint: String): String
    suspend fun executeGetRequest(endpoint: String): JsonArray
    suspend fun executeGetRequestAuthorized(endpoint: String): JsonArray
    suspend fun executePostRequest(endpoint: String, body: JsonArray): JsonArray
    suspend fun executeDeleteRequest(endpoint: String, body: JsonArray? = null): JsonArray
    suspend fun executePutRequest(endpoint: String, body: JsonArray): JsonArray
}