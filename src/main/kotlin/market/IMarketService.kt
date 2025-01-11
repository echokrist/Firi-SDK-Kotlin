package market

import kotlinx.serialization.json.JsonArray

public interface IMarketService {
    public suspend fun getMarketOrderBooks(market: String, bids: Int? = 0, asks: Int? = 0): JsonArray
    public suspend fun getMarketInformation(market: String): JsonArray
    public suspend fun getMarkets(): JsonArray
    public suspend fun getMarketTicker(market: String): JsonArray
    public suspend fun getMarketTickers(): JsonArray
    public suspend fun getMarketHistory(market: String, count: Int? = 100): JsonArray
    public fun verifyMarket(market: String): Markets
}