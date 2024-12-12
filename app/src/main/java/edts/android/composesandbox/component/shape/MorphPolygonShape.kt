package edts.android.composesandbox.component.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.toPath

/**
 * @see <a href="https://developer.android.com/develop/ui/compose/graphics/draw/shapes#morph-button">Documentation</a>
 */

class MorphPolygonShape(
    private val morph: Morph,
    private val percentage: Float
): Shape{
    private val matrix = Matrix()
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // to maintains the aspect ration between shape morph
        matrix.scale(size.width/2f, size.height/2f)
        // to ensures the shape is properly centered
        matrix.translate(1f, 1f)

        val path = morph.toPath(progress = percentage).asComposePath()
        path.transform(matrix)
        return Outline.Generic(path)
    }
}