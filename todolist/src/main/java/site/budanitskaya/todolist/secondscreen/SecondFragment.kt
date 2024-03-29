package site.budanitskaya.todolist.secondscreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.*
import androidx.annotation.Nullable
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.databinding.FragmentSecondBinding


class SecondFragment : MvpAppCompatFragment(), SecondScreenView {

    @InjectPresenter
    lateinit var presenter: SecondScreenPresenter
    private lateinit var args: SecondFragmentArgs
    private lateinit var binding: FragmentSecondBinding

    private var onTimeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        presenter.setTime(hourOfDay, minute)
    }

    private var onDateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            presenter.setDate(year, monthOfYear, dayOfMonth)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(inflater)
        setHasOptionsMenu(true)

        args = SecondFragmentArgs.fromBundle(requireArguments())
        presenter.loadTask(args.isNew, args.adapterPosition)

        binding.timeButton.setOnClickListener {
            showTimePickerDialog()
        }

        binding.dateButton.setOnClickListener {
            showDatePickerDialog()
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_two_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> {
                if (binding.enterTaskName.text.toString() != "" && binding.describeTask.text.toString() != "") {
                    if (args.isNew) {
                        presenter.insertTask(
                            binding.enterTaskName.text.toString(),
                            binding.describeTask.text.toString(),
                            binding.currentDateTime.text as String,
                            getCheckedRadioButtonText().toInt()
                        )
                    } else if (!args.isNew) {
                        presenter.updateTask(
                            binding.enterTaskName.text.toString(),
                            binding.describeTask.text.toString(),
                            binding.currentDateTime.text as String,
                            getCheckedRadioButtonText().toInt()
                        )
                    }
                }
            }
        }
        return false
    }

    override fun showDatePickerDialog() {
        DatePickerDialog(
            requireContext(), onDateSetListener,
            presenter.setDeadlineYear(),
            presenter.setDeadlineMonth(),
            presenter.setDeadlineDay()
        )
            .show()
    }

    override fun showTimePickerDialog() {
        TimePickerDialog(
            requireContext(), onTimeSetListener,
            presenter.setDeadlineHour(),
            presenter.setDeadlineMinute(), true
        )
            .show()
    }

    override fun updateDateTime() {
        binding.currentDateTime.text = presenter.formatTimeDate(requireContext())
    }

    override fun loadView(title: String, description: String, deadline: String,
                          priority: Int) {
        binding.enterTaskName.setText(title)
        binding.describeTask.setText(description)
        binding.currentDateTime.setText(deadline)
        checkRadioButtonWithText(priority.toString())
    }

    override fun navigateToFirstFragment() {
        findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
    }

    private fun getCheckedRadioButtonText(): String {
        return when {
            binding.rbOne.isChecked -> binding.rbOne.text.toString()
            binding.rbTwo.isChecked -> binding.rbTwo.text.toString()
            binding.rbThree.isChecked -> binding.rbThree.text.toString()
            binding.rbFour.isChecked -> binding.rbFour.text.toString()
            binding.rbFive.isChecked -> binding.rbFive.text.toString()
            else -> "0"
        }
    }

    override fun checkRadioButtonWithText(number: String) {
        when (number) {
            binding.rbOne.text.toString() -> binding.rg.check(R.id.rb_one)
            binding.rbTwo.text.toString() -> binding.rg.check(R.id.rb_two)
            binding.rbThree.text.toString() -> binding.rg.check(R.id.rb_three)
            binding.rbFour.text.toString() -> binding.rg.check(R.id.rb_four)
            binding.rbFive.text.toString() -> binding.rg.check(R.id.rb_five)
        }
    }
}
