package order

import api.IApiClient
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.encodeToJsonElement
import main.service.BaseService
import market.IMarketService

public class OrderService(private val apiClientService: IApiClient, private val marketServices: IMarketService): BaseService(),IOrderService {

    override val endpoint: String = "orders"

    override suspend fun getOrders(): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("/$endpoint")
    }

    override suspend fun createOrder(market: String, type: String, price: Double, amount: Double): JsonArray {
        val validMarket = this.marketServices.verifyMarket(market)
        val order: ICreateOrder = CreateOrder(market = validMarket.toString(), type = type, price = price, amount = amount)
        val body = Json.encodeToJsonElement(order) as JsonArray
        return this.apiClientService.executePostRequest("/orders", body)
    }

    override suspend fun deleteAllOrders(): JsonArray {
        return this.apiClientService.executeDeleteRequest("/$endpoint/delete/all")
    }

    override suspend fun getAllActiveOrdersByMarket(market: String, count: Int?): JsonArray {
        val validMarket = this.marketServices.verifyMarket(market)
        return this.apiClientService.executeGetRequestAuthorized("/$endpoint/$validMarket?count=${count.toString()}")
    }

    override suspend fun getAllFilledClosedOrdersByMarket(market: String, count: Int?): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("/$endpoint/$market/history")
    }

    override suspend fun getAllFilledClosedOrders(count: Int?): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("/$endpoint/history")
    }

    override suspend fun getOrderById(orderId: Int): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("/$endpoint/$orderId")
    }

    override suspend fun deleteOrdersByMarketOrderId(market: String, orderId: Int) : JsonArray {
        val body = Json.encodeToJsonElement(mapOf("market" to market, "orderId" to orderId)) as JsonArray
        return this.apiClientService.executeDeleteRequest("/$endpoint/$market/$orderId", body)
    }

    override suspend fun deleteOrderByOrderId(orderId: Int): JsonArray {
        val body = Json.encodeToJsonElement(mapOf("orderId" to orderId)) as JsonArray
        return this.apiClientService.executeDeleteRequest("/$endpoint/$orderId", body)
    }

    override suspend fun deleteOrdersByMarket(market: String): JsonArray {
        val body = Json.encodeToJsonElement(mapOf("market" to market)) as JsonArray
        return this.apiClientService.executeDeleteRequest("/$endpoint/$market", body)
    }

    override suspend fun getAllOrdersHistory(type: String?, count: Int?): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("/history/$endpoint?type=${type.toString()}&count=${count.toString()}")
    }

    override suspend fun getOrderHistoryByMarket(market: String, type: String?, count: Int?): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("/history/$endpoint/$market")
    }
}