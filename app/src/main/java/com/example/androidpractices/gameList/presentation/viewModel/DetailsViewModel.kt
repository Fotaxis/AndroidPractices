package com.example.androidpractices.gameList.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractices.core.coroutinesUtils.launchLoadingAndError
import com.example.androidpractices.gameList.domain.entity.GameFullEntity
import com.example.androidpractices.gameList.domain.repository.IGamesRepository
import com.example.androidpractices.gameList.presentation.state.GameDetailState
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back

class DetailsViewModel(
    private val repository: IGamesRepository,
    private val navigation: StackNavContainer,
    private val id: String
): ViewModel() {

    private val mutableState = MutableDetailsState()
    val viewState = mutableState as GameDetailState

    init {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.game = repository.getById(id)
        }
    }

    fun back() {
        navigation.back()
    }

    fun onRatingChanged(rating: Int) {
        mutableState.rating = rating
    }

    private class MutableDetailsState : GameDetailState {
        override var game: GameFullEntity? by mutableStateOf(null)
        override var rating: Int by mutableIntStateOf(0)
        override val maxRating: Int
            get() = 5
        override val isRatingVisible: Boolean get() = rating != 0
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
    }
}