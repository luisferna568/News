package com.example.news.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
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
import com.example.news.components.TopAppBar
import com.example.news.models.Article
import com.example.news.navigation.AppScreens
import com.example.news.viewmodel.NewsViewModel

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsScreen(NavController(context = LocalContext.current), NewsViewModel())
}

@Composable
fun NewsScreen(navController: NavController, viewModel: NewsViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val remoteResult by viewModel.news.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Drawer(navController)
            }
        },
    ) {
        Scaffold(
            topBar = { TopAppBar("Noticias", drawerState, scope) }
        ) { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                items(remoteResult?.articles ?: emptyList()) { news ->
                    NewsCard(article = news, navController)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun NewsCard(article: Article, navController: NavController) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .clickable {
                navController.navigate(AppScreens.NewsDetailsScreen.createRoute(article.title))
            }
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
//            AsyncImage(
//                model = "https://example.com/image.jpg",
//                contentDescription = "Translated description of what the image contains"
//            )
            Image(
                painter = rememberImagePainter(data = article.urlToImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Autor: ${article.author}",
//                    style = MaterialTheme.typography.body2.copy(fontSize = 14.sp)
                )
//                Text(
//                    text = "Year: ${movie.releaseYear}",
////                    style = MaterialTheme.typography.body2.copy(fontSize = 14.sp)
//                )
                Text(
                    text = "Fecha: ${article.publishedAt}",
//                    style = MaterialTheme.typography.body2.copy(fontSize = 14.sp)
                )
//                Text(
//                    text = movie.description,
////                    style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
//                    maxLines = 3,
//                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
//                )
            }
        }
    }

}
