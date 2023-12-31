package com.prashant.blog.utils.commonfunctions

import kotlinx.datetime.internal.JSJoda.DateTimeFormatter
import kotlinx.datetime.internal.JSJoda.Instant
import kotlinx.datetime.internal.JSJoda.LocalDate
import kotlinx.datetime.internal.JSJoda.LocalDateTime
import kotlinx.datetime.internal.JSJoda.LocalTime
import kotlinx.datetime.internal.JSJoda.ZoneId
import kotlinx.datetime.internal.JSJoda.ZoneOffset
import kotlinx.datetime.internal.JSJoda.ZonedDateTime
import kotlin.js.Date

object DateTimeUtil {
    fun parseLocalDateWithTimeToLocalDateTime(localDateWithTime: String): LocalDateTime {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        return LocalDateTime.parse(localDateWithTime, formatter)
    }

    fun convertLocalDateTimeToUtc(localDateTime: LocalDateTime): ZonedDateTime {
        return ZonedDateTime.of(localDateTime, ZoneOffset.UTC)
    }

    private fun LocalDate.getCurrentTimeFromLocalDate(): LocalTime {
        val localDateTime = LocalDateTime.of(this, LocalTime.now(ZoneId.SYSTEM))
        return localDateTime.toLocalTime()
    }

    fun LocalDate.toUTC() = this.atStartOfDay(ZoneOffset.UTC).toInstant()
    fun LocalDate.toUTCString() = this.atStartOfDay(ZoneOffset.UTC).toInstant().toString()
    fun LocalDate.toUTCWithTime() =
        combineDateAndTime(this.toString(), this.getCurrentTimeFromLocalDate().toString())

    private fun combineDateAndTime(date: String, time: String): ZonedDateTime {
        val dateTimeString = "$date $time"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val localDateTime = LocalDateTime.parse(dateTimeString, formatter)

        // Combine with UTC
        return ZonedDateTime.of(localDateTime, ZoneOffset.UTC)
    }

    fun LocalDateTime.formatLocalDateTime(use12HourFormat: Boolean = false): String {
        val formatterPattern = if (use12HourFormat) "yyyy-MM-dd h:mm a" else "yyyy-MM-dd HH:mm"
        val formatter = DateTimeFormatter.ofPattern(formatterPattern)

        val dateTime = LocalDateTime.parse(this.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val localDateTime = dateTime.atZone(ZoneId.systemDefault()).toLocalDateTime()

        return localDateTime.format(formatter)
    }

    fun getCurrentTimestamp(): String {
        val instant = LocalDateTime.ofInstant(Instant.now(), ZoneId.UTC)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return formatter.format(instant)
    }

    fun String.convertAndFormatTimestamp(): String {
        val instantUTC = Instant.parse(this)
        val zonedDateTimeIST = instantUTC.atZone(ZoneId.SYSTEM)

        val formatterIST = DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' h:mm a")
        return formatterIST.format(zonedDateTimeIST)
    }

    fun Long.parseDateString() = Date(this).toLocaleDateString()
    fun String.parseDateString(): String {
        val date = Date(this)
        val options = dateLocaleOptions {
            year = "numeric"
            month = "long"
            day = "numeric"
            hour = "numeric"
            minute = "numeric"
            hour12 = true
//            formatMatcher = "MMM dd, yyyy 'at' h:mm a"
        }
        return date.toLocaleString("en-US", options = options)

    }

}