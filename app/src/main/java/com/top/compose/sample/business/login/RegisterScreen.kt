package com.top.compose.sample.business.login

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.top.compose.icon.FaIcon
import com.top.compose.icon.FaIcons
import com.top.compose.sample.HorizontalDottedProgressBar
import com.top.compose.sample.R
import com.top.compose.sample.business.viewmodel.LoginViewModel
import com.top.compose.sample.ui.lottie.LottieRegisterAnimation
import com.top.compose.widget.TopAppBarCenter

@Composable
fun RegisterScreen(
    rememberNavController: NavHostController,
    onClick: () -> Unit = {}
) {

    TopAppBarCenter(
        title = {
            Text(text = stringResource(R.string.register), color = Color.Black)
        },
        navigationIcon = {
            IconButton(onClick = {
                onClick()
            }) {
                FaIcon(faIcon = FaIcons.ArrowLeft, tint = Color.Black)
            }
        },
        backgroundColor = Color.White,
        isImmersive = true
    ) {
        RegisterContent(rememberNavController)
    }
}

@Composable
fun RegisterContent(
    rememberNavController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var account by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var repassword by remember {
        mutableStateOf(TextFieldValue(""))
    }


    var hasError by remember {
        mutableStateOf(false)
    }

    var passwordVisualTransformation by remember {
        mutableStateOf<VisualTransformation>(
            PasswordVisualTransformation()
        )
    }

    val rePasswordInteractionState = remember { MutableInteractionSource() }



    var rePasswordVisualTransformation by remember {
        mutableStateOf<VisualTransformation>(
            PasswordVisualTransformation()
        )
    }

    val passwordInteractionState = remember { MutableInteractionSource() }
    val success by viewModel.success.observeAsState()


    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                LottieRegisterAnimation()
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                OutlinedTextField(
                    value = account,
                    leadingIcon = {
                        FaIcon(
                            faIcon = FaIcons.User,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                        )
                    },
                    maxLines = 1,
                    isError = hasError,
                    label = { Text(text = "Account") },
                    placeholder = { Text(text = "leo94666") },
                    trailingIcon = {

                    },
                    onValueChange = {
                        account = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
            }
            item { Spacer(modifier = Modifier.height(2.dp)) }
            item {
                OutlinedTextField(
                    value = password,
                    leadingIcon = {
                        FaIcon(
                            faIcon = FaIcons.Key,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                        )
                    },
                    trailingIcon = {

                        IconButton(onClick = {
                            passwordVisualTransformation =
                                if (passwordVisualTransformation != VisualTransformation.None) {
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                }
                        }) {
                            if (passwordVisualTransformation != VisualTransformation.None) {
                                FaIcon(
                                    faIcon = FaIcons.EyeSlash,
                                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                                )
                            } else {
                                FaIcon(
                                    faIcon = FaIcons.Eye,
                                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                                )
                            }

                        }

                    },
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "Password") },
                    onValueChange = {
                        password = it
                    },
                    interactionSource = passwordInteractionState,
                    visualTransformation = passwordVisualTransformation,
                )
            }
            item { Spacer(modifier = Modifier.height(2.dp)) }
            item {
                OutlinedTextField(
                    value = repassword,
                    leadingIcon = {
                        FaIcon(
                            faIcon = FaIcons.Key,
                            tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                        )
                    },
                    trailingIcon = {

                        IconButton(onClick = {
                            rePasswordVisualTransformation =
                                if (rePasswordVisualTransformation != VisualTransformation.None) {
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                }
                        }) {
                            if (rePasswordVisualTransformation != VisualTransformation.None) {
                                FaIcon(
                                    faIcon = FaIcons.EyeSlash,
                                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                                )
                            } else {
                                FaIcon(
                                    faIcon = FaIcons.Eye,
                                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                                )
                            }

                        }

                    },
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text(text = "RepeatPassword") },
                    onValueChange = {
                        repassword = it
                    },
                    interactionSource = rePasswordInteractionState,
                    visualTransformation = rePasswordVisualTransformation,
                )
            }
            item {
                Button(
                    onClick = {
                        if (invalidInput(account = account.text, password = password.text)) {
                            hasError = true
                            viewModel.success.value = false
                        } else {
                            hasError = false
                            viewModel.success.value = true
                            viewModel.login(account.text, password.text)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                ) {
                    if (success == true) {
                        HorizontalDottedProgressBar()
                    } else {
                        Text(text = "Register")
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(vertical = 16.dp)) {
                    Spacer(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )
                    Text(
                        text = "Or Login",
                        color = Color.LightGray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(MaterialTheme.colors.background)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
            item {
                OutlinedButton(
                    onClick = { }, modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    FaIcon(
                        faIcon = FaIcons.Registered,
                        tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                    )
                    ClickableText(
                        text = AnnotatedString("Login Account"),
                        style = MaterialTheme.typography.h6.copy(
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rememberNavController.navigateUp()
                    }
                }
            }
        }
    }
}