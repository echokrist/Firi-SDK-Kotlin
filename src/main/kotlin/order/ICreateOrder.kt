package order

interface ICreateOrder {
    val market: String
    val type: String
    val price: Double
    val amount: Double
}
