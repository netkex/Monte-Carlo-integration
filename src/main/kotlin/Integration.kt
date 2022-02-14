const val intN = 1e6.toInt()

/**
 * @param [f] function to integrate
 * @param [p] distribution function
 * @param [N] number of samples
 */
fun integrate2D(
    f: (pnt: Point2D) -> Double,
    p: (pnt: Point2D) -> Double,
    N: Int = intN): Double {
    val pts = generatePoints(
        Point2D(nextGaussian(), nextGaussian()),
        p).take(N)
    return 1.0 / N * pts.map { f(it) / p(it) }.sum()
}

