package api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.*
import java.io.IOException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class ApiClient(
    override val apiVersion: ApiVersion,
    override val apiClientId: String,
    override val apiKey: String,
    override val apiSecretKey: String

) : IApiClient {

    override var apiClientDetails: ApiClientDetails

    init {
        val apiUrl = Url("https://api.firi.com/$apiVersion/")
        this.apiClientDetails = ApiClientDetails(
            version = apiVersion,
            clientId = apiClientId,
            apiKey = apiKey,
            secretKey = apiSecretKey,
            url = apiUrl,
        )
    }

    override fun generateHttpClient(): HttpClient {
        return HttpClient(CIO) {
            engine {
                requestTimeout = 15000 // Request timeout in milliseconds
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 10000 // Connection timeout in milliseconds
                socketTimeoutMillis = 10000 // Socket timeout in milliseconds
            }
        }
    }

    override fun generateHeaders(): Headers {
        return Headers.build {
            append("Content-Type", "application/json")
            append("Accept", "application/json")
        }
    }

    override fun generateHeadersAuthorized(body: JsonArray?): Headers {
        val signature: String = this.generateSignature(body)

        return Headers.build {
            append("Content-Type", "application/json")
            append("Accept", "application/json")
            append("firi-access-key", value = apiClientDetails.apiKey.toString())
            append("firi-user-signature", value = signature)
            append("firi-user-clientid", value = apiClientDetails.clientId.toString())
        }
    }

    override fun generateSignature(body: JsonArray?): String {
        val secretKey = this.apiClientDetails.secretKey
        val time = System.currentTimeMillis() / 1000
        val validity = 2000L

        var jsonString = ""

        if (body?.isEmpty() == false) {
            // Add timestamp and validity to the body
            val updatedBody = buildJsonArray {
                body.forEach { element -> add(element) }
                add(JsonPrimitive(time))
                add(JsonPrimitive(validity))
            }

            // Serialize the updated body to a JSON string
            jsonString = Json.encodeToString(JsonArray.serializer(), updatedBody)
        } else {
            val updatedBody = buildJsonArray {
                add(JsonPrimitive(time))
                add(JsonPrimitive(validity))
            }
            // Serialize the body to a JSON string
            jsonString = Json.encodeToString(JsonArray.serializer(), updatedBody)
        }

        // Compute the HMAC over the JSON string
        return createHmac(jsonString, secretKey)
    }

    override fun createHmac(data: String, key: String): String {
        val sha256Hmac = Mac.getInstance("HmacSHA256")
        val secretKeySpec = SecretKeySpec(key.toByteArray(), "HmacSHA256")
        sha256Hmac.init(secretKeySpec)
        val hashBytes = sha256Hmac.doFinal(data.toByteArray())

        // Convert the hash bytes to a hexadecimal string
        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    override fun generateUrl(endpoint: String): String {
        return apiClientDetails.url.toString() + endpoint
    }

    override suspend fun executeGetRequest(endpoint: String): JsonArray {

        val client = generateHttpClient()

        try {
            val request: HttpResponse = client.get(generateUrl(endpoint)) {
                method = HttpMethod.Get
                headers {
                    appendAll(generateHeaders())
                }
            }

            val response: JsonElement = Json.decodeFromString(request.body())

            if (request.status.value != 200) {
                throw Exception("Request failed with status code ${request.status.value} and message $response")
            }

            if (response is JsonNull) {
                throw Exception("Request to endpoint $endpoint resulted in an empty response, no data found.")
            }

            val prettyResponse = Json { prettyPrint = true }.encodeToString(JsonElement.serializer(), response)

            return when (response) {
                is JsonArray -> Json.decodeFromString(prettyResponse)
                is JsonObject -> buildJsonArray { add(Json.decodeFromString(prettyResponse)) }
                else -> throw Exception("Unexpected JSON element type: ${response::class.simpleName}")
            }

        } catch (e: HttpRequestTimeoutException) {
            throw Exception("Request to endpoint $endpoint timed out.")
        } catch (e: ClientRequestException) {
            throw Exception("Client request error: ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            throw Exception("Server response error: ${e.response.status.description}")
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        } catch (e: Exception) {
            throw Exception("An unexpected error occurred: ${e.message}")
        } finally {
            client.close()
        }
    }

    override suspend fun executeGetRequestAuthorized(endpoint: String): JsonArray {
        val client = generateHttpClient()

        try {
            val request: HttpResponse = client.get(generateUrl(endpoint)) {
                method = HttpMethod.Get
                headers {
                    appendAll(generateHeadersAuthorized())
                }
            }

            val response: JsonElement = Json.decodeFromString(request.body())

            if (request.status.value != 200) {
                throw Exception("Request failed with status code ${request.status.value} and message $response")
            }

            if (response is JsonNull) {
                throw Exception("Request to endpoint $endpoint resulted in an empty response, no data found.")
            }

            val prettyResponse = Json { prettyPrint = true }.encodeToString(JsonElement.serializer(), response)

            return when (response) {
                is JsonArray -> Json.decodeFromString(prettyResponse)
                is JsonObject -> buildJsonArray { add(Json.decodeFromString(prettyResponse)) }
                else -> throw Exception("Unexpected JSON element type: ${response::class.simpleName}")
            }
        } catch (e: HttpRequestTimeoutException) {
            throw Exception("Request to endpoint $endpoint timed out.")
        } catch (e: ClientRequestException) {
            throw Exception("Client request error: ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            throw Exception("Server response error: ${e.response.status.description}")
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        } catch (e: Exception) {
            throw Exception("An unexpected error occurred: ${e.message}")
        } finally {
            client.close()
        }
    }

    override suspend fun executePostRequest(endpoint: String, body: JsonArray): JsonArray {
        val client = generateHttpClient()

        try {
            val request: HttpResponse = client.post(generateUrl(endpoint)) {
                method = HttpMethod.Post
                headers {
                    appendAll(generateHeadersAuthorized(body))
                }
                setBody(body)
            }

            val response: JsonElement = Json.decodeFromString(request.body())

            if (request.status.value != 201) {
                throw Exception("Request to endpoint $endpoint, with body $body, failed with status code ${request.status.value} and message $response")
            }

            val prettyResponse = Json { prettyPrint = true }.encodeToString(JsonElement.serializer(), response)

            return when (response) {
                is JsonArray -> Json.decodeFromString(prettyResponse)
                is JsonObject -> buildJsonArray { add(Json.decodeFromString(prettyResponse)) }
                else -> throw Exception("Unexpected JSON element type: ${response::class.simpleName}")
            }
        } catch (e: HttpRequestTimeoutException) {
            throw Exception("Request to endpoint $endpoint timed out.")
        } catch (e: ClientRequestException) {
            throw Exception("Client request error: ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            throw Exception("Server response error: ${e.response.status.description}")
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        } catch (e: Exception) {
            throw Exception("An unexpected error occurred: ${e.message}")
        } finally {
            client.close()
        }
    }

    override suspend fun executeDeleteRequest(endpoint: String, body: JsonArray?): JsonArray {

        val client = generateHttpClient()

        try {
            val request: HttpResponse = client.request(generateUrl(endpoint)) {
                method = HttpMethod.Delete
                headers {
                    appendAll(generateHeadersAuthorized(body))
                }
                setBody(body)
            }

            val response: JsonElement = Json.decodeFromString(request.body())

            if (request.status.value != 201) {
                throw Exception("Request to endpoint $endpoint, with body $body, failed with status code ${request.status.value} and message $response")
            }

            val prettyResponse = Json { prettyPrint = true }.encodeToString(JsonElement.serializer(), response)

            return when (response) {
                is JsonArray -> Json.decodeFromString(prettyResponse)
                is JsonObject -> buildJsonArray { add(Json.decodeFromString(prettyResponse)) }
                else -> throw Exception("Unexpected JSON element type: ${response::class.simpleName}")
            }
        } catch (e: HttpRequestTimeoutException) {
            throw Exception("Request to endpoint $endpoint timed out.")
        } catch (e: ClientRequestException) {
            throw Exception("Client request error: ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            throw Exception("Server response error: ${e.response.status.description}")
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        } catch (e: Exception) {
            throw Exception("An unexpected error occurred: ${e.message}")
        } finally {
            client.close()
        }
    }


    override suspend fun executePutRequest(endpoint: String, body: JsonArray): JsonArray {
        val client = generateHttpClient()

        try {
            val request: HttpResponse = client.put(generateUrl(endpoint)) {
                method = HttpMethod.Put
                headers {
                    appendAll(generateHeadersAuthorized(body))
                }
                setBody(body)
            }

            val response: JsonElement = Json.decodeFromString(request.body())

            if (request.status.value != 201) {
                throw Exception("Request to endpoint $endpoint, with body $body, failed with status code ${request.status.value} and message $response")
            }

            val prettyResponse = Json { prettyPrint = true }.encodeToString(JsonElement.serializer(), response)

            return when (response) {
                is JsonArray -> Json.decodeFromString(prettyResponse)
                is JsonObject -> buildJsonArray { add(Json.decodeFromString(prettyResponse)) }
                else -> throw Exception("Unexpected JSON element type: ${response::class.simpleName}")
            }
        } catch (e: HttpRequestTimeoutException) {
            throw Exception("Request to endpoint $endpoint timed out.")
        } catch (e: ClientRequestException) {
            throw Exception("Client request error: ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            throw Exception("Server response error: ${e.response.status.description}")
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        } catch (e: Exception) {
            throw Exception("An unexpected error occurred: ${e.message}")
        } finally {
            client.close()
        }
    }

    sealed class ApiResponseDecoded {
        data class JsonArray(val data: JsonArray) : ApiResponseDecoded()
        data class JsonObject(val error: JsonObject) : ApiResponseDecoded()
    }
}
