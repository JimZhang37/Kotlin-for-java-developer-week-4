package rationals


import java.math.BigInteger
import kotlin.math.absoluteValue
import kotlin.math.sign


class Rational(var numerator: BigInteger, var denominator: BigInteger) : Comparable<Rational> {





    init {


        if (denominator.signum() == -1) {
            denominator = -denominator
            numerator = -numerator
        }
    }

    fun gcm(a: BigInteger, b: BigInteger): BigInteger {
        return if (b == 0.toBigInteger()) a else gcm(b, a % b) // Not bad for one line of code :)
    }

    fun asFraction(a: BigInteger, b: BigInteger): String {
        val gcm = gcm(a, b)

        return (a / gcm).toString() + "/" + b / gcm
    }

    private fun simplify(v1: BigInteger, v2: BigInteger) {
        //println("simplify")
        var j = if (v1.abs() > v2) v2 else v1.abs()


        while (j > 1.toBigInteger()) {
            if (this.numerator % j == 0.toBigInteger() && this.denominator % j == 0.toBigInteger()) {

                this.numerator /= j
                this.denominator /= j
                break
            }
            j--
            //println(j--)
        }


    }


    override fun equals(other: Any?): Boolean {

        if (other is Rational) {
            if (this.numerator.signum() != other.numerator.signum())
                return false

            if (this.numerator * other.denominator == other.numerator * this.denominator)
                return true
        }

        return false
    }

    /***
     * compareTo is used to support >, <, >=, <=
     */
    override operator fun compareTo(other: Rational): Int {
        var i = 0
        when {
            (this - other).numerator > 0.toBigInteger() -> i = 1
            (this - other).numerator == 0.toBigInteger() -> i = 0
            (this - other).numerator < 0.toBigInteger() -> i = -1
        }
        return i
    }

    override fun toString(): String {
        val gcm = gcm(this.numerator.abs(), this.denominator.abs())

        if (this.denominator != gcm) return (this.numerator / gcm).toString() + '/' + (this.denominator / gcm).toString()

        return (this.numerator / gcm).toString()
    }

    operator fun times(a: Rational): Rational {
        return Rational(this.numerator * a.numerator, this.denominator * a.denominator)
    }

    /***
     *
     */
    operator fun plus(a: Rational): Rational {

        return Rational(this.numerator * a.denominator + this.denominator * a.numerator, this.denominator * a.denominator)
    }

    /***
     * minus is used to support -
     */
    operator fun minus(a: Rational): Rational {
        return Rational(this.numerator * a.denominator - this.denominator * a.numerator, this.denominator * a.denominator)

    }

    /***
     * div is used to support /
     */
    operator fun div(a: Rational): Rational {
        return Rational(this.numerator * a.denominator, this.denominator * a.numerator)
    }

    /***
     * unaryMinus is used to support -x, x-
     */
    operator fun unaryMinus(): Rational {
        return Rational(-this.numerator, this.denominator)
    }

//    /***
//     *
//     */
//    operator fun iterator(): Rational {
//        return Rational(1, 1)
//    }


    /***
     * rangeTo is used to support ..
     */
    operator fun rangeTo(a: Rational): RationalRange {

        return RationalRange(this, a)
    }


}

class RationalRange(private val start: Rational, private val end: Rational) {
    /***
     * contains is used to support in
     */
    operator fun contains(a: Rational): Boolean {
        if (a >= start && a <= end) return true
        return false
    }
}

fun String.toRational(): Rational {
    val stringArray = this.split("/")

    if (stringArray.size == 2) {
        val a = stringArray[0].toBigInteger() divBy stringArray[1].toBigInteger()

        return a
    }
    if (stringArray.size == 1) {
        val a = stringArray[0].toBigInteger() divBy 1.toBigInteger()

        return a
    }
    return Rational(1.toBigInteger(),1.toBigInteger())
}

infix fun Int.divBy(b: Int): Rational {
    val a: Rational = Rational(this.toBigInteger(), b.toBigInteger())
    return a
}

infix fun Long.divBy(b: Long): Rational {
    return Rational(this.toBigInteger(), b.toBigInteger())


}


infix fun BigInteger.divBy(b: BigInteger): Rational {

    return Rational(this, b)

}


fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}