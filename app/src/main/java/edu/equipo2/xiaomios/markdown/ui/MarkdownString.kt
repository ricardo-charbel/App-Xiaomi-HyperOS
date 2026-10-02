package edu.equipo2.xiaomios.markdown.ui

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import edu.equipo2.xiaomios.markdown.parser.MarkdownInline

fun BuildMarkdownString(content:List<MarkdownInline>): AnnotatedString{
    val annotatedString = buildAnnotatedString {
        content.forEach {inline ->
            when(inline){
                // Texto Normal
                is MarkdownInline.Text -> {
                    append(inline.value)
                }

                // Texto en Negritas e Inclinado
                is MarkdownInline.BoldItalic -> {
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    ){
                        append(inline.value)
                    }
                }

                // Texto En Negritas
                is MarkdownInline.Bold -> {
                    withStyle(
                        SpanStyle(fontWeight = FontWeight.Bold)
                    ){
                        append(inline.value)
                    }
                }

                // Texto Inclinado
                is MarkdownInline.Italic -> {
                    withStyle(
                        SpanStyle(fontStyle = FontStyle.Italic)
                    ){
                        append(inline.value)
                    }
                }

                // Código
                is MarkdownInline.Code -> {
                    withStyle(
                        SpanStyle(fontFamily = FontFamily.Monospace)
                    ){
                        append(inline.value)
                    }
                }

                // Link
                is MarkdownInline.Link -> {
                    withStyle(
                        SpanStyle(textDecoration = TextDecoration.Underline)
                    ){
                        withLink(
                            LinkAnnotation.Url(
                                url = inline.url
                            )
                        ){
                            append(inline.text)
                        }
                    }
                }
            }
        }
    }
    return annotatedString
}