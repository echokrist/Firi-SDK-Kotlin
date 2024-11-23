package order

interface IOrder {
     val id: Int
     val market: String
     val type: String
     val price: Double
     val amount: Double
     val remaining: Double
     val matched: Double
     val cancelled: Double
     val createdAt: String
}