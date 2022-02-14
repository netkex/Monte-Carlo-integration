import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.*

internal class IntegrateTest {
    private val eps = 1e-2
    private val iterations = 1e6.toInt()

    private fun assertEqualsEps(expected: Double, actual: Double, epsilon: Double) {
        assertTrue(
            abs(expected - actual) < max(epsilon, expected * epsilon),
            "expected <$expected>, actual <$actual>, eps <$epsilon>, \n" +
                    "relative eps <${expected * epsilon}>, diff <${abs(expected - actual)}>")
    }

    private fun pStandard(pnt: Point2D): Double {
        return 1 / (2 * PI) * exp((-pnt.x.pow(2) - pnt.y.pow(2)) / 2)
    }

    @Test
    fun `integral from test task` () {
        fun fTest(pnt: Point2D): Double {
            return if (pnt.x < 0 || pnt.y < 0) 0.0 else exp(-pnt.x.pow(2) - pnt.y.pow(2))
        }

        val res = PI / 4
        assertEqualsEps(res, integrate2D(::fTest, ::pStandard, N=iterations), eps)
    }

    @Test
    fun `integral half of sphere`() {
        fun fTest(pnt: Point2D): Double {
            val r = pnt.x.pow(2) + pnt.y.pow(2)
            return if (r < 1) sqrt(1 - r) else 0.0
        }

        val res = 2 * PI / 3
        assertEqualsEps(res, integrate2D(::fTest, ::pStandard, N=iterations), eps)
    }

    @Test
    fun `integral with non-standard deviation`() {
        fun fTest(pnt: Point2D): Double {
            return if (abs(pnt.x) > 1 || abs(pnt.y) > 1) 0.0 else pnt.x.pow(3) + 5 * pnt.y.pow(2)* pnt.x + 3
        }

        fun pTest(pnt: Point2D): Double {
            return if (abs(pnt.x) > 2 || abs(pnt.y) > 2) 0.0 else 1.0 / 16
        }

        val res = 12.0
        assertEqualsEps(res, integrate2D(::fTest, ::pTest, N=iterations), eps)
    }

    @Test
    fun `integral with non-standard deviation not centered at 0`() {
        fun fTest(pnt: Point2D): Double {
            return if (abs(pnt.x - 5) > 1 || abs(pnt.y - 3) > 1) 0.0
            else (pnt.x - 5).pow(3) + 5 * (pnt.y - 3).pow(2)* (pnt.x - 5) + 3
        }

        fun pTest(pnt: Point2D): Double {
            return if (abs(pnt.x - 5) > 2 || abs(pnt.y - 3) > 2) 0.0 else 1.0 / 16
        }

        val res = 12.0
        assertEqualsEps(res, integrate2D(::fTest, ::pTest, firstPoint=Point2D(5.0, 3.0), N=iterations), eps)
    }

}