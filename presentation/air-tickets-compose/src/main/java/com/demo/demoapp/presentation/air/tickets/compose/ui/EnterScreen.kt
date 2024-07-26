package com.demo.demoapp.presentation.air.tickets.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.demoapp.core.common.Result
import com.demo.demoapp.core.designsystem.compose.Black
import com.demo.demoapp.core.designsystem.compose.Button1
import com.demo.demoapp.core.designsystem.compose.Grey2
import com.demo.demoapp.core.designsystem.compose.Grey4
import com.demo.demoapp.core.designsystem.compose.Grey5
import com.demo.demoapp.core.designsystem.compose.Grey6
import com.demo.demoapp.core.designsystem.compose.Text2
import com.demo.demoapp.core.designsystem.compose.Title1
import com.demo.demoapp.core.designsystem.compose.Title3
import com.demo.demoapp.core.designsystem.compose.White
import com.demo.demoapp.domain.model.Concert
import com.demo.demoapp.presentation.air.tickets.compose.R
import com.demo.demoapp.presentation.air.tickets.compose.viewmodels.EnterScreenViewModel
import com.valentinilk.shimmer.shimmer
import java.text.NumberFormat


@Composable
internal fun EnterScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: EnterScreenViewModel = hiltViewModel()
) {
    val fromDestState by viewModel.fromStateFlow.collectAsStateWithLifecycle()
    val concertsState by viewModel.concertsStateFlow.collectAsStateWithLifecycle()
    EnterScreen(fromDestState = fromDestState, concertsState = concertsState, modifier = modifier)

}

@Composable
private fun EnterScreen(
    fromDestState: Result<String>,
    concertsState: Result<List<Concert>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .padding(horizontal = 15.5.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        ScreenTitle()
        Spacer(modifier = Modifier.height(36.dp))
        DestsButtons(fromDestState = fromDestState)
        Spacer(modifier = Modifier.height(20.dp))
        Concerts(concertsState)
    }
}

@Composable
private fun ScreenTitle() {
    Text(
        text = "Поиск дешевых" + "\n" + "   авиабилетов",
        style = Title1
    )
}

@Composable
private fun DestsButtons(fromDestState: Result<String>) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Grey2)
            .padding(18.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp))
                .background(color = Grey4)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(28.dp)
                    .padding(start = 6.dp)
                    .background(color = Grey4)
                    .align(Alignment.CenterStart)
            )
            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                DestText(text = "Откуда - Москва", textState = fromDestState) {

                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Grey5,
                    modifier = Modifier.padding(start = 30.dp)
                )
                DestText(text = "Куда - Турция", textState = Result.Loading) {

                }
            }
        }
    }
}


@Composable
private fun ColumnScope.DestText(
    text: String,
    textState: Result<String>,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        style = Button1,
        color = when (textState) {
            is Result.Success -> White
            else -> Grey6
        },
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClick()
            }
            .padding(start = 28.dp)
            .padding(vertical = 12.dp)
    )
}

@Composable
private fun ColumnScope.Concerts(concerts: Result<List<Concert>>) {
    Column(modifier = Modifier
        .align(Alignment.Start)
        .clip(RoundedCornerShape(16.dp))
        .background(Grey2)
        .wrapContentSize()
        .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        ConcertsTitle()
        Spacer(modifier = Modifier.height(4.dp))
        ConcertsList(concerts = concerts)
    }
}

@Composable
private fun ColumnScope.ConcertsTitle() {
   Text(text = "Музыкально отлететь", style = Title1)
}

@Composable
private fun ColumnScope.ConcertsList(concerts: Result<List<Concert>>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.wrapContentSize()
    ) {
        if(concerts is Result.Success) {
            items(concerts.data) { concert ->
                Concert(concert)
            }
        } else {
            items(3) {
                ConcertLoading()
            }
        }
    }
}

@Composable
private fun LazyItemScope.ConcertLoading() {
    Column(modifier = Modifier.shimmer(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(modifier = Modifier.size(125.dp).clip(RoundedCornerShape(16.dp)).background(color = Grey5))
        Box(modifier = Modifier.width(125.dp).height(16.dp).clip(RoundedCornerShape(16.dp)).background(color = Grey5))
        Box(modifier = Modifier.width(125.dp).height(16.dp).clip(RoundedCornerShape(16.dp)).background(color = Grey5))
        Box(modifier = Modifier.width(125.dp).height(16.dp).clip(RoundedCornerShape(16.dp)).background(color = Grey5))
    }
}

@Composable
private fun LazyItemScope.Concert(concert: Concert) {
    Column {
        val bitmap = concert.drawable.toBitmap().asImageBitmap()
        Image(
            bitmap = bitmap, contentDescription = null, modifier = Modifier
                .size(125.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                ), contentScale = ContentScale.FillBounds
        )
        Text(text = concert.title, style = Title3)
        Text(text = concert.town, style = Text2)
        Text(text = "от ${NumberFormat.getInstance().format(concert.price.value)}₽", style = Text2)
    }
}

@Composable
@Preview
private fun EnterScreenPreview() {
    EnterScreen(Result.Loading, Result.Loading)
}