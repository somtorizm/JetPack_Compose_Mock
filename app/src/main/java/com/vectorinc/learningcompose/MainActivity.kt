package com.vectorinc.learningcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vectorinc.learningcompose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewConversation()


        }

    }

    data class Message(val name: String, val desc: String)


    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewCardMessage() {
        LearningComposeTheme() {
            MessageCard(
                message = Message(
                    "Victor",
                    "This is victor title description that makes things available"
                )
            )
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        LearningComposeTheme() {
            val messageList: MutableList<Message> = mutableListOf()
            messageList.add(Message("Victor", "This is victor the best students in Nigeira"))
            messageList.add(
                Message(
                    "Somto",
                    "This is victor the best students in Nigeira, This is victor the best students in Nigeira, This is victor the best students in Nigeira"
                )
            )
            messageList.add(Message("Igwebuike", "This is victor the best students in Nigeira"))
            messageList.add(Message("Victor", "This is victor the best students in Nigeira"))
            messageList.add(Message("Chidalu", "This is victor the best students in Nigeira"))
            messageList.add(
                Message(
                    "Ezenwa",
                    "This is victor the best students in Nigeira, This is victor the best students in Nigeira, This is victor the best students in Nigeira"
                )
            )

            Conversation(messageList)
        }
    }


    @Composable
    fun MessageCard(message: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.victor),
                contentDescription = "Victor's Image",
                modifier = Modifier
                    .size(40.dp)
                    .border(width = 1.dp, MaterialTheme.colors.secondary, RoundedCornerShape(6.dp))
                    .clip(RoundedCornerShape(6.dp))

            )
            Spacer(modifier = Modifier.width(8.dp))


            var isExpanded by remember { mutableStateOf(false) }

            val surfacColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            )

            Column(modifier = Modifier.clickable {
                isExpanded = !isExpanded
            }) {
                Text(
                    text = message.name,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(5.dp))

                androidx.compose.material.Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    color = surfacColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(

                        text = message.desc,
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )


                }

            }


        }
    }


}