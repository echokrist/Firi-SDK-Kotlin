package transaction

import coin.Coin
import kotlinx.serialization.Serializable
import java.util.*


@Serializable
public data class Transaction(
    public val id: String,
    public val amount: Double,
    public val currency: Coin,
    public val type: String,
    public val date: Date,
    public val details: Map<String, Any>
)
