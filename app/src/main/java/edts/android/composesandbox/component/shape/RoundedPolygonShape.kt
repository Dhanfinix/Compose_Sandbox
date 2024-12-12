package edts.android.composesandbox.component.shape

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import kotlin.math.max

/**
 * @see <a href="https://developer.android.com/develop/ui/compose/graphics/draw/shapes#use-polygon">Documentation</a>
 */
fun RoundedPolygon.getBounds() = calculateBounds().let { Rect(it[0], it[1], it[2], it[3]) }
class RoundedPolygonShape(
    private val polygon: RoundedPolygon,
    private var matrix: Matrix = Matrix()
) : Shape {
    private var path = Path()
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        path.rewind()   // clear any previous drawings
        path = polygon.toPath().asComposePath()     // convert shape to compose path
        matrix.reset()  // clear previous transformations
        val bounds = polygon.getBounds()    // get the bounds of the shape
        val maxDimension = max(bounds.width, bounds.height)

        // scale the shape to fit the size
        matrix.scale(size.width / maxDimension, size.height / maxDimension)
        // move the shape to correct position
        matrix.translate(-bounds.left, -bounds.top)
        // apply transformation to the path
        path.transform(matrix)
        return Outline.Generic(path)
    }
}