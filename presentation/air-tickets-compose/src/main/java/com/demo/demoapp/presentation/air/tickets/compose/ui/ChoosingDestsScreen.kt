package com.demo.demoapp.presentation.air.tickets.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.demoapp.core.common.Result
import com.demo.demoapp.core.designsystem.compose.Button1
import com.demo.demoapp.core.designsystem.compose.Grey4
import com.demo.demoapp.core.designsystem.compose.Grey5
import com.demo.demoapp.core.designsystem.compose.Grey6
import com.demo.demoapp.core.designsystem.compose.Tab
import com.demo.demoapp.core.designsystem.compose.Title1
import com.demo.demoapp.core.designsystem.compose.White
import com.demo.demoapp.domain.model.DestinationSuggestion
import com.demo.demoapp.presentation.air.tickets.compose.R
import com.demo.demoapp.presentation.air.tickets.compose.navigation.DestChosen
import com.demo.demoapp.presentation.air.tickets.compose.viewmodels.ChoosingDestsScreenViewModel
import com.valentinilk.shimmer.shimmer

private enum class ChoosingDestsState {
    FROM, TO, TOWNS
}

private fun DestChosen.toChoosingDestsState() =
    when (this) {
        DestChosen.FROM -> ChoosingDestsState.FROM
        DestChosen.TO -> ChoosingDestsState.TO
        DestChosen.NONE -> ChoosingDestsState.TOWNS
    }

@Composable
internal fun ColumnScope.ChoosingDestsRoute(
    chosenDest: DestChosen,
    modifier: Modifier = Modifier,
    viewModel: ChoosingDestsScreenViewModel = hiltViewModel()
) {
    val fromText by viewModel.fromStateFlow.collectAsStateWithLifecycle()
    if (fromText is Result.Loading) return
    var focused by remember { mutableStateOf(chosenDest) }
    var toText by remember { mutableStateOf("") }
    val fromList by viewModel.prevEnteredFromStateFlow.collectAsStateWithLifecycle()
    val toList by viewModel.toDestinationsStateFlow.collectAsStateWithLifecycle()
    val towns by viewModel.townsStateFlow.collectAsStateWithLifecycle()
    var filterText by remember { mutableStateOf("") }
    var choosingDestsState by remember { mutableStateOf(chosenDest.toChoosingDestsState()) }
    val onClick: (String) -> Unit = {
        when (focused) {
            DestChosen.FROM -> viewModel.enterFrom(it)
            DestChosen.TO -> {
                viewModel.enterTo(it)
                toText = it
            }

            DestChosen.NONE -> Unit
        }
    }
    val onFocusChange: (DestChosen, FocusState) -> Unit = { destChosen, focusState ->
        if (!focusState.isFocused) Unit
        focused = destChosen
        filterText = ""
        choosingDestsState = destChosen.toChoosingDestsState()
    }
    val onValueChange: (String) -> Unit = {
        filterText = it
        choosingDestsState =
            if (it.isEmpty()) focused.toChoosingDestsState()
            else ChoosingDestsState.TOWNS
    }
    ChoosingDestsScreen(
        focused = focused,
        fromText = (fromText as? Result.Success)?.data ?: "",
        toText = toText,
        fromList = fromList,
        toList = toList,
        towns = towns,
        filterText = filterText,
        choosingDestsState = choosingDestsState,
        onClick = onClick,
        onFocusChange = onFocusChange,
        onValueChange = onValueChange,
        modifier = modifier
    )
}

@Composable
private fun ChoosingDestsScreen(
    focused: DestChosen,
    fromText: String,
    toText: String,
    fromList: Result<List<String>>,
    toList: Result<List<DestinationSuggestion>>,
    towns: Result<List<String>>,
    filterText: String,
    choosingDestsState: ChoosingDestsState,
    onClick: (String) -> Unit,
    onFocusChange: (DestChosen, FocusState) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.5.dp)
    ) {
        DestTextField(
            textDefault = fromText,
            hint = "Откуда вылет",
            focused = focused == DestChosen.FROM,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            onValueChange = onValueChange,
            onFocusChange = { onFocusChange(DestChosen.FROM, it) },
        )
        HorizontalDivider(thickness = 1.dp, color = Grey5)
        DestTextField(
            textDefault = toText,
            hint = "Куда лететь",
            focused = focused == DestChosen.TO,
            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
            onValueChange = onValueChange,
            onFocusChange = { onFocusChange(DestChosen.TO, it) }
        )
        Spacer(modifier = Modifier.height(30.dp))
        when (choosingDestsState) {
            ChoosingDestsState.FROM -> From(
                items = fromList,
                filterText = filterText,
                onClick = onClick
            )

            ChoosingDestsState.TO -> To(items = toList, filterText = filterText, onClick = onClick)
            ChoosingDestsState.TOWNS -> Towns(items = towns, filterText = filterText, onClick = onClick)
        }
    }
}

@Composable
private fun Towns(
    items: Result<List<String>>,
    filterText: String,
    onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .background(Grey4, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(10.dp)
    ) {
        ChoosingDestsLazyColumn(
            items = items,
            filterAfterLoaded = { this.filter { it.startsWith(filterText) } },
            itemContent = { TownItem(text = it, onClick = onClick) },
            itemContentLoading = { TownLoadingItem(index = it) }
        )
    }
}

