package order

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    override val id: Int,
    override val market: String,
    override val type: String,
    override val price: Double,
    override val amount: Double,
    override val remaining: Double,
    override val matched: Double,
    override val cancelled: Double,
    override val createdAt: String
): IOrder

