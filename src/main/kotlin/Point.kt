/**
 * All derived classes have to have no-arg constructor, that creates point with zero coordinate
 * All derived classes have to have [generateNewPoint] fun, that returns its own class
 */
abstract class Point {
    abstract fun generateNewPoint(gen: (m: Double) -> Double): Point
}

class Point2D(val x: Double = 0.0, val y: Double = 0.0): Point() {
    override fun generateNewPoint(gen: (m: Double) -> Double): Point2D {
        return Point2D(gen(x), gen(y))
    }

    override fun toString(): String {
        return "Point2D(x: $x, y: $y)"
    }
}

class Point1D(val x: Double = 0.0): Point() {
    override fun generateNewPoint(gen: (m: Double) -> Double): Point1D {
        return Point1D(gen(x))
    }

    override fun toString(): String {
        return "Point1D(x: $x)"
    }
}

class Point3D(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0): Point() {
    override fun generateNewPoint(gen: (m: Double) -> Double): Point3D {
        return Point3D(gen(x), gen(y), gen(z))
    }
}

class IncorrectPointImplementation: Exception("Incorrect implementation of point class")