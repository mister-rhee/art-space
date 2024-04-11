package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currentArt by remember { mutableStateOf(R.drawable.mona_lisa) }

    Column(
        modifier = Modifier.statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        when (currentArt) {
            R.drawable.mona_lisa -> {
                DrawImageAndFrame(imageToDraw = R.drawable.mona_lisa)
                EditArtInformation(
                    artTitle = R.string.mona_lisa,
                    artist = R.string.mona_lisa_artist,
                    artDate =  R.string.mona_lisa_date
                )
                PrevNextButtonRow(
                    prevButtonOnClick = { currentArt = R.drawable.the_starry_night },
                    nextButtonOnClick = { currentArt = R.drawable.the_scream },
                    modifier = Modifier
                )
            }

            R.drawable.the_scream -> {
                DrawImageAndFrame(imageToDraw = R.drawable.the_scream)
                EditArtInformation(
                    artTitle = R.string.the_scream,
                    artist = R.string.the_scream_artist,
                    artDate =  R.string.the_scream_date
                )
                PrevNextButtonRow(
                    prevButtonOnClick = { currentArt = R.drawable.mona_lisa },
                    nextButtonOnClick = { currentArt = R.drawable.the_starry_night },
                    modifier = Modifier
                )
            }

            R.drawable.the_starry_night -> {
                DrawImageAndFrame(imageToDraw = R.drawable.the_starry_night)
                EditArtInformation(
                    artTitle = R.string.the_starry_night,
                    artist = R.string.the_starry_night_artist,
                    artDate =  R.string.the_starry_night_date
                )
                PrevNextButtonRow(
                    prevButtonOnClick = { currentArt = R.drawable.the_scream },
                    nextButtonOnClick = { currentArt = R.drawable.mona_lisa },
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun DrawImageAndFrame(
    imageToDraw: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(20.dp),
        shadowElevation = 8.dp
    ) {
        Image(
            painter = painterResource(id = imageToDraw),
            contentDescription = null,
            modifier = modifier.padding(32.dp)
        )
    }
}

@Composable
fun EditArtInformation(
    artTitle: Int,
    artist: Int,
    artDate: Int,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.padding(20.dp)) {
        Column(
            modifier = modifier
                .wrapContentWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = artTitle),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Light,
                modifier = modifier
            )
            Row(modifier = modifier) {
                Text(
                    text = stringResource(id = artist),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                )
                Text(
                    text = stringResource(id = artDate),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    modifier = modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
fun PrevNextButtonRow(
    prevButtonOnClick: () -> Unit,
    nextButtonOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = prevButtonOnClick,
            modifier = modifier.width(110.dp)
        ) {
            Text(text = stringResource(id = R.string.previous))
        }
        Button(
            onClick = nextButtonOnClick,
            modifier = modifier.width(110.dp)
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}