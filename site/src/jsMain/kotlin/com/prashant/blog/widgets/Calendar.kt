package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.utils.commonfunctions.CommonFunctions.capitalize
import com.prashant.blog.utils.css.CssStyleRegistration.colorScheme
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.silk.components.icons.fa.FaRotateRight
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.datetime.internal.JSJoda.LocalDate
import kotlinx.datetime.internal.JSJoda.Month
import kotlinx.datetime.internal.JSJoda.YearMonth
import kotlinx.datetime.internal.JSJoda.ZoneId
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.Text


/**
 * A composable Calendar component that allows the selection of dates.
 *
 * @param onSelectedDate Callback to handle the selection of a date.
 */
@Composable
fun Calendar(onSelectedDate: (LocalDate) -> Unit) {
    val localDate = LocalDate.now(clockOrZone = ZoneId.SYSTEM)
    var selectedMonth by remember { mutableIntStateOf(localDate.month().value().toInt()) }
    var selectedYear by remember { mutableIntStateOf(localDate.year().toInt()) }
    var selectedDay by remember { mutableIntStateOf(localDate.dayOfMonth().toInt()) }

    LaunchedEffect(Unit) {
        onSelectedDate(LocalDate.of(selectedYear, selectedMonth, selectedDay))
    }

    CalendarContainer(
        month = selectedMonth,
        year = selectedYear,
        selectedDay = selectedDay,
        onPreviousClick = {
            selectedDay = 0
            if (selectedMonth == 1) {
                selectedMonth = 12
                selectedYear--
            } else {
                selectedMonth--
            }
        },
        onNextClick = {
            selectedDay = 0
            if (selectedMonth == 12) {
                selectedMonth = 1
                selectedYear++
            } else {
                selectedMonth++
            }
        },
        onReload = {
            selectedDay = localDate.dayOfMonth().toInt()
            selectedMonth = localDate.month().value().toInt()
            selectedYear = localDate.year().toInt()
            onSelectedDate(LocalDate.of(selectedYear, selectedMonth, selectedDay))
        },
        onDayClick = {
            selectedDay = it
            onSelectedDate(LocalDate.of(selectedYear, selectedMonth, selectedDay))
        }
    )
}

/**
 * Internal Calendar content composable.
 *
 * @param month Current month.
 * @param year Current year.
 * @param onNextClick Callback for the next month button.
 * @param onDayClick Callback for a day click.
 * @param onPreviousClick Callback for the previous month button.
 * @param onReload Callback to reload the calendar to the current month.
 */
@Composable
private fun CalendarContainer(
    selectedDay: Int,
    month: Int,
    year: Int,
    onNextClick: () -> Unit,
    onDayClick: (Int) -> Unit,
    onPreviousClick: () -> Unit,
    onReload: () -> Unit,
) {
    val daysInMonth = getDaysInMonth(year, month)
    val firstDayOfWeek = getFirstDayOfWeek(year, month)
    val days = generateDays(firstDayOfWeek, daysInMonth)
    val currentDate = LocalDate.now(clockOrZone = ZoneId.SYSTEM)
    val currentMonthYear = remember {
        YearMonth.of(currentDate.year(), currentDate.month())
    }

    val selectedMonth by remember { mutableIntStateOf(currentMonthYear.monthValue().toInt()) }
    val selectedYear by remember { mutableIntStateOf(currentMonthYear.year().toInt()) }


    Column(
        modifier = Modifier.fillMaxSize().padding(16.px),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalendarHeader(
            month = month,
            year = year,
            visibility = !(selectedMonth == month && year == selectedYear),
            onNextClick = onNextClick,
            onPreviousClick = onPreviousClick,
            onReload = onReload
        )
        SimpleGrid(
            numColumns(7), modifier = Modifier.weight(1f)
        ) {
            repeat(days.size) { index ->
                val day = days[index]
                CalendarDay(
                    day = day,
                    isCurrentDate = currentDate.dayOfMonth()
                        .toInt() == day && selectedMonth == month && year == selectedYear,
                    isSelected = selectedDay == day,
                    onDayClick = {
                        onDayClick.invoke(it)
                    }
                )
            }
        }
    }
}

