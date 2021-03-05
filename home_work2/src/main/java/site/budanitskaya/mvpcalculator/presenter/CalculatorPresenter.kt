package site.budanitskaya.mvpcalculator.presenter

import site.budanitskaya.mvpcalculator.repository.CalculatorRepository
import site.budanitskaya.mvpcalculator.Presenter
import site.budanitskaya.mvpcalculator.Repository
import site.budanitskaya.mvpcalculator.View
import site.budanitskaya.mvpcalculator.enums.*

class CalculatorPresenter(mView: View?) : Presenter {

    private var mView: View? = null
    private var mRepository: Repository? = null

    init {
        this.mView = mView;
        this.mRepository = CalculatorRepository();
    }

    //Сообщение
    private var message: String? = null

    override fun onButtonWasClicked(digit: String) {
        message = mRepository?.getStringToBeDisplayedOnTheScreen(digit)
        mView?.showText(message)
    }

    override fun onButtonWasClicked(binaryOperator: BinaryOperator) {
        message = mRepository?.getStringToBeDisplayedOnTheScreen(binaryOperator)
        mView?.showText(message)
    }

    override fun onButtonWasClicked(equals: Equals) {
        message = mRepository?.getStringToBeDisplayedOnTheScreen(equals)
        mView?.showText(message)
    }

    override fun onButtonWasClicked(cButton: Other) {
        message = mRepository?.getStringToBeDisplayedOnTheScreen(cButton)
        mView?.showText(message)
    }

    override fun onButtonWasClicked(point: Point) {
        message = mRepository?.getStringToBeDisplayedOnTheScreen(point)
        mView?.showText(message)
    }

    override fun onButtonWasClicked(unaryOperator: UnaryOperator) {
        message = mRepository?.getStringToBeDisplayedOnTheScreen(unaryOperator)
        mView?.showText(message)
    }


    override fun onDestroy() {

    }
}