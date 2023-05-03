package jp.matsuura.pokemon.androidapp.ui.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun DetailScreen (
    viewModel: DetailViewModel = hiltViewModel()
) {
    Text(text = "Detail Screen")
}