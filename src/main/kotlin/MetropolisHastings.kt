import java.util.Random

fun nextGaussian(mean: Double = 0.0, std: Double = 1.0): Double {
    return mean + Random().nextGaussian() * std
}

private fun <T: Point> nextStep(oldPnt: T, p: (pnt: T) -> Double): T {
    val newPnt = try {
        oldPnt.generateNewPoint(::nextGaussian) as T
    } catch (e: Exception) {
        throw IncorrectPointImplementation()
    }

    val acceptRatio = p(newPnt) / p(oldPnt)
    return if (Random().nextDouble() <= acceptRatio) newPnt else oldPnt
}

fun <T: Point> generatePoints(startPoint: T, p: (pnt: T) -> Double) =
    generateSequence(startPoint) {
        nextStep(it, p)
    }