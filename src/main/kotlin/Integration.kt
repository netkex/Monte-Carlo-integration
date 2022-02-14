import kotlin.reflect.full.createInstance

const val intN = 1e6.toInt()

/**
 * @param [f] function to integrate
 * @param [p] distribution function
 * @param [firstPoint] first point of samples in Monte-Carlo algorithm (optional)
 * @param  [N] number of samples (optional)
 */
inline fun<reified T: Point> integrate(
    noinline f: (pnt: T) -> Double,
    noinline p: (pnt: T) -> Double,
    firstPoint: T? = null,
    N: Int = intN): Double {
    if (firstPoint != null && p(firstPoint) == 0.0) {
        throw Exception("First point in sequence cannot have 0 distribution")
    }
    val startPoint = firstPoint ?: try {
        var curPoint = T::class.createInstance().generateNewPoint(::nextGaussian) as T
        while (p(curPoint) == 0.0) {
            curPoint = curPoint.generateNewPoint(::nextGaussian) as T
        }
        curPoint
    } catch (e: Exception) {
        throw IncorrectPointImplementation()
    }

    val pts = generatePoints(
        startPoint,
        p).take(N)
    return 1.0 / N * pts.map { f(it) / p(it) }.sum()
}

