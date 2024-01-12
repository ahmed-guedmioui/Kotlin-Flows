package com.ahmedapps.kotlinflows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahmedapps.kotlinflows.ui.theme.KotlinFlowsTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val simpleViewModel: SimpleViewModel by viewModels()
    private val tag = "kotlin_flows"

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                simpleViewModel.textState.collectLatest { text ->
//                    binding.textView.text = text
                }
            }
        }

        setContent {
            KotlinFlowsTheme {
                val simpleViewModel1 = viewModel<SimpleViewModel>()
                val textState = simpleViewModel1.textState.collectAsState()

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = textState.value)

                    TextField(
                        value = textState.value,
                        onValueChange = {
                            simpleViewModel1.changeText(it)
                        }
                    )
                }

            }
        }

    }

}
