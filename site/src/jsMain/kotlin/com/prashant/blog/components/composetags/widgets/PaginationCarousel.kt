package com.prashant.blog.components.composetags.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowLeft
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRight
import com.varabyte.kobweb.silk.components.icons.fa.FaBackward
import com.varabyte.kobweb.silk.components.icons.fa.FaForward
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text


@Composable
fun PaginationCarousel(totalPages: Int, currentPage: Int, currentCount: (Int) -> Unit) {

    var currentCount by remember { mutableStateOf(totalPages) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.px)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .gap(5.px)
                .padding(bottom = 16.px),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PaginationButton(icon = {
                FaBackward()
            }) {
                currentCount = 0
            }
            PaginationButton(icon = {
                FaArrowLeft()
            }) {
                if (currentPage > 1) {
                    currentCount(currentPage - 1)
                }
            }
            PaginationNumbers(currentPage, currentCount) { selectedStep ->
                currentCount(selectedStep)
            }
            PaginationButton(icon = { FaArrowRight() }) {
                if (currentPage < currentCount - 1) {
                    currentCount(currentPage + 1)
                }
            }
            PaginationButton(icon = { FaForward() }) {
                currentCount = 0
            }
        }

    }
}

@Composable
fun PaginationButton(
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.px)
            .borderRadius(50.percent)
            .onClick { onClick.invoke() }
            .size(50.px)
            .background(MaterialTheme.colorScheme.container),
        contentAlignment = Alignment.Center
    ) {
        icon.invoke()
    }
}

@Composable
fun PaginationNumbers(currentStep: Int, totalPages: Int, onNumberClick: (Int) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .borderRadius(50.percent)
            .gap(8.px)
            .padding(leftRight = 8.px),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 0 until totalPages) {
            PaginationNumber(i + 1, currentStep == i) {
                onNumberClick(it - 1)
            }
        }
    }
}

@Composable
fun PaginationNumber(number: Int, isActive: Boolean, onClick: (Int) -> Unit) {

    Box(
        modifier = Modifier
            .borderRadius(50.percent)
            .size(40.px)
            .background(if (isActive) MaterialTheme.colorScheme.container else MaterialTheme.colorScheme.onContainer)
            .padding(8.px)
            .onClick { onClick(number) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            value = number.toString(),
        )
    }

}

fun splitInteger(input: Int, forward: Boolean): List<Int> {
    val splits = mutableListOf<Int>()
    var remaining = input
    var currentIndex = if (forward) 0 else 4

    while (remaining > 0) {
        val splitValue = if (forward) {
            if (remaining >= 5) {
                currentIndex += 5
                5
            } else {
                currentIndex += remaining
                remaining
            }
        } else {
            if (remaining >= 5) {
                currentIndex -= 5
                5
            } else {
                currentIndex -= remaining
                remaining
            }
        }

        splits.add(splitValue)
        remaining -= splitValue
    }

    return splits
}

fun main() {
    val example1 = 9
    val splits1 = splitInteger(example1, true)
    println("Example 1: $example1")
    println("Splits: $splits1")

    val example2 = 10
    val splits2 = splitInteger(example2, false)
    println("Example 2: $example2")
    println("Splits: $splits2")
}
