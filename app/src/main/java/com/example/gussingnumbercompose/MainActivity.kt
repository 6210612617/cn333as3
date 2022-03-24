package com.example.gussingnumbercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gussingnumbercompose.ui.theme.GussingNumberComposeTheme
import org.intellij.lang.annotations.JdkConstants
import java.lang.Math.abs
import kotlin.random.Random
import androidx.compose.material.Text as Text1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            GussingNumber()
        }
    }
}

var realAns = Random.nextInt(1,1000)




@Composable
fun GussingNumber() {

    var answer = rememberSaveable { mutableStateOf("") }
    var textHint = rememberSaveable { mutableStateOf("Guess your number") }
    var textAns = rememberSaveable { mutableStateOf("")}
    var count = remember { mutableStateOf(0) }
    var textBotton = rememberSaveable { mutableStateOf("Submit")}


    fun checkAnswer() {
        var ans = if (answer.value.isEmpty()){0} else {answer.value.toInt()}
        var dif = ans - realAns
        textBotton.value = "Submit"
        textAns.value = ""
        if (ans == realAns){
            textHint.value = "You Win"
            textAns.value = "Answer is "+ realAns
            textBotton.value = "Try Again"
            realAns = Random.nextInt(1,1000)
            count.value +=1

        }
        else if((abs(dif) == 1) or (abs(dif) == 2) ){
            textHint.value = " Hint : Closer!!"
            count.value +=1
        }
        else if(abs(dif) <= 10){
            textHint.value = " Hint : in range!!"
            count.value +=1
        }
        else if(abs(dif) <= 100){
            textHint.value = " Hint : getting close!"
            count.value +=1
        }
        else if (ans > realAns) {
            textHint.value = " Hint : It's Higher!"
            count.value +=1
        }
        else{
            textHint.value = " Hint : It's lower!"
            count.value +=1
        }


    }


    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        androidx.compose.material.Text(
            text = "Guess number from 1 - 1000",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .padding(bottom = 60.dp)
        )
        androidx.compose.material.Text(
            text = "Count: " + count.value,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)

        )
        androidx.compose.material.Text(
            text = textAns.value,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)

        )

        TextField(
            value = answer.value,
            onValueChange = { answer.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        Button(onClick = {checkAnswer()}) {
            androidx.compose.material.Text(
                textAlign = TextAlign.Center,
                text = textBotton.value,


            )
        }

        androidx.compose.material.Text(
            text = textHint.value,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .padding(top = 20.dp)

        )

        ResetButton(word = "Restart", onClick = {
            textAns.value = ""
            count.value=0
            textHint.value=""
            var realAns = Random.nextInt(1,1000)})

    }
}

@Composable
fun ResetButton(word: String, onClick: () -> Unit){
    Button(
        onClick = onClick
    ) {
        androidx.compose.material.Text(text = "$word!", fontSize = 15.sp)

    }

}


@Preview
@Composable
fun Preview(){
    GussingNumber()
}


