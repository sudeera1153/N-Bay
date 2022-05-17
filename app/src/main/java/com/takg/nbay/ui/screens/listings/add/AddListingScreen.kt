package com.takg.nbay.ui.screens.listings.add

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.takg.nbay.R
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.ItemCategory
import com.takg.nbay.domain.model.ItemCondition
import com.takg.nbay.ui.components.DefaultSnackbar
import com.takg.nbay.ui.components.OutLinedDropDown
import com.takg.nbay.ui.components.PrimaryTextField
import com.takg.nbay.ui.navigation.Screen
import com.takg.nbay.ui.theme.NBayTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import com.takg.nbay.common.Constants
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddListingScreen(
    outputDirectoryCallback: () -> File,
    navController: NavController,
    viewModel: AddListingViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    val outputDirectory = outputDirectoryCallback()

    val scaffoldState = rememberScaffoldState()
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    LaunchedEffect(key1 = context) {
        viewModel.addListingEvents.collect {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> print("Loading")
                is Resource.Success -> {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back Button",
                        tint = Color.Black
                    )
                }

            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            scaffoldState.snackbarHostState
        }
    ) {
        Box(modifier = Modifier.padding(horizontal = 24.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create a Listing",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                ListingImage(
                    outputDirectory = outputDirectory,
                    scaffoldState = scaffoldState,
                    cameraPermissionState = cameraPermissionState
                )

                PrimaryTextField(
                    value = state.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                    singleLine = true,
                    maxLines = 1,
                    label = {
                        Text(text = "Title", color = Color.LightGray)
                    },
                    leadingIcon = { Icon(Icons.Outlined.Title, contentDescription = "Title Icon") },
                    onValueChange = {
                        viewModel.onEvent(AddListingFormEvent.TitleChanged(it))
                    },
                )
                PrimaryTextField(
                    value = state.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp)
                        .height(80.dp),
                    singleLine = true,
                    maxLines = 20,
                    label = {
                        Text(
                            text = "Description",
                            color = Color.LightGray,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Feed,
                            contentDescription = "Feed Icon",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    },
                    onValueChange = { viewModel.onEvent(AddListingFormEvent.DescriptionChanged(it)) },
                )
                OutLinedDropDown(
                    value = state.description,
                    labelText = "Select Category",
                    options = ItemCategory.values().associate {
                        it.name to it.value
                    },
                    onSelect = {
                        viewModel.onEvent(AddListingFormEvent.CategoryChanged(it))
                    }
                )
                OutLinedDropDown(
                    value = state.description,
                    labelText = "Item Condition",
                    options = ItemCondition.values().associate {
                        it.name to it.value
                    },
                    onSelect = {
                        viewModel.onEvent(AddListingFormEvent.ConditionChanged(it))
                    }
                )
                PrimaryTextField(
                    value = state.price,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    maxLines = 1,
                    label = {
                        Text(text = "Price", color = Color.LightGray)
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.AttachMoney,
                            contentDescription = "Money Icon"
                        )
                    },
                    onValueChange = { viewModel.onEvent(AddListingFormEvent.PriceChanged(it)) },
                )

                Button(
                    onClick = {
                        viewModel.onEvent(AddListingFormEvent.Submit)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 45.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentPadding = PaddingValues(vertical = 15.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff0aa1dd),
                    )
                ) {
                    Text(
                        text = "Place Item",
                        color = Color.White
                    )

                }
            }

            DefaultSnackbar(
                snackbarHostState = scaffoldState.snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter),
                onAction = {
                    scaffoldState.snackbarHostState.currentSnackbarData?.performAction()
                },
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ListingImage(
    outputDirectory: File,
    scaffoldState: ScaffoldState,
    cameraPermissionState: PermissionState
) {
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }

    val context = LocalContext.current
    val mainExecutor = ContextCompat.getMainExecutor(context)

    if (snackbarVisibleState) {
        val permissionRationale = if (cameraPermissionState.shouldShowRationale) {
            "The camera is important for this app. Please grant the permission."
        } else {
            "Camera permission required for this feature to be available. " +
                    "Please grant the permission"
        }

        LaunchedEffect(LocalContext.current) {
            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = permissionRationale,
                actionLabel = "Grant Access",
                duration = SnackbarDuration.Long
            )
            when (snackbarResult) {
                SnackbarResult.Dismissed -> {
                    //User denied the permission, do nothing
                }
                SnackbarResult.ActionPerformed -> {
                    cameraPermissionState.launchPermissionRequest()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.box_placeholder),
            contentDescription = "Item",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
                .height(150.dp)
                .align(Alignment.CenterHorizontally)
        )
        OutlinedButton(
            onClick = {
                if (!cameraPermissionState.hasPermission) {
                    setSnackBarState(true)
                } else {
                    takePhoto(
                        imageCapture = imageCapture,
                        outputDirectory = outputDirectory,
                        executor = mainExecutor,
                        onImageCaptured = {
                            Log.e("nbay", "Captured: $it")
                        },
                        onError = {
                            Log.e("nbay", "Image capture Error")
                        }
                    )
                }

            },
            modifier = Modifier.width(190.dp),
            contentPadding = PaddingValues(vertical = 15.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = elevation(10.dp)
        ) {
            Text(
                "Select Item Picture",
                color = Color.LightGray,
                modifier = Modifier.padding(start = 10.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateListingPreview() {
    NBayTheme {
//        AddListingScreen(rememberNavController());
    }
}

private fun takePhoto(
    imageCapture: ImageCapture,
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {

    val photoFile = File(
        outputDirectory,
        SimpleDateFormat(
            Constants.FILENAME_FORMAT,
            Locale.US
        ).format(System.currentTimeMillis()) + ".jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(outputOptions, executor, object : ImageCapture.OnImageSavedCallback {
        override fun onError(exception: ImageCaptureException) {
            Log.e("nbay", "Take photo error:", exception)
            onError(exception)
        }

        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            val savedUri = Uri.fromFile(photoFile)
            onImageCaptured(savedUri)
        }
    })
}