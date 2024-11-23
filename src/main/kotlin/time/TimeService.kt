package time

import api.IApiClient
import kotlinx.serialization.json.JsonArray
import main.service.BaseService

class TimeService(private val apiClientService: IApiClient): BaseService(),ITimeService
{
    override val endpoint: String = "time"

    override suspend fun getServerTime(): JsonArray {
            return this.apiClientService.executeGetRequest(this.endpoint)
    }
}