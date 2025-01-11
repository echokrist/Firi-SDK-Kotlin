package api
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
public data class ApiClientDetails(
    val version: ApiVersion,
    val clientId: String,
    val apiKey: String,
    val secretKey: String,
    val url: Url,
)
