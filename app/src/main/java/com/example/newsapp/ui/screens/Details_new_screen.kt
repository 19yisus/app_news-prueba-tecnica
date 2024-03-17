package com.example.newsapp.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.example.newsapp.domain.models.Article
import com.example.newsapp.ui.components.common.TopBarWithButtonBack_component
import com.example.newsapp.ui.viewModel.News_viewModel
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class Details_new_screen(private val article: Article): Screen{
	private lateinit var navigator: Navigator
	
	@RequiresApi(Build.VERSION_CODES.O)
	@Composable
	override fun Content() {
		navigator = LocalNavigator.currentOrThrow
		
		val vm: News_viewModel = hiltViewModel()
		Text(text = "Vista detallata")
		Log.i("Data", article.toString())
		
		// Convertir la cadena de fecha en un objeto Instant
		val instant = Instant.parse(article.publishedAt)
		// Definir la zona horaria (opcional, ajusta según tus necesidades)
		val zonaHoraria = ZoneId.of("America/New_York")
		// Convertir el Instant a un objeto LocalDateTime en la zona horaria especificada
		val fechaLocal = instant.atZone(zonaHoraria).toLocalDateTime()
		// Definir el formato deseado (por ejemplo, "dd-MM-yyyy HH:mm:ss")
		val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
		// Formatear la fecha
		val fechaFormateada = fechaLocal.format(formatter)
		
		Scaffold(
			topBar = { TopBarWithButtonBack_component { navigator.pop() } }
		) {
			Box(modifier = Modifier
				.fillMaxSize()
				.padding(it)){
				Column {
					Card(
						shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
						colors = CardDefaults.cardColors(
							containerColor = MaterialTheme.colorScheme.primary
						),
						modifier = Modifier
							.fillMaxWidth()
							.height(200.dp)
					) {
						AsyncImage(
							model = article.urlToImage ?: "",
							contentDescription = "Image",
							modifier = Modifier
								.aspectRatio(18 / 9f)
								.fillMaxSize()
								.scale(1.6F)
						)
					}
					Spacer(modifier = Modifier.padding(vertical = 10.dp))
					Row(
						modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
						verticalAlignment = Alignment.CenterVertically
					) {
						Text(
							text = article.title,
							fontWeight = FontWeight.Bold,
							fontSize = 23.sp,
							modifier = Modifier.weight(0.7F)
						)
						
						Text(
							text = fechaFormateada.toString(),
							fontStyle = FontStyle.Italic
						)
					}
					Spacer(modifier = Modifier.padding(vertical = 10.dp))
					Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
						Text(
							text = article.content ?: "",
							textAlign = TextAlign.Justify
						)
					}
				}
			}
		}
	}
}