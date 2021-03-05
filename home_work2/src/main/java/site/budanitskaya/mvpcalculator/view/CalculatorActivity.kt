package site.budanitskaya.mvpcalculator.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import site.budanitskaya.mvpcalculator.Presenter
import site.budanitskaya.mvpcalculator.R
import site.budanitskaya.mvpcalculator.databinding.ActivityMainBinding
import site.budanitskaya.mvpcalculator.enums.*
import site.budanitskaya.mvpcalculator.presenter.CalculatorPresenter


class CalculatorActivity : AppCompatActivity(), site.budanitskaya.mvpcalculator.View {

    lateinit var binding: ActivityMainBinding

    private lateinit var mPresenter: Presenter
    var clickListener: View.OnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPresenter = CalculatorPresenter(this);
    }

    override fun onStart() {
        super.onStart()
        clickListener = View.OnClickListener { binding ->
            when (binding.id) {
                R.id.oneBtn -> mPresenter.onButtonWasClicked("1")
                R.id.twoBtn -> mPresenter.onButtonWasClicked("2")
                R.id.threeBtn -> mPresenter.onButtonWasClicked("3")
                R.id.fourBtn -> mPresenter.onButtonWasClicked("4")
                R.id.fiveBtn -> mPresenter.onButtonWasClicked("5")
                R.id.sixBtn -> mPresenter.onButtonWasClicked("6")
                R.id.sevenBtn -> mPresenter.onButtonWasClicked("7")
                R.id.eightBtn -> mPresenter.onButtonWasClicked("8")
                R.id.nineBtn -> mPresenter.onButtonWasClicked("9")
                R.id.zeroBtn -> mPresenter.onButtonWasClicked("0")
                R.id.plusBtn -> mPresenter.onButtonWasClicked(BinaryOperator.PLUS)
                R.id.minusBtn -> mPresenter.onButtonWasClicked(BinaryOperator.MINUS)
                R.id.multiplyBtn -> mPresenter.onButtonWasClicked(BinaryOperator.MULTIPLY)
                R.id.divideBtn -> mPresenter.onButtonWasClicked(BinaryOperator.DIVIDE)
                R.id.powBtn -> mPresenter.onButtonWasClicked(BinaryOperator.POW)
                R.id.equalsBtn -> mPresenter.onButtonWasClicked(Equals.EQUALS)
                R.id.cBtn -> mPresenter.onButtonWasClicked(Other.C)
                R.id.pointBtn -> mPresenter.onButtonWasClicked(Point.POINT)
                R.id.sqrtBtn -> mPresenter.onButtonWasClicked(UnaryOperator.SQRT)
                R.id.negateBtn -> mPresenter.onButtonWasClicked(UnaryOperator.NEGATE)
            }
        }


        binding.oneBtn.setOnClickListener(clickListener)
        binding.twoBtn.setOnClickListener(clickListener)
        binding.threeBtn.setOnClickListener(clickListener)
        binding.fourBtn.setOnClickListener(clickListener)
        binding.fiveBtn.setOnClickListener(clickListener)
        binding.sixBtn.setOnClickListener(clickListener)
        binding.sevenBtn.setOnClickListener(clickListener)
        binding.eightBtn.setOnClickListener(clickListener)
        binding.nineBtn.setOnClickListener(clickListener)
        binding.zeroBtn.setOnClickListener(clickListener)
        binding.plusBtn.setOnClickListener(clickListener)
        binding.minusBtn.setOnClickListener(clickListener)
        binding.multiplyBtn.setOnClickListener(clickListener)
        binding.divideBtn.setOnClickListener(clickListener)
        binding.sqrtBtn.setOnClickListener(clickListener)
        binding.powBtn.setOnClickListener(clickListener)
        binding.cBtn.setOnClickListener(clickListener)
        binding.equalsBtn.setOnClickListener(clickListener)
        binding.pointBtn.setOnClickListener(clickListener)
        binding.negateBtn.setOnClickListener(clickListener)
    }

    override fun onStop() {
        super.onStop()
        if (clickListener != null) clickListener = null
    }

    override fun showText(message: String?) {
        binding.etTxt.setText(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }
}
