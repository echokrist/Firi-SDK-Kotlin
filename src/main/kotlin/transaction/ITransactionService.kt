package transaction

import kotlinx.serialization.json.JsonArray
import java.util.*

public interface ITransactionService {
    public suspend fun getAllTransactionHistory(direction: String? = "start", count: Int? = 100): JsonArray
    public suspend fun getTransactionHistoryByYear(year: Int, direction: String? = "start"): JsonArray
    public suspend fun getTransactionHistoryByMonthYear(
        month: Int = Calendar.getInstance().get(Calendar.MONTH) + 1,
        year: Int = Calendar.getInstance().get(Calendar.YEAR),
        direction: String? = "start"
    ): JsonArray
}