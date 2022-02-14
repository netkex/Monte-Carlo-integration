@file: JvmName("main")

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.pow
import kotlin.system.measureTimeMillis

data class Point2D(val x: Double, val y: Double)

private fun fTest(pnt: Point2D): Double {
    return if (pnt.x < 0 || pnt.y < 0) 0.0 else exp(-pnt.x.pow(2) - pnt.y.pow(2))
}

private fun pTest(pnt: Point2D): Double {
    return 1 / (2 * PI) * exp((-pnt.x.pow(2) - pnt.y.pow(2)) / 2)
}

fun main() {
    println("Integration exp(-x^2 - y^2) when x >= 0 & y >= 0")

    val time = measureTimeMillis {
        val mcres = integrate2D(::fTest, ::pTest)
        val res = PI / 4
        val diff = abs(mcres - res)
        println("Monte-Carlo res: $mcres   Actual res: $res  Abs. err: $diff  Rel. err: ${diff / res}")
    }

    println("Time in millis: $time")
}
