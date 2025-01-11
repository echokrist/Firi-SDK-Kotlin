package order

public interface IOrder {
     public val id: Int
     public val market: String
     public val type: String
     public val price: Double
     public val amount: Double
     public val remaining: Double
     public val matched: Double
     public val cancelled: Double
     public val createdAt: String
}