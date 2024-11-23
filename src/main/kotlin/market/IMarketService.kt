package market

import kotlinx.serialization.json.JsonArray

interface IMarketService {
    suspend fun getMarketOrderBooks(market: String, bids: Int? = 0, asks: Int? = 0): JsonArray
    suspend fun getMarketInformation(market: String): JsonArray
    suspend fun getMarkets(): JsonArray
    suspend fun getMarketTicker(market: String): JsonArray
    suspend fun getMarketTickers(): JsonArray
    suspend fun getMarketHistory(market: String, count: Int? = 100): JsonArray
    fun verifyMarket(market: String): Markets
}