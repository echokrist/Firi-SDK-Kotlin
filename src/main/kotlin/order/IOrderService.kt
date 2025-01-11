package order

import kotlinx.serialization.json.JsonArray

public interface IOrderService {
    public suspend fun getOrders(): JsonArray
    public suspend fun createOrder(market: String, type: String, price: Double, amount: Double): JsonArray
    public suspend fun deleteAllOrders(): JsonArray
    public suspend fun getAllActiveOrdersByMarket(market: String, count: Int? = 100): JsonArray
    public suspend fun getAllFilledClosedOrdersByMarket(market: String, count: Int? = 100): JsonArray
    public suspend fun getAllFilledClosedOrders(count: Int? = 100): JsonArray
    public suspend fun getOrderById(orderId: Int): JsonArray
    public suspend fun deleteOrdersByMarketOrderId(market: String, orderId: Int): JsonArray
    public suspend fun deleteOrderByOrderId(orderId: Int): JsonArray
    public suspend fun deleteOrdersByMarket(market: String): JsonArray
    public suspend fun getAllOrdersHistory(type: String? = "", count: Int? = 100): JsonArray
    public suspend fun getOrderHistoryByMarket(market: String, type: String? = "", count: Int? = 100): JsonArray
}