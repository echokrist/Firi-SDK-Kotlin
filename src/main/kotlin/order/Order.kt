package order

import kotlinx.serialization.Serializable

@Serializable
public data class Order(
    public override val id: Int,
    public override val market: String,
    public override val type: String,
    public override val price: Double,
    public override val amount: Double,
    public override val remaining: Double,
    public override val matched: Double,
    public override val cancelled: Double,
    public override val createdAt: String
): IOrder

