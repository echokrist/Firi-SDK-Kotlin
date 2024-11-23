package transaction

import api.IApiClient
import kotlinx.serialization.json.JsonArray
import main.service.BaseService

class TransactionService(private val apiClientService: IApiClient): BaseService(),ITransactionService {

    override val endpoint: String = "transactions"

    override suspend fun getAllTransactionHistory(direction: String?, count: Int?): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("/history/$endpoint?direction=${direction.toString()}&count=${count.toString()}")
    }

    override suspend fun getTransactionHistoryByYear(year: Int, direction: String?): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("/history/$endpoint/$year")
    }

    override suspend fun getTransactionHistoryByMonthYear(month: Int, year: Int, direction: String?): JsonArray {
        return this.apiClientService.executeGetRequestAuthorized("/history/$endpoint/$month/$year")
    }
}