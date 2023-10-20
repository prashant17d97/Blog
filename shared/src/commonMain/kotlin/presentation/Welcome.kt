package presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.NavHostController
import core.ResourcePath.Drawable.contentDescription
import core.ResourcePath.Drawable.welcomeFirst
import core.ResourcePath.Drawable.welcomeSecond
import core.ResourcePath.String.next
import core.ResourcePath.String.welcomeHeadingFirst
import core.ResourcePath.String.welcomeHeadingSecond
import core.ResourcePath.String.welcomeLabelFirst
import core.ResourcePath.String.welcomeLabelSecond
import datamodel.WelcomeModel
import kotlinx.coroutines.launch
import navigation.Screens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.CommonElements.NinjaButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Welcome(navHostController: NavHostController) {

    val coroutineScope = rememberCoroutineScope()
    val welcomeModel = listOf(
        WelcomeModel(
            heading = welcomeHeadingFirst,
            label = welcomeLabelFirst,
            iconString = welcomeFirst,
        ),
        WelcomeModel(
            heading = welcomeHeadingSecond,
            label = welcomeLabelSecond,
            iconString = welcomeSecond,
        ),
    )
    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) {
        welcomeModel.size
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState, modifier = Modifier
        ) {
            WelcomeItem(welcomeModel[it])
        }

        NinjaButton(text = next, onClick = {
            coroutineScope.launch {
                if (pagerState.currentPage != welcomeModel.size - 1) {
                    pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                } else {
                    navHostController.navigate(route = Screens.LoginScreen, popInclusive = true,)
                }
            }

        })
    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun WelcomeItem(welcomeModel: WelcomeModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(res = welcomeModel.iconString),
            modifier = Modifier,
            contentScale = ContentScale.FillWidth,
            contentDescription = welcomeModel.iconString.contentDescription
        )

        Text(
            text = welcomeModel.heading,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            lineHeight = 35.sp,
            modifier = Modifier.padding(horizontal = 5.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = welcomeModel.label,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.ExtraLight
            ),
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = 35.dp),
        )
    }
}