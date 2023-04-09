import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmanagementapp.ui.theme.Neutral5
import com.example.taskmanagementapp.ui.theme.NeutralBorder
import com.example.taskmanagementapp.ui.theme.VisbyTypography

@Composable
fun TimeGrid(
    listTime: List<Int>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .then(modifier)
            .padding(
                top = 12.dp,
                bottom = 20.dp
            ),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        for (time in listTime) {
            TimelineRow(time = time)
        }
    }
}

@Composable
fun TimelineRow(time: Int) {
    val isPM: Boolean
    val setTime: Int

    if (time > 12) {
        setTime = time - 12
        isPM = true
    } else {
        setTime = time
        isPM = false
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(16.dp)
    ) {
        Text(
            text = if (isPM) "$setTime PM" else "$setTime AM",
            style = VisbyTypography.caption,
            color = Neutral5,
            modifier = Modifier.width(56.dp)
        )

        Divider(
            color = NeutralBorder,
            thickness = 1.dp,
            modifier = Modifier.weight(1f)
        )
    }
}