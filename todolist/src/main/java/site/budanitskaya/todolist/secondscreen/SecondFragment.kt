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

        if (!args.isNew) {

            presenter.prepareTaskOpen(args.adapterPosition)


        } else {
            presenter.task = Task()
        }

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> {
                if (binding.enterTaskName.text.toString() != "" && binding.describeTask.text.toString() != "" && args.isNew!!) {
                    presenter.insertTask()

                } else if (binding.enterTaskName.text.toString() != "" && binding.describeTask.text.toString() != "" && !args.isNew!!) {
                    presenter.updateTask()
                }
            }
        }
        return false
    }

    override fun onTaskInserted() {
        presenter.task.taskTitle = binding.enterTaskName.text.toString()
        presenter.task.taskDescription = binding.describeTask.text.toString()
        presenter.task.dateAndTime = "Deadline date: ${binding.currentDateTime.text as String}"
        findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
    }

    override fun onTaskUpdated() {
        presenter.task.taskTitle = binding.enterTaskName.text.toString()
        presenter.task.taskDescription = binding.describeTask.text.toString()
        presenter.task.dateAndTime = "Deadline date: ${binding.currentDateTime.text as String}"
        findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
    }

    override fun onTaskOpened(task: Task) {
        binding.enterTaskName.setText(task.taskTitle)
        binding.describeTask.setText(task.taskDescription)
    }

    private fun setInitialDateTime() {
        binding.currentDateTime.text = DateUtils.formatDateTime(
            requireContext(),
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                    or DateUtils.FORMAT_SHOW_TIME
        )
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
}