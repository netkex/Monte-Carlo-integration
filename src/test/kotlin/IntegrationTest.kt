import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.*

internal class IntegrateTest {
    private val eps = 1e-2
    private val iterations = 1e6.toInt()

    private fun assertEqualsEps(expected: Double, actual: Double, epsilon: Double) {
        assertTrue(
            abs(expected - actual) < min(epsilon, expected * epsilon),
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

}