@file:OptIn(ExperimentalResourceApi::class)

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

data class Experience(
  val company: String,
  val role: String,
  val startDate: String,
  val endDate: String,
  val location: String,
  val projects: List<String>
)

@Composable
fun CreateBizCard() {
  Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
    Card(
      elevation = 4.dp,
      shape = RoundedCornerShape(4),
      backgroundColor = Color(0xFFF8F8F8),
      modifier = Modifier.padding(12.dp)
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        var showProjects by remember { mutableStateOf(true) }
        val projectList = remember {
          mutableStateOf(
            mutableStateListOf(
              Experience(
                "Google",
                "Software Engineer",
                "August 2022",
                "Present",
                "Lagos, NG",
                listOf("Search", "Maps")
              ),
              Experience(
                "FinTech",
                "Frontend Developer",
                "October 2021",
                "August 2022",
                "Abuja, NG",
                listOf("Dashboard", "Landing Page")
              ),
              Experience(
                "Eduland", "IT Support", "March 2021", "July 2021", "Remote", listOf()
              ),
            )
          )
        }
        ImageProfile()
        Divider(
          thickness = 2.dp,
          color = Color(0xFFBDBDBD),
          modifier = Modifier.padding(bottom = 16.dp, start = 24.dp, end = 24.dp)
        )
        ProfileInfo()
        TextButton(
          onClick = { showProjects = !showProjects },
          contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        ) {
          Text(
            text = "${if (showProjects) "Hide" else "Show"} Experiences",
            color = Color(0xFF008080),
            fontWeight = FontWeight(700)
          )
        }
        if (showProjects) {
          LazyColumn(modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)) {
            items(projectList.value.size) { index ->
              ExperienceItem(
                experience = projectList.value[index], modifier = Modifier.padding(bottom = 8.dp)
              )
            }
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExperienceItem(experience: Experience, modifier: Modifier = Modifier) {
  Column(
    modifier = modifier.fillMaxWidth().border(1.dp, Color(0xFFBDBDBD), RoundedCornerShape(8))
      .padding(
        start = 12.dp,
        end = 12.dp,
        top = 12.dp,
        bottom = if (experience.projects.isNotEmpty()) 0.dp else 18.dp
      )
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
      modifier = modifier.fillMaxWidth().padding(bottom = 4.dp)
    ) {
      Text(
        text = experience.company,
        fontWeight = FontWeight(700),
        fontSize = MaterialTheme.typography.h6.fontSize,
        fontFamily = FontFamily.Serif,
      )
      Text(
        text = experience.role,
        fontWeight = FontWeight(500),
        color = Color(0xFF008080),
        fontSize = MaterialTheme.typography.body1.fontSize,
        fontFamily = FontFamily.Default,
      )
    }

    Text(text = "${experience.startDate} - ${experience.endDate}", color = Color(0xFF383838))
    Text(text = experience.location)
    if (experience.projects.isNotEmpty()) {
      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxHeight().padding(top = 8.dp)
      ) {
        experience.projects.forEach { project ->
          Column(
            modifier = modifier.fillMaxHeight()
              .border(1.5.dp, Color(0xFFBDBDBD), RoundedCornerShape(20)).padding(
                horizontal = 12.dp, vertical = 6.dp
              ),
          ) {
            Text(
              text = project,
              fontWeight = FontWeight(700),
              color = Color(0xFF008080),
            )
          }
        }
      }
    }
  }
}

@Composable
fun ProfileInfo() {
  Text(
    text = "John Doe",
    style = MaterialTheme.typography.h3,
    color = Color(0xFF008080),
    fontWeight = FontWeight(700)
  )
  Text(
    text = "Software Engineer",
    style = MaterialTheme.typography.h5,
    color = Color(0xFFBDBDBD),
    fontWeight = FontWeight(600)
  )
  Text(
    text = "@john_doe", style = MaterialTheme.typography.subtitle1, color = Color(0xFFBDBDBD)
  )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageProfile() {
  Row(
    horizontalArrangement = Arrangement.Center,
  ) {
    Image(
      painterResource("profile.png"),
      contentDescription = "profile",
      contentScale = ContentScale.Crop,
      modifier = Modifier.padding(top = 16.dp, bottom = 16.dp).width(200.dp).height(200.dp).border(
        4.dp, MaterialTheme.colors.secondaryVariant, CircleShape
      ).clip(CircleShape)
    )
  }
}

@Composable
fun App() {
  CreateBizCard()
}

expect fun getPlatformName(): String