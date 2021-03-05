package site.budanitskaya.mvpcalculator.repository

import android.util.Log
import site.budanitskaya.mvpcalculator.Repository
import site.budanitskaya.mvpcalculator.enums.*
import kotlin.math.pow

class CalculatorRepository : Repository {

    var number1: String? = null
    var binaryOperator: BinaryOperator? = null
    var number2: String? = null
    var unaryOperator: UnaryOperator? = null


    private fun trimTrailingZeros(string: String): String {
        var str = string
        while (str.endsWith(".0")) {
            str = str.substring(0, string.length - 2)
        }
        while (str.endsWith("0") && str.contains(".")) {
            str = str.substring(0, string.length - 1);
        }
        return str
    }


    private fun calculate(): String? {

        if (binaryOperator != null && unaryOperator == null && this.number1 != null && this.number2 != null) {
            number1 = when (binaryOperator) {
                BinaryOperator.PLUS -> trimTrailingZeros((this.number1!!.toDouble() + this.number2!!.toDouble()).toString())
                BinaryOperator.MINUS -> trimTrailingZeros((this.number1!!.toDouble() - this.number2!!.toDouble()).toString())
                BinaryOperator.MULTIPLY -> trimTrailingZeros((this.number1!!.toDouble() * this.number2!!.toDouble()).toString())
                BinaryOperator.DIVIDE -> trimTrailingZeros((this.number1!!.toDouble() / this.number2!!.toDouble()).toString())
                BinaryOperator.POW -> (if (this.number1!!.toDouble() > 0 && this.number2!!.toDouble() > 0) {
                    trimTrailingZeros(
                        (this.number1!!.toDouble().pow(this.number2!!.toDouble())).toString()
                    )
                } else "").toString()
                null -> null
            }
        } else if (unaryOperator == UnaryOperator.NEGATE && this.number1 != null && this.number2 == null) {
            this.number1 = "-${this.number1}"
            Log.d("31415", "onCreate: ${this.number1}, $binaryOperator, ${this.number2}")
            return this.number1
        } else if (unaryOperator == UnaryOperator.NEGATE && this.number2 != null) {
            this.number2 = "-${this.number2}"
            Log.d("31415", "onCreate: ${this.number1}, $binaryOperator, ${this.number2}")
            return this.number2
        } else if (unaryOperator == UnaryOperator.SQRT && number1 != null && number2 == null) {
            if (number1?.toDouble()!! > 0) {
                number1 = trimTrailingZeros(kotlin.math.sqrt((number1!!.toDouble())).toString())
                unaryOperator = null
                return number1
            } else {
                number1 = null
                number2 = null
                unaryOperator = null
            }
        }

        Log.d("31415", "onCreate: ${this.number1}, $binaryOperator, ${this.number2}")

        return number1
    }

    override fun getStringToBeDisplayedOnTheScreen(digit: String): String {
        if (number1 == null && binaryOperator == null && number2 == null) {
            number1 = digit
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!
        } else if (number1 != null && binaryOperator == null && number2 == null) {
            number1 += digit
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!
        } else if (number1 != null && binaryOperator != null && number2 == null) {
            number2 = digit
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number2!!
        } else if (number1 != null && binaryOperator != null && number2 != null) {
            number2 += digit
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number2!!
        } else if (number1 != null && this.binaryOperator == null && number2 == null) {
            number1 = digit
            return number1!!
        }

        if (number1 != null && binaryOperator != null && number2 != null) {
            number1 = digit
            binaryOperator = null
            number2 = null
            return digit

        }
        return ""
    }

    override fun getStringToBeDisplayedOnTheScreen(binaryOperator: BinaryOperator): String {

        if (number1 != null && this.binaryOperator == null && number2 == null) {
            this.binaryOperator = binaryOperator
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!

        } else if (number1 != null && this.binaryOperator == null && number2 == null) {
            this.binaryOperator = binaryOperator
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!

        } else if (number1 != null && this.binaryOperator != null && number2 == null) {
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!
        } else if (number1 != null && this.binaryOperator != null && number2 == null) {
            this.binaryOperator = binaryOperator

            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!
        } else if (number1 != null && this.binaryOperator != null && number2 != null) {
            number1 = calculate()
            this.binaryOperator = binaryOperator
            number2 = null
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!

        } else if (number1 != null && this.binaryOperator != null && number2 != null) {
            this.binaryOperator = binaryOperator
            number2 = null
            Log.d("31415", "$number1, $this.binaryOperator, $number2")
            return number1!!
        } else if (number1 != null && this.binaryOperator == null && number2 == null) {
            return number1!!
        }

        Log.d("31415", "$number1, $binaryOperator, $number2")

        return ""
    }

    override fun getStringToBeDisplayedOnTheScreen(equals: Equals): String {

        if (number1 != null && binaryOperator == null && number2 == null) {
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!
        } else if (number1 != null && binaryOperator != null && number2 == null) {
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!
        } else if (number1 != null && binaryOperator != null && number2 != null) {
            number1 = calculate()
            binaryOperator = null
            number2 = null

            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!
        }
        return ""
    }

    override fun getStringToBeDisplayedOnTheScreen(cButton: Other): String {
        number1 = null
        binaryOperator = null
        number2 = null
        Log.d("31415", "$number1, $binaryOperator, $number2")
        return ""
    }

    override fun getStringToBeDisplayedOnTheScreen(point: Point): String {
        if(number1 == null) {
            number1 = "0."
        }

        if (number1 != null && !number1!!.contains(".") && binaryOperator == null && number2 == null) {
            number1 += "."
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!
        } else if (number1 != null && binaryOperator != null && number2 != null && !number2!!.contains(".")) {
            number2 += "."
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number2!!
        }

        Log.d("31415", "$number1, $binaryOperator, $number2")
        return ""
    }

    override fun getStringToBeDisplayedOnTheScreen(unaryOperator: UnaryOperator): String {
        if (number1 != null && this.binaryOperator == null && number2 == null) {
            this.unaryOperator = unaryOperator
            number1 = calculate()
            this.unaryOperator = null
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number1!!
        } else if (number1 != null && this.binaryOperator != null && number2 != null) {
            this.unaryOperator = unaryOperator
            number2 = calculate()
            this.unaryOperator = null
            Log.d("31415", "$number1, $binaryOperator, $number2")
            return number2!!
        }
        return ""
    }
}

