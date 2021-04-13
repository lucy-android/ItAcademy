package site.budanitskaya.todolist.secondscreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateUtils
import android.view.*
import androidx.annotation.Nullable
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.databinding.FragmentSecondBinding
import site.budanitskaya.todolist.util.TaskDataSource
import java.util.*


class SecondFragment : MvpAppCompatFragment(), SecondScreenView {

    @InjectPresenter
    lateinit var presenter: SecondScreenPresenter
    private lateinit var args: SecondFragmentArgs
    private lateinit var binding: FragmentSecondBinding
    private var dateAndTime: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(inflater)
        setHasOptionsMenu(true)

        args = SecondFragmentArgs.fromBundle(requireArguments())
        presenter.loadTask(args.isNew, args.adapterPosition)

        setInitialDateTime()

        binding.timeButton.setOnClickListener {
            setTime()
        }

        binding.dateButton.setOnClickListener {
            setDate()
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
                            binding.currentDateTime.text as String
                        )
                    } else if (!args.isNew) {
                        presenter.updateTask(
                            binding.enterTaskName.text.toString(),
                            binding.describeTask.text.toString(),
                            binding.currentDateTime.text as String
                        )
                    }
                }
            }
        }
        return false
    }

    private fun setDate() {
        DatePickerDialog(
            requireContext(), date,
            dateAndTime[Calendar.YEAR],
            dateAndTime[Calendar.MONTH],
            dateAndTime[Calendar.DAY_OF_MONTH]
        )
            .show()
    }

    private fun setTime() {
        TimePickerDialog(
            requireContext(), time,
            dateAndTime[Calendar.HOUR_OF_DAY],
            dateAndTime[Calendar.MINUTE], true
        )
            .show()
    }

    private fun setInitialDateTime() {
        binding.currentDateTime.text = DateUtils.formatDateTime(
            requireContext(),
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                    or DateUtils.FORMAT_SHOW_TIME
        )
    }

    private var time = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        dateAndTime[Calendar.HOUR_OF_DAY] = hourOfDay
        dateAndTime[Calendar.MINUTE] = minute
        setInitialDateTime()
    }

    private var date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        dateAndTime[Calendar.YEAR] = year
        dateAndTime[Calendar.MONTH] = monthOfYear
        dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
        setInitialDateTime()
    }

    override fun loadView(title: String, description: String, deadline: String) {
        binding.enterTaskName.setText(title)
        binding.describeTask.setText(description)
        binding.currentDateTime.setText(deadline)
    }

    override fun onTaskUpdated() {
        findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
    }

    override fun onTaskInserted() {
        findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
    }
}