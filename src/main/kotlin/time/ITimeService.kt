package time

import kotlinx.serialization.json.JsonArray

interface ITimeService {
    suspend fun getServerTime(): JsonArray
}