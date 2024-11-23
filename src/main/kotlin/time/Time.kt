package time

import kotlinx.serialization.Serializable


@Serializable
data class Time(
    val serverTime: Long
)
