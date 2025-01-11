package time

import kotlinx.serialization.json.JsonArray

public interface ITimeService {
    public suspend fun getServerTime(): JsonArray
}