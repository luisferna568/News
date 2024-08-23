package com.example.news.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.news.components.Drawer
import com.example.news.components.TopAppBar
import com.example.news.data.Student
import com.example.news.data.students

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(navController = NavController(context = LocalContext.current))
}

@Composable
fun AboutScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Drawer(navController)
            }
        },
    ) {
        Scaffold(
            topBar = { TopAppBar("Acerca de", drawerState, scope) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Materia: Desarrollo para dispositivos inteligentes",
                )
                Text(
                    text = "Profesor: Dr. Armando Méndez Morales",
                )
                Text(
                    text = "Año: 2024",
                )
                Text(
                    text = "Grado: 9no",
                )
                Text(
                    text = "Grupo: A",
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Integrantes",
                    fontSize = 20.sp,
                )

                students.forEach { student ->
                    StudentItem(student)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }
    }
}

@Composable
fun StudentItem(student: Student) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = student.photo),
            contentDescription = "Foto de ${student.name}",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = student.name)
        }
    }
}