package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text


/**
 * Composable function for a Pagination Carousel, allowing navigation through pages.
 *
 * @param totalPages The total number of pages available.
 * @param currentPage The current active page.
 * @param currentCount A lambda function to provide the current count.
 *
 * @author: Prashant Singh
 */
@Composable
fun PaginationCarousel(
    totalPages: Int,
    currentPage: Int,
    currentCount: (Int) -> Unit,
) {

    // Determine the end value based on the total number of pages
    val endValue by remember { mutableStateOf(5.takeIf { totalPages > 10 } ?: totalPages) }
    var range by remember { mutableStateOf(IntRange(1, endValue)) }

    // Invoke the actual implementation of PaginationCarousel with the calculated range
    PaginationCarousel(
        range = range,
        currentPage = currentPage,
        totalPages = totalPages,
        isButtonRequire = totalPages > 10,
        currentCount = currentCount,
        forwardClick = {
            range = calculateRangeOnClick(totalPages, true, range)
            currentCount(range.first)
        },
        backwardClick = {
            range = calculateRangeOnClick(totalPages, false, range)
            currentCount(range.first)
        }
    )
}

/**
 * Internal implementation of Pagination Carousel.
 *
 * @param range The range of pages to display.
 * @param currentPage The current active page.
 * @param isButtonRequire The visibility state of pagination buttons.
 * @param currentCount A lambda function to provide the current count.
 * @param forwardClick A lambda function to handle forward button click.
 * @param backwardClick A lambda function to handle backward button click.
 */
@Composable
private fun PaginationCarousel(
    range: IntRange,
    currentPage: Int,
    totalPages: Int,
    isButtonRequire: Boolean,
    currentCount: (Int) -> Unit,
    forwardClick: () -> Unit,
    backwardClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.px)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().gap(5.px).padding(bottom = 16.px),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Backward pagination button
            PaginationButton(
                isButtonRequire = isButtonRequire && range.first != 1,
                icon = {
                    FaCustomIcon(iconName = "fa-angle-left")
                }) {
                backwardClick()
            }

            // Display pagination numbers
            PaginationNumbers(currentPage, range) { selectedStep ->
                currentCount(selectedStep)
            }

            // Forward pagination button
            PaginationButton(
                icon = { FaCustomIcon() },
                isButtonRequire = isButtonRequire && range.last != totalPages,
            ) {
                forwardClick()
            }
        }
    }
}

/**
 * Composable function for a pagination button.
 *
 * @param icon The composable function to display as the button icon.
 * @param isButtonRequire The visibility state of the button.
 * @param onClick A lambda function to handle button click.
 */
@Composable
private fun PaginationButton(
    icon: @Composable () -> Unit,
    isButtonRequire: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.padding(4.px).visibility(isButtonRequire)
            .onClick { onClick.invoke() }
            .borderRadius(50.percent)
            .background(MaterialTheme.colorScheme.container)
            .size(50.px),
        contentAlignment = Alignment.Center
    ) {
        icon.invoke()
    }
}

/**
 * Composable function to display pagination numbers.
 *
 * @param currentStep The current active page.
 * @param totalPages The range of total pages.
 * @param onNumberClick A lambda function to handle number click.
 */
@Composable
private fun PaginationNumbers(
    currentStep: Int,
    totalPages: IntRange,
    onNumberClick: (Int) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxHeight().borderRadius(50.percent).gap(8.px)
            .padding(leftRight = 8.px),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Display individual pagination numbers
        for (i in totalPages) {
            PaginationNumber(i, currentStep == i) {
                onNumberClick(it)
            }
        }
    }
}

/**
 * Composable function to display a single pagination number.
 *
 * @param number The page number to display.
 * @param isActive The activation state of the number.
 * @param onClick A lambda function to handle number click.
 */
@Composable
private fun PaginationNumber(number: Int, isActive: Boolean, onClick: (Int) -> Unit) {

    Box(
        modifier = Modifier.borderRadius(50.percent).size(40.px).cursor(Cursor.Pointer)
            .background(if (isActive) MaterialTheme.colorScheme.container else MaterialTheme.colorScheme.onContainer)
            .padding(8.px).onClick { onClick(number) }, contentAlignment = Alignment.Center
    ) {
        Text(
            value = number.toString(),
        )
    }
}

/**
 * Calculate the range of pages to display based on the current range and direction of movement.
 *
 * @param totalPages The maximum number of pages.
 * @param isForwardCall Boolean indicating whether it's a forward movement.
 * @param currentRange The current range of displayed pages.
 * @return The updated range of pages to display.
 */
private fun calculateRangeOnClick(
    totalPages: Int,
    isForwardCall: Boolean,
    currentRange: IntRange
): IntRange {
    return if (isForwardCall) {
        if (currentRange.last + 1 < totalPages) {
            calculateRange(currentRange.last + 1, totalPages)
        } else {
            currentRange
        }
    } else {
        if (currentRange.first - 5 > 0) {
            calculateRange(currentRange.first - 5, totalPages)
        } else {
            currentRange
        }
    }
}

/**
 * Calculate the range of pages to display based on the starting page and maximum number of pages.
 *
 * @param start The starting page.
 * @param totalPages The maximum number of pages.
 * @return The range of pages to display.
 */
private fun calculateRange(start: Int, totalPages: Int): IntRange {
    val end = minOf(start + 4, totalPages)
    return IntRange(start, end)
}
