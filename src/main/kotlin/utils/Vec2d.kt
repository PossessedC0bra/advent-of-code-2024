package utils

//  ___       _ __     __        ____     _
// |_ _|_ __ | |\ \   / /__  ___|___ \ __| |
//  | || '_ \| __\ \ / / _ \/ __| __) / _` |
//  | || | | | |_ \ V /  __/ (__ / __/ (_| |
// |___|_| |_|\__| \_/ \___|\___|_____\__,_|

data class IntVec2d(val x: Int, val y: Int) {

    operator fun plus(other: IntVec2d) = IntVec2d(x + other.x, y + other.y)

    operator fun unaryMinus() = IntVec2d(-x, -y)
    operator fun minus(other: IntVec2d) = IntVec2d(x - other.x, y - other.y)

    operator fun times(b: Int) = IntVec2d(x * b, y * b)

    operator fun div(b: Int) = IntVec2d(x / b, y / b)

    fun wrap(width: Int, height: Int): IntVec2d {
        val wrappedX = x % width
        val wrappedY = y % height

        return IntVec2d(
            if (wrappedX < 0) wrappedX + width else wrappedX,
            if (wrappedY < 0) wrappedY + height else wrappedY
        )
    }
}

operator fun Int.times(other: LongVec2d) = LongVec2d(this * other.x, this * other.y)

//#region LongVec2d
//  _                    __     __        ____     _
// | |    ___  _ __   __ \ \   / /__  ___|___ \ __| |
// | |   / _ \| '_ \ / _` \ \ / / _ \/ __| __) / _` |
// | |__| (_) | | | | (_| |\ V /  __/ (__ / __/ (_| |
// |_____\___/|_| |_|\__, | \_/ \___|\___|_____\__,_|
//                   |___/


data class LongVec2d(val x: Long, val y: Long) {

    operator fun plus(other: LongVec2d) = LongVec2d(x + other.x, y + other.y)

    operator fun unaryMinus() = LongVec2d(-x, -y)
    operator fun minus(other: LongVec2d) = LongVec2d(x - other.x, y - other.y)

    operator fun times(b: Int) = LongVec2d(x * b, y * b)

    operator fun div(b: Int) = LongVec2d(x / b, y / b)

    fun wrap(width: Int, height: Int): LongVec2d {
        val wrappedX = x % width
        val wrappedY = y % height

        return LongVec2d(
            if (wrappedX < 0) wrappedX + width else wrappedX,
            if (wrappedY < 0) wrappedY + height else wrappedY
        )
    }
}

operator fun Long.times(other: LongVec2d) = LongVec2d(this * other.x, this * other.y)
