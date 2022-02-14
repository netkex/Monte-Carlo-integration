import java.util.Random

fun nextGaussian(mean: Double = 0.0, std: Double = 1.0): Double {
    return mean + Random().nextGaussian() * std
}

private fun nextStep(oldPnt: Point2D, p: (pnt: Point2D) -> Double): Point2D {
    val newPnt = Point2D(nextGaussian(oldPnt.x), nextGaussian(oldPnt.y))
    val acceptRatio = p(newPnt) / p(oldPnt)
    return if (Random().nextDouble() <= acceptRatio) newPnt else oldPnt
}

fun generatePoints(startPoint: Point2D, p: (pnt: Point2D) -> Double) =
    generateSequence(startPoint) {
        nextStep(it, p)
    }