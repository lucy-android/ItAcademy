package site.budanitskaya.todolist.secondscreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateUtils
import android.view.*
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.databinding.FragmentSecondBinding
import java.util.*


class SecondFragment : MvpAppCompatFragment(), SecondScreenView {

    @InjectPresenter
    lateinit var presenter: SecondScreenPresenter
    private lateinit var args: SecondFragmentArgs
    private lateinit var binding: FragmentSecondBinding

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
            val task = Task()
            presenter.loadTask(task)
        }

        setInitialDateTime()

        binding.timeButton.setOnClickListener {
            presenter.setTime(requireContext())
        }

        binding.dateButton.setOnClickListener {
            presenter.setDate(requireContext())
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
                    presenter.insertTask(
                        binding.enterTaskName.text.toString(),
                        binding.describeTask.text.toString(),
                        "Deadline date: ${binding.currentDateTime.text as String}"
                    )

                } else if (binding.enterTaskName.text.toString() != "" && binding.describeTask.text.toString() != "" && !args.isNew!!) {
                    presenter.updateTask(
                        binding.enterTaskName.text.toString(),
                        binding.describeTask.text.toString(),
                        "Deadline date: ${binding.currentDateTime.text as String}"
                    )
                }
            }
        }
        return false
    }

    override fun onTaskSaved() {
        findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
    }

    override fun onTaskOpened(task: Task) {
        binding.enterTaskName.setText(task.taskTitle)
        binding.describeTask.setText(task.taskDescription)
        binding.currentDateTime.text = task.dateAndTime
    }

    override fun setInitialDateTime() {
        binding.currentDateTime.text = presenter.formatDateTime(requireContext())
    }


}