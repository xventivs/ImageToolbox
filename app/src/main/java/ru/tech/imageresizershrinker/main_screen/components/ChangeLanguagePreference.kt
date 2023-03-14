package ru.tech.imageresizershrinker.main_screen.components

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import org.xmlpull.v1.XmlPullParser
import ru.tech.imageresizershrinker.R
import java.util.*

@Composable
fun ChangeLanguagePreference() {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    Column(Modifier.animateContentSize()) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { showDialog = true }
                .block(
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(R.string.language))
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = context.getCurrentLocaleString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 14.sp,
                    color = LocalContentColor.current.copy(alpha = 0.5f)
                )
            }
        }
    }

    if (showDialog) {
        PickLanguageDialog(
            entries = context.getLanguages(),
            selected = context.getCurrentLocaleString(),
            onSelect = {
                val locale = if (it == "") {
                    LocaleListCompat.getEmptyLocaleList()
                } else {
                    LocaleListCompat.forLanguageTags(it)
                }
                AppCompatDelegate.setApplicationLocales(locale)
            },
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
private fun PickLanguageDialog(
    entries: Map<String, String>,
    selected: String,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = { Icon(imageVector = Icons.Outlined.Translate, contentDescription = null) },
        title = { Text(stringResource(R.string.language)) },
        text = {
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                entries.forEach { locale ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                            .clickable {
                                onSelect(locale.key)
                                onDismiss()
                            }
                            .padding(start = 12.dp, end = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selected == locale.value,
                            onClick = {
                                onSelect(locale.key)
                                onDismiss()
                            }
                        )
                        Text(locale.value)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

private fun Context.getLanguages(): Map<String, String> {
    val languages = mutableListOf<Pair<String, String>>()
    val parser = resources.getXml(R.xml.locales_config)
    var eventType = parser.eventType
    while (eventType != XmlPullParser.END_DOCUMENT) {
        if (eventType == XmlPullParser.START_TAG && parser.name == "locale") {
            for (i in 0 until parser.attributeCount) {
                if (parser.getAttributeName(i) == "name") {
                    val langTag = parser.getAttributeValue(i)
                    val displayName = getDisplayName(langTag)
                    if (displayName.isNotEmpty()) {
                        languages.add(Pair(langTag, displayName))
                    }
                }
            }
        }
        eventType = parser.next()
    }

    languages.sortBy { it.second }
    languages.add(0, Pair("", getString(R.string.system)))

    return languages.toMap()
}

private fun Context.getCurrentLocaleString(): String {
    val locales = AppCompatDelegate.getApplicationLocales()
    if (locales == LocaleListCompat.getEmptyLocaleList()) {
        return getString(R.string.system)
    }
    return getDisplayName(locales.toLanguageTags())
}

private fun getDisplayName(lang: String?): String {
    if (lang == null) {
        return ""
    }

    val locale = when (lang) {
        "" -> LocaleListCompat.getAdjustedDefault()[0]
        else -> Locale.forLanguageTag(lang)
    }
    return locale!!.getDisplayName(locale).replaceFirstChar { it.uppercase(locale) }
}
