package api

import io.ktor.http.*
import kotlinx.serialization.Serializable
import java.util.*
import javax.crypto.Mac
import javax.crypto.SecretKey

@Serializable
data class ApiConnectionDetails(
    val secretKey: SecretKey,
    val timeStamp: Date,
    val validity: Int,
    val body: String,
    val headers: Headers,
    val signature: Mac,
)

