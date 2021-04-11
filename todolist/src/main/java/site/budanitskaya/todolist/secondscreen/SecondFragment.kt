package site.budanitskaya.todolist.secondscreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateUtils
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.firstscreen.FirstScreenPresenter
import site.budanitskaya.todolist.util.TaskList
import java.util.*


class SecondFragment : MvpAppCompatFragment() {

    @InjectPresenter
    lateinit var presenter: SecondScreenPresenter

    private lateinit var secondFragmentView: View
    private lateinit var enterTaskName: EditText
    private lateinit var describeTask: EditText
    private var isNew: Boolean? = null
    private lateinit var task: Task
    private var currentDateTime: TextView? = null
    private var dateAndTime: Calendar = Calendar.getInstance()
    private var adapterPosition: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        secondFragmentView = inflater.inflate(R.layout.fragment_second, container, false)
        enterTaskName = secondFragmentView.findViewById(R.id.enter_task_name)
        describeTask = secondFragmentView.findViewById(R.id.describe_task)

        val args = SecondFragmentArgs.fromBundle(requireArguments())
        isNew = args.isNew
        adapterPosition = args.adapterPosition

        if (!isNew!!) {
            task = TaskList.taskList[adapterPosition!!]
            enterTaskName.setText(task.taskTitle)
            describeTask.setText(task.taskDescription)
        } else {
            task = Task()
        }

        currentDateTime = secondFragmentView.findViewById<View>(R.id.current_date_time) as TextView
        setInitialDateTime()

        val timeButton = secondFragmentView.findViewById<Button>(R.id.timeButton)

        timeButton.setOnClickListener {
            setTime(secondFragmentView)
        }

        val dateButton = secondFragmentView.findViewById<Button>(R.id.dateButton)
        dateButton.setOnClickListener {
            setDate(secondFragmentView)
        }
        return secondFragmentView
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
                if (enterTaskName.text.toString() != "" && describeTask.text.toString() != "" && this.isNew!!) {
                    task.taskTitle = enterTaskName.text.toString()
                    task.taskDescription = describeTask.text.toString()
                    task.dateAndTime = "Deadline date: ${currentDateTime?.text as String}"

                    TaskList.insertTask(task)
                    findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())

                } else if (enterTaskName.text.toString() != "" && describeTask.text.toString() != "" && !isNew!!) {

                    task.taskTitle = enterTaskName.text.toString()
                    task.taskDescription = describeTask.text.toString()
                    task.dateAndTime = "Due date: ${currentDateTime?.text as String}"

                    TaskList.updateTask(this.task)
                    findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
                }
            }
        }
        return false
    }

    private fun setDate(v: View?) {
        DatePickerDialog(
            requireContext(), date,
            dateAndTime[Calendar.YEAR],
            dateAndTime[Calendar.MONTH],
            dateAndTime[Calendar.DAY_OF_MONTH]
        )
            .show()
    }

    private fun setTime(v: View?) {
        TimePickerDialog(
            requireContext(), time,
            dateAndTime[Calendar.HOUR_OF_DAY],
            dateAndTime[Calendar.MINUTE], true
        )
            .show()
    }

    private fun setInitialDateTime() {
        currentDateTime!!.text = DateUtils.formatDateTime(
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
}