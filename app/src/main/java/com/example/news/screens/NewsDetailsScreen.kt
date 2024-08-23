package com.example.news.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.news.components.Drawer
import com.example.news.components.TopAppBarSecondary
import com.example.news.viewmodel.NewsViewModel

@Preview(showBackground = true)
@Composable
fun MovieDetailScreenPreview() {
    NewsDetailsScreen(
        navController = NavController(context = LocalContext.current),
        title = "helo",
        viewModel = NewsViewModel()
    )
}

@Composable
fun NewsDetailsScreen(navController: NavController, title: String, viewModel: NewsViewModel) {

    val result by viewModel.news.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

    val article = result?.articles?.find { article -> article.title == title }

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
            topBar = { TopAppBarSecondary(title = article?.source?.name ?: "Error", navController) }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(500.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = rememberImagePainter(article?.urlToImage ?: ""),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(500.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = article?.title ?: "Error",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Autor: ${article?.author ?: "Error"}",
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Publicado: ${article?.publishedAt ?: "Error"}",
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Descripción: ${article?.description ?: "Error"}",
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Más información: ${article?.url ?: "Error"}",
                        )
                    }
                }
            }
        }
    }
}
