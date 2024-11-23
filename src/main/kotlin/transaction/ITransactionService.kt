package transaction

import kotlinx.serialization.json.JsonArray
import java.util.*

interface ITransactionService {
    suspend fun getAllTransactionHistory(direction: String? = "start", count: Int? = 100): JsonArray
    suspend fun getTransactionHistoryByYear(year: Int, direction: String? = "start"): JsonArray
    suspend fun getTransactionHistoryByMonthYear(
        month: Int = Calendar.getInstance().get(Calendar.MONTH) + 1,
        year: Int = Calendar.getInstance().get(Calendar.YEAR),
        direction: String? = "start"
    ): JsonArray
}