package site.budanitskaya.mvpcalculator

import site.budanitskaya.mvpcalculator.enums.*

interface View {
    fun showText(message: String?)
}

interface Presenter {
    fun onButtonWasClicked(digit: String)
    fun onButtonWasClicked(binaryOperator: BinaryOperator)
    fun onButtonWasClicked(equals: Equals)
    fun onButtonWasClicked(cButton: Other)
    fun onButtonWasClicked(point: Point)
    fun onButtonWasClicked(unaryOperator: UnaryOperator)
    fun onDestroy()
}

interface Repository {
    fun getStringToBeDisplayedOnTheScreen(digit: String): String
    fun getStringToBeDisplayedOnTheScreen(binaryOperator: BinaryOperator): String
    fun getStringToBeDisplayedOnTheScreen(equals: Equals): String
    fun getStringToBeDisplayedOnTheScreen(cButton: Other): String
    fun getStringToBeDisplayedOnTheScreen(point: Point): String
    fun getStringToBeDisplayedOnTheScreen(unaryOperator: UnaryOperator): String
}