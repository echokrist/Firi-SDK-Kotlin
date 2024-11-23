package market

import api.IApiClient
import kotlinx.serialization.json.JsonArray
import main.service.BaseService

class MarketService(private val apiClientService: IApiClient): BaseService(), IMarketService {

    override val endpoint: String = "markets"

    override suspend fun getMarketOrderBooks(market: String, bids: Int?, asks: Int?): JsonArray {
        val validMarket = this.verifyMarket(market)
        return this.apiClientService.executeGetRequest("/$endpoint/$validMarket/depth?bids=${bids.toString()}&asks=${asks.toString()}")
    }

    override suspend fun getMarketInformation(market: String): JsonArray {
        val validMarket = this.verifyMarket(market)
        return this.apiClientService.executeGetRequest("/$endpoint/$validMarket")
    }

    override suspend fun getMarkets(): JsonArray {
        return this.apiClientService.executeGetRequest("/$endpoint")
    }

    override suspend fun getMarketTicker(market: String): JsonArray {
        val validMarket = this.verifyMarket(market)
        return this.apiClientService.executeGetRequest("/$endpoint/$validMarket/ticker")
    }

    override suspend fun getMarketTickers(): JsonArray {
        return this.apiClientService.executeGetRequest("/$endpoint/tickers")
    }

    override suspend fun getMarketHistory(market: String, count: Int?): JsonArray {
        val validMarket = this.verifyMarket(market)
        return this.apiClientService.executeGetRequest("/$endpoint/$validMarket/history?count=${count.toString()}")
    }

    override fun verifyMarket(market: String): Markets {
        try {
            return Markets.valueOf(market)
        } catch (e: IllegalArgumentException) {
            val caller = Thread.currentThread().stackTrace[2].methodName
            throw IllegalArgumentException("Invalid market passed to $caller")
        }
    }
}


