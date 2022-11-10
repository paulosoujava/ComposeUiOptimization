package com.paulo.composeuioptimization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paulo.composeuioptimization.optimization1.MainViewModel
import com.paulo.composeuioptimization.optimization1.RgbSelector
import com.paulo.composeuioptimization.optimization2.CustomGrid
import com.paulo.composeuioptimization.optimization2.FeedViewModel
import com.paulo.composeuioptimization.ui.theme.ComposeUiOptimizationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUiOptimizationTheme {

                val viewModel = viewModel<MainViewModel>()
                val changeColorLambda = remember<(Color) -> Unit> {
                    {
                        viewModel.changeColor(it)
                    }
                }
                val color = remember {
                    mutableStateOf(Color.Red)
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    RgbSelector(
                        color = viewModel.color,
                        /*onColorClick = {
                            viewModel.changeColor(it)
                        }*/
                        //onColorClick = viewModel::changeColor
                        // onColorClick = changeColorLambda
                        onColorClick = {
                            color.value = it
                        }
                    )
                }

                val viewModelF = viewModel<FeedViewModel>()
                val feeds = viewModelF.feeds

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CustomGrid(
                        feeds = feeds,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(onClick = viewModelF::rearrangeFeeds) {
                        Text(text = "Shuffle feeds")
                    }
                }
            }
        }
    }
}
