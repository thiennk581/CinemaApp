package com.example.momocinema.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momocinema.AppComponent.CustomButton
import com.example.momocinema.AppComponent.PasswordTextField
import com.example.momocinema.AppComponent.TextFieldCustom
import com.example.momocinema.R
import com.example.momocinema.ui.theme.MomoCinemaTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier.padding(horizontal = 28.dp)) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        Divider(thickness = 90.dp, color = Color.White)
        Image(painter = painterResource(id = R.drawable.popcorn), contentDescription = null, modifier = Modifier.size(120.dp))
        Text(text = stringResource(id = R.string.welcome_back), fontSize = 30.sp, fontWeight = FontWeight(800), color = Color(0xFF234EC6))
        Divider(thickness = 2.dp, color = Color.White)
        Text(text = stringResource(id = R.string.sign_to_continue), style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight(400))
        Divider(thickness = 70.dp, color = Color.White)

        TextFieldCustom(
            label = R.string.email_label,
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.size(width = 310.dp, height = 65.dp)
        )

        Divider(thickness = 26.dp, color = Color.White)

        PasswordTextField(
            label = R.string.password_label,
            leadingIcon = Icons.Outlined.Lock,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            value = password,
            onValueChange = { password = it },
            idError = 1
            // TODO nếu trùng khớp password thì idError = 0
        )

        Text(text = stringResource(id = R.string.forgot_password), style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight(700), color = Color(0xFF234EC6),
            modifier = modifier
                .align(Alignment.End)
                .clickable { /* TO DO*/ })
        // TODO?      a thêm vô cho vui, chắc khỏi cũng đc

        Divider(thickness = 30.dp, color = Color.White)
        CustomButton(actionText = R.string.login_button, onClick = { /* TODO */})
        Divider(thickness = 8.dp, color = Color.White)
        Row {
            Text(text = stringResource(id = R.string.dont_have_an_account), modifier = Modifier.padding(end = 4.dp))
            Text(text = stringResource(id = R.string.register), fontWeight = FontWeight.Bold, color = Color(0xFF234EC6),
                modifier = Modifier.clickable {  })
                                    // TODO: qua RegisterScreen
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MomoCinemaTheme {
        LoginScreen()
    }
}