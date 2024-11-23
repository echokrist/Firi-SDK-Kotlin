package transaction

import coin.Coin
import kotlinx.serialization.Serializable
import java.util.*


@Serializable
data class Transaction(
    val id: String,
    val amount: Double,
    val currency: Coin,
    val type: String,
    val date: Date,
    val details: Map<String, Any>
)
