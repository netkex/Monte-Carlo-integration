const val intN = 1e6.toInt()

/**
 * @param [f] function to integrate
 * @param [p] distribution function
 * @param [N] number of samples
 */
fun integrate2D(
    f: (pnt: Point2D) -> Double,
    p: (pnt: Point2D) -> Double,
    firstPoint: Point2D? = null,
    N: Int = intN): Double {
    if (firstPoint != null && p(firstPoint) == 0.0) {
        throw Exception("First point in sequence cannot have 0 distribution")
    }
    val startPoint = if (firstPoint == null) {
        var curPoint = Point2D(nextGaussian(), nextGaussian())
        while (p(curPoint) == 0.0) {
            curPoint = Point2D(nextGaussian(), nextGaussian())
        }
        curPoint
    } else {
        firstPoint
    }

    val pts = generatePoints(
        startPoint,
        p).take(N)
    return 1.0 / N * pts.map { f(it) / p(it) }.sum()
}

