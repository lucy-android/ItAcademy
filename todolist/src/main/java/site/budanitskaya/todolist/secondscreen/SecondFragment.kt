package site.budanitskaya.todolist.secondscreen

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
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


class SecondFragment : Fragment() {

    private lateinit var secondFragmentView: View
    private lateinit var enterTaskName: EditText
    private lateinit var describeTask: EditText
    private var adapterPosition: Int? = null
    private var isNew : Boolean? = null
    init {
        val args = SecondFragmentArgs.fromBundle(requireArguments())
        isNew = args.isNew
    }

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

        adapterPosition = args.adapterPosition

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

                val task = Task()
                if () {
                    task.taskTitle = enterTaskName.text.toString()
                    task.taskDescription = describeTask.text.toString()

                    TaskList.insertTask(task, adapterPosition!!)
                    findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
                } else if(enterTaskName.text.toString() != "" && describeTask.text.toString() != "" && adapterPosition!! == -1){
                    TaskList.insertTask(task, 0)
                }
            }
        }

        return false
    }
}