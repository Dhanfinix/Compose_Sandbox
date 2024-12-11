package edts.android.composesandbox.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString

object StringUtil {
    /**
     * AI Generated extension function to highlight the string in @param color
     * if it contains the query
     */
    fun String.highlightQuery(
        query: String,
        color: Color = Color.Yellow
    ): AnnotatedString {
        // If the original string or the query is empty, return the original string as an AnnotatedString
        if (this.isEmpty() || query.isEmpty()) return AnnotatedString(this)

        // Convert both the original string and the query to lowercase for case-insensitive matching
        val lowercaseText = this.lowercase()
        val lowercaseQuery = query.lowercase()

        return buildAnnotatedString {
            var startIndex = 0 // Start position for searching the query within the string
            var index: Int     // Variable to store the index of the found query

            // Loop through the original string to find all occurrences of the query
            while (startIndex < this@highlightQuery.length) {
                // Find the index of the next occurrence of the query starting from startIndex
                index = lowercaseText.indexOf(lowercaseQuery, startIndex)

                // If the query is not found, append the remaining part of the string and exit the loop
                if (index == -1) {
                    append(this@highlightQuery.substring(startIndex))
                    break
                }

                // Append the part of the string before the found query
                append(this@highlightQuery.substring(startIndex, index))

                // Push the style for highlighting (e.g., yellow background)
                pushStyle(SpanStyle(background = color))
                // Append the found query with the highlighted style
                append(this@highlightQuery.substring(index, index + query.length))
                // Pop the highlighted style to revert to the previous style
                pop()

                // Move the startIndex to the end of the found query for the next iteration
                startIndex = index + query.length
            }
        }
    }

}