package order

import kotlinx.serialization.Serializable


@Serializable
public data class CreateOrder(
    override val market: String,
    override val type: String,
    override val price: Double,
    override val amount: Double
): ICreateOrder
