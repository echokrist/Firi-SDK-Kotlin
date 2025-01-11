package time

import kotlinx.serialization.Serializable


@Serializable
public data class Time(
    public val serverTime: Long
)