/**
 * Composable for displaying the header of the calendar.
 *
 * @param month Current month.
 * @param year Current year.
 * @param visibility Visibility of the reload button.
 * @param onNextClick Callback for the next month button.
 * @param onPreviousClick Callback for the previous month button.
 * @param onReload Callback to reload the calendar to the current month.
 */
@Composable
private fun CalendarHeader(
    month: Int,
    year: Int,
    visibility: Boolean,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onReload: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.px),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            FaCustomIcon(
                modifier = Modifier.cursor(Cursor.Pointer).onClick { onPreviousClick.invoke() },
                iconName = "fa-angle-left"
            )

            H5 {
                Text(
                    value = "${
                        Month.of(month).name().capitalize()
                    } $year",
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.gap(10.px)
            ) {
                if (visibility) {
                    FaRotateRight(modifier = Modifier.visibility(visibility)
                        .onClick { onReload.invoke() })
                }
                FaCustomIcon(
                    modifier = Modifier.cursor(Cursor.Pointer).onClick { onNextClick.invoke() })
            }
        }
        WeekDayNames()
    }
}


/**
 * Composable for displaying the names of the weekdays.
 */
@Composable
fun WeekDayNames() {
    val weekDayNames = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 8.px),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        weekDayNames.forEach { dayName ->
            SpanText(
                text = dayName, modifier = Modifier.weight(1f).margin(leftRight = 5.px)
            )
        }
    }
}

/**
 * Composable for displaying a single day in the calendar.
 *
 * @param day The day to display.
 * @param isCurrentDate Whether the day is the current date.
 * @param isSelected Whether the day is selected.
 * @param onDayClick Callback for a day click.
 */
@Composable
fun CalendarDay(
    day: Int,
    isCurrentDate: Boolean,
    isSelected: Boolean,
    onDayClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .size(40.px)
            .margin(4.px)
            .borderRadius(50.percent)
            .cursor(Cursor.Pointer)
            .background(
                color = if (isSelected && isCurrentDate && day > 0) {
                    Color.rgb(0xC46EB4)
                } else if (isSelected && day > 0) {
                    colorScheme.action
                } else if (isCurrentDate && day > 0) {
                    colorScheme.secondary
                } else {
                    colorScheme.onContainer
                },
            )
            .onClick {
                onDayClick(day)
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            value = if (day > 0) day.toString() else "",
        )
    }
}


/**
 * Get the number of days in a specific month of a year.
 *
 * @param year The year.
 * @param month The month.
 * @return The number of days in the specified month.
 */
fun getDaysInMonth(year: Int, month: Int): Int {
    return LocalDate.of(year, month, 1).lengthOfMonth().toInt()
}


/**
 * Get the index of the first day of the week in a specific month of a year.
 *
 * @param year The year.
 * @param month The month.
 * @return The index of the first day of the week (0-6).
 */
fun getFirstDayOfWeek(year: Int, month: Int): Int {
    return LocalDate.of(year, month, 1).dayOfWeek().value().toInt() % 7
}


/**
 * Generate a list of days in a month, including placeholders for preceding month days.
 *
 * @param firstDayOfWeek The index of the first day of the week (0-6).
 * @param daysInMonth The number of days in the month.
 * @return The list of days.
 */
fun generateDays(firstDayOfWeek: Int, daysInMonth: Int): List<Int> {
    val days = mutableListOf<Int>()

    for (i in 0 until firstDayOfWeek) {
        days.add(0) // Placeholder for preceding month days
    }

    for (day in 1..daysInMonth) {
        days.add(day)
    }

    return days
}