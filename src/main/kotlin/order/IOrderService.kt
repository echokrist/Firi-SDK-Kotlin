package order

import kotlinx.serialization.json.JsonArray

interface IOrderService {
    suspend fun getOrders(): JsonArray
    suspend fun createOrder(market: String, type: String, price: Double, amount: Double): JsonArray
    suspend fun deleteAllOrders(): JsonArray
    suspend fun getAllActiveOrdersByMarket(market: String, count: Int? = 100): JsonArray
    suspend fun getAllFilledClosedOrdersByMarket(market: String, count: Int? = 100): JsonArray
    suspend fun getAllFilledClosedOrders(count: Int? = 100): JsonArray
    suspend fun getOrderById(orderId: Int): JsonArray
    suspend fun deleteOrdersByMarketOrderId(market: String, orderId: Int): JsonArray
    suspend fun deleteOrderByOrderId(orderId: Int): JsonArray
    suspend fun deleteOrdersByMarket(market: String): JsonArray
    suspend fun getAllOrdersHistory(type: String? = "", count: Int? = 100): JsonArray
    suspend fun getOrderHistoryByMarket(market: String, type: String? = "", count: Int? = 100): JsonArray
}