@Composable
private fun From(
    items: Result<List<String>>,
    filterText: String,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Grey4, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(10.dp)
    ) {
        Text(
            "Вы уже искали",
            style = Title1,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        Spacer(Modifier.height(10.dp))
        ChoosingDestsLazyColumn(
            items = items,
            filterAfterLoaded = { this.filter { it.startsWith(filterText) } },
            itemContent = { TownItem(text = it, onClick = onClick) },
            itemContentLoading = { TownLoadingItem(index = it) }
        )
    }
}

@Composable
private fun To(
    items: Result<List<DestinationSuggestion>>,
    filterText: String,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Grey4, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(10.dp)
    ) {
        ChoosingDestsLazyColumn(
            items = items,
            filterAfterLoaded = { this.filter { it.town.startsWith(filterText) } },
            itemContent = { ToItem(destinationSuggestion = it, onClick = onClick) },
            itemContentLoading = { ToLoadingItem(index = it) })
    }
}


@Composable
private fun ColumnScope.TownsList(
    items: Result<List<String>>,
    filterText: String,
    onClick: (String) -> Unit
) {
    ChoosingDestsLazyColumn(
        items = items,
        filterAfterLoaded = { this.filter { it.startsWith(filterText) } },
        itemContent = { TownItem(text = it, onClick = onClick) },
        itemContentLoading = { TownLoadingItem(index = it) }
    )
}

@Composable
private fun LazyItemScope.ToItem(
    destinationSuggestion: DestinationSuggestion,
    onClick: (String) -> Unit
) {
    Column(modifier = Modifier
        .clickable { onClick(destinationSuggestion.town) }
        .padding(12.dp)) {
        Text(
            text = destinationSuggestion.town, style = Button1, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        Text(
            text = destinationSuggestion.hint, style = Tab, color = Grey6, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}

@Composable
private fun LazyItemScope.ToLoadingItem(index: Int) {
    Box(modifier = Modifier.shimmer()) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(40.dp)
                .background(Grey5)
        )
    }
}

@Composable
private fun TownItem(text: String, onClick: (String) -> Unit) {
    Text(
        text = text,
        style = Button1,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(text) }
            .padding(vertical = 12.dp)
    )
}

@Composable
fun LazyItemScope.TownLoadingItem(index: Int) {
    Box(modifier = Modifier.shimmer()) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(40.dp)
                .background(Grey5)
        )
    }
}


@Composable
private inline fun <T> ChoosingDestsLazyColumn(
    items: Result<List<T>>,
    crossinline filterAfterLoaded: List<T>.() -> List<T>,
    noinline itemContent: @Composable() (LazyItemScope.(T) -> Unit),
    noinline itemContentLoading: @Composable() (LazyItemScope.(Int) -> Unit),
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        if (items is Result.Success) {
            itemsIndexed(items = items.data.filterAfterLoaded(), itemContent = { index, item ->
                itemContent(item)
                if (index < items.data.size - 1) {
                    HorizontalDivider(color = Grey5, thickness = 1.dp)
                }
            })
        } else if (items is Result.Loading) {
            items(count = 10, itemContent = {
                itemContentLoading(it)
                if (it < 9) {
                    HorizontalDivider(color = Grey5, thickness = 1.dp)
                }
            })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ColumnScope.DestTextField(
    focused: Boolean,
    textDefault: String,
    hint: String,
    shape: Shape,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = remember { MutableInteractionSource() }
    var text by remember { mutableStateOf(textDefault) }
    BasicTextField(
        value = text.replaceFirstChar { it.titlecase() },
        onValueChange = {
            onValueChange(it)
            text = it
        },
        textStyle = Button1,
        singleLine = true,
        interactionSource = interactionSource,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .focusRequester(focusRequester)
            .onFocusChanged {
                onFocusChange(it)
            },
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = textDefault.ifEmpty { text },
                innerTextField = innerTextField,
                placeholder = { Text(hint, style = Button1, color = Grey6) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = if (focused) R.drawable.ic_search else R.drawable.ic_plane_up),
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier.size(28.dp)
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove_destination),
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier
                            .size(28.dp)
                            .background(color = Grey5, shape = CircleShape)
                            .clip(CircleShape)
                            .clickable { text = "" }
                    )
                },
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(vertical = 10.dp),
                shape = shape,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Grey4,
                    focusedContainerColor = Grey4,
                    disabledContainerColor = Grey4,
                    errorContainerColor = Grey4,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
        }
    )
    LaunchedEffect(Unit) {
        if (focused) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }
    LaunchedEffect(textDefault) {
        if (textDefault.isNotEmpty()) {
            text = textDefault
        }
    }
}

@Composable
@Preview
private fun ChoosingDestsScreenPreview() {
    ChoosingDestsScreen(
        focused = DestChosen.NONE,
        fromText = "",
        toText = "",
        fromList = Result.Loading,
        toList = Result.Loading,
        towns = Result.Loading,
        filterText = "",
        choosingDestsState = ChoosingDestsState.TOWNS,
        onClick = {},
        onFocusChange = { _, _ -> },
        onValueChange = {},
    )
}

@Composable
@Preview
private fun TownItemPreview() {
    TownItem("Москва") {}
}