package sdk

import api.ApiClient
import api.ApiVersion
import api.IApiClient
import balance.BalanceService
import balance.IBalanceService
import kotlinx.serialization.json.JsonArray
import main.deposit.DepositService
import main.deposit.IDespoitService
import market.IMarketService
import market.MarketService
import market.Markets
import order.IOrderService
import order.OrderService
import time.ITimeService
import time.TimeService
import transaction.ITransactionService
import transaction.TransactionService

public class FiriSDK(override val apiVersion: String = ApiVersion.v2.toString(), override val apiKey: String, override val apiClientId: String, override val apiSecretKey : String) : IFiriSDK
{

    private val apiClient: IApiClient by lazy {
        val validVersion = ApiVersion.entries.find { it.toString() == apiVersion }
        val version = validVersion ?: ApiVersion.v2
        ApiClient(
            apiVersion = version,
            apiKey = apiKey,
            apiClientId = apiClientId,
            apiSecretKey = apiSecretKey
        )
    }

    private val timeService: ITimeService by lazy { TimeService(this.apiClient) }
    private val balanceService: IBalanceService by lazy { BalanceService(this.apiClient) }
    private val transactionService: ITransactionService by lazy { TransactionService(this.apiClient) }
    private val depositService: IDespoitService by lazy { DepositService(this.apiClient) }
    private val marketServices: IMarketService by lazy { MarketService(this.apiClient) }
    private val orderServices: IOrderService by lazy { OrderService(this.apiClient, this.marketServices) }

    override suspend fun getServerTime(): JsonArray {
        return this.timeService.getServerTime()
    }

    override suspend fun getUserWalletBalances(): JsonArray {
        return this.balanceService.getUserWalletBalances()
    }

    override suspend fun getAllTransactionHistory(direction: String?, count: Int?): JsonArray {
        return this.transactionService.getAllTransactionHistory(direction, count)
    }

    override suspend fun getTransactionHistoryByYear(year: Int, direction: String?): JsonArray {
        return this.transactionService.getTransactionHistoryByYear(year, direction)
    }

    override suspend fun getTransactionHistoryByMonthYear(
        month: Int,
        year: Int,
        direction: String?
    ): JsonArray {
        return this.transactionService.getTransactionHistoryByMonthYear(month, year)
    }

    override suspend fun getMarketOrderBooks(market: String, bids: Int?, asks: Int?): JsonArray {
        return this.marketServices.getMarketOrderBooks(market, bids, asks)
    }

    override suspend fun getMarketInformation(market: String): JsonArray {
        return this.marketServices.getMarketInformation(market)
    }

    override suspend fun getMarkets(): JsonArray {
        return this.marketServices.getMarkets()
    }

    override suspend fun getMarketTicker(market: String): JsonArray {
        return this.marketServices.getMarketTicker(market)
    }

    override suspend fun getMarketTickers(): JsonArray {
        return this.marketServices.getMarketTickers()
    }

    override suspend fun getMarketHistory(market: String, count: Int?): JsonArray {
        return this.marketServices.getMarketHistory(market, count)
    }

    override fun verifyMarket(market: String): Markets {
        return this.marketServices.verifyMarket(market)
    }

    override suspend fun getOrders(): JsonArray {
        return this.orderServices.getOrders()
    }

    override suspend fun createOrder(
        market: String,
        type: String,
        price: Double,
        amount: Double
    ): JsonArray {
        return this.orderServices.createOrder(market, type, price, amount)
    }

    override suspend fun deleteAllOrders(): JsonArray {
        return this.orderServices.deleteAllOrders()
    }

    override suspend fun getAllActiveOrdersByMarket(
        market: String,
        count: Int?
    ): JsonArray {
        return this.orderServices.getAllActiveOrdersByMarket(market, count)
    }

    override suspend fun getAllFilledClosedOrdersByMarket(
        market: String,
        count: Int?
    ): JsonArray {
        return this.orderServices.getAllFilledClosedOrdersByMarket(market, count)
    }

    override suspend fun getAllFilledClosedOrders(count: Int?): JsonArray {
        return this.orderServices.getAllFilledClosedOrders(count)
    }

    override suspend fun getOrderById(orderId: Int): JsonArray {
        return this.orderServices.getOrderById(orderId)
    }

    override suspend fun deleteOrdersByMarketOrderId(
        market: String,
        orderId: Int
    ): JsonArray {
        return this.orderServices.deleteOrdersByMarketOrderId(market, orderId)
    }

    override suspend fun deleteOrderByOrderId(orderId: Int): JsonArray {
        return this.orderServices.deleteOrderByOrderId(orderId)
    }

    override suspend fun deleteOrdersByMarket(market: String): JsonArray {
        return this.orderServices.deleteOrdersByMarket(market)
    }

    override suspend fun getAllOrdersHistory(type: String?, count: Int?): JsonArray {
        return this.orderServices.getAllOrdersHistory()
    }

    override suspend fun getOrderHistoryByMarket(market: String, type: String?, count: Int?): JsonArray {
        return this.orderServices.getOrderHistoryByMarket(market, type, count)
    }

    override suspend fun getUserPendingCurrencyWithdrawal(currency: String): JsonArray {
        return this.depositService.getUserPendingCurrencyWithdrawal(currency)
    }

    override suspend fun getUserCurrencyAddress(currency: String): JsonArray {
        return this.depositService.getUserCurrencyAddress(currency)
    }

    override suspend fun getUsersDepositHistory(
        count: Int?,
        before: Int?
    ): JsonArray {
        return this.depositService.getUsersDepositHistory(count, before)
    }

    override suspend fun getUserDepositAddresses(): JsonArray {
        return this.depositService.getUserDepositAddresses()
    }
}