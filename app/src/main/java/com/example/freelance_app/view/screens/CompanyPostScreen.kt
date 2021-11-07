package com.example.freelance_app.view.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.freelance_app.utils.CustomColors
import com.example.freelance_app.view.reusables.TopBar
import com.example.freelance_app.view.reusablesv2.Btn
import com.example.freelance_app.view.reusablesv2.CustomField


@ExperimentalAnimationApi
@Composable
fun CompanyPostScreen(navigateToCompanyMainPage: () -> Unit) {
    var description by remember {
        mutableStateOf(
            "Example: " + AppPreferences.job
        )
    }
    var skills by remember {
        mutableStateOf(
            "Example: " + AppPreferences.skills
        )
    }
    var switch by remember {
        mutableStateOf(AppPreferences.mode == "Save")
    }
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                TopBar()
                if (switch) {
                    DisplayCreatePost(
                        d = description,
                        s = skills
                    ) { d, s ->
                        description = d
                        skills = s
                        switch = false
                    }
                } else {
                    DisplayDeleteVersion(
                        d = description,
                        s = skills
                    ) {
                        navigateToCompanyMainPage()
                    }
                }
                Spacer(modifier = Modifier.padding(bottom = 200.dp))
            }
        }
    )
}

@Composable
fun DisplayCreatePost(s: String, d: String, clicked: (String, String) -> Unit) {
    var description by remember {
        mutableStateOf(d)
    }
    var skills by remember {
        mutableStateOf(s)
    }
    PostNameAndDate(text = "Dishwasher", edit = true)
    Text(
        text = "Job Description:",
        color = Color.Black,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .shadow(35.dp)
    )
    CustomField(
        text = description,
        label = "-",
        switch = true,
        bgColor = Color.White,
        textColor = Color.Black,
    ) {}
    Text(
        text = "Needed Skills:",
        color = Color.Black,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
            .shadow(35.dp)
    )
    CustomField(
        text = skills,
        label = "-",
        switch = true,
        bgColor = Color.White,
        textColor = Color.Black,
    ) {}

    ButtonGroup(btn1 = "Save", btn2 = "Applicants") {
        clicked(description, skills)
    }

}

@Composable
fun DisplayDeleteVersion(
    d: String,
    s: String,
    navigateToCompanyMainPage: () -> Unit
) {
    var desc by remember {
        mutableStateOf(d)
    }
    var skills by remember {
        mutableStateOf(s)
    }
    PostNameAndDate(text = "Dishwasher", edit = false)
    Text(
        text = "Job Description:",
        color = Color.Black,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .shadow(35.dp)
    )
    CustomField(
        text = desc,
        label = "-",
        switch = false,
        bgColor = CustomColors.primaryLight,
        textColor = CustomColors.primary,
    ) {}
    Text(
        text = "Needed Skills:",
        color = Color.Black,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
            .shadow(35.dp)
    )
    CustomField(
        text = skills,
        label = "-",
        switch = false,
        bgColor = CustomColors.primaryLight,
        textColor = CustomColors.primary,
    ) {}

    ButtonGroup(btn1 = "Delete", btn2 = "Applicants") {
        navigateToCompanyMainPage()
    }

}

@Composable
fun PostNameAndDate(text: String, edit: Boolean) {
    var dates by remember { mutableStateOf(text) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(start = 20.dp)
                .shadow(35.dp)
        )
        if (!edit) {
            CustomField(
                text = AppPreferences.dates,
                modifier = Modifier.weight(1f),
                label = "Dates",
                placeholder = "Dates",
                switch = false,
                bgColor = CustomColors.primaryLight,
                textColor = CustomColors.primary,
            ) {}
        } else {
            CustomField(
                text = AppPreferences.dates,
                modifier = Modifier.weight(1f),
                label = "Dates",
                placeholder = "Dates",
                switch = true,
                bgColor = Color.White,
                textColor = Color.Black,
            ) {}
        }
    }
}

@Composable
fun ButtonGroup(btn1: String, btn2: String, clicked: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp, horizontal = 20.dp)
    ) {
        Btn(text = btn1) { clicked() }
        Btn(text = btn2, padding = 15) { clicked() }
    }
}
