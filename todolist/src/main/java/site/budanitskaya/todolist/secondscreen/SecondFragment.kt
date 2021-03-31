package site.budanitskaya.todolist.secondscreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.annotation.Nullable
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import site.budanitskaya.todolist.MainActivity
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase
import site.budanitskaya.todolist.util.TaskList
import java.util.*


class SecondFragment : Fragment() {

    private lateinit var secondFragmentView: View
    private lateinit var enterTaskName: EditText
    private lateinit var describeTask: EditText
    private var isNew: Boolean? = null
    private lateinit var task: Task


    var currentDateTime: TextView? = null
    var dateAndTime = Calendar.getInstance()

    private var adapterPosition: Int? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
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

        timeButton.setOnClickListener{
            setTime(secondFragmentView)
        }

        val dateButton = secondFragmentView.findViewById<Button>(R.id.dateButton)

        dateButton.setOnClickListener{
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

                    TaskList.insertTask(task)
                    findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
                } else if (enterTaskName.text.toString() != "" && describeTask.text.toString() != "" && !isNew!!) {

                    task.taskTitle = enterTaskName.text.toString()
                    task.taskDescription = describeTask.text.toString()

                    TaskList.updateTask(this.task)
                    findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
                }
            }
        }

        return false
    }


    // отображаем диалоговое окно для выбора даты
    fun setDate(v: View?) {
        DatePickerDialog(requireContext(), d,
            dateAndTime[Calendar.YEAR],
            dateAndTime[Calendar.MONTH],
            dateAndTime[Calendar.DAY_OF_MONTH])
            .show()
    }

    // отображаем диалоговое окно для выбора времени
    fun setTime(v: View?) {
        TimePickerDialog(requireContext(), t,
            dateAndTime[Calendar.HOUR_OF_DAY],
            dateAndTime[Calendar.MINUTE], true)
            .show()
    }

    // установка начальных даты и времени
    private fun setInitialDateTime() {
        currentDateTime!!.text = DateUtils.formatDateTime(requireContext(),
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                    or DateUtils.FORMAT_SHOW_TIME)
    }

    // установка обработчика выбора времени
    var t = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        dateAndTime[Calendar.HOUR_OF_DAY] = hourOfDay
        dateAndTime[Calendar.MINUTE] = minute
        setInitialDateTime()
    }

    // установка обработчика выбора даты
    var d = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        dateAndTime[Calendar.YEAR] = year
        dateAndTime[Calendar.MONTH] = monthOfYear
        dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
        setInitialDateTime()
    }
}