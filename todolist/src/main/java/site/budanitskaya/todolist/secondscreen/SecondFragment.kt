package site.budanitskaya.todolist.secondscreen

import android.os.Bundle
import android.view.*
import android.widget.EditText

import androidx.annotation.Nullable
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import site.budanitskaya.todolist.MainActivity
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase


class SecondFragment : Fragment() {

    private lateinit var secondFragmentView: View
    private lateinit var enterTaskName: EditText
    private lateinit var describeTask: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        secondFragmentView = inflater.inflate(R.layout.fragment_second, container, false)
        enterTaskName = secondFragmentView.findViewById(R.id.enter_task_name)

        describeTask = secondFragmentView.findViewById(R.id.describe_task)

        return secondFragmentView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_items, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar) as Toolbar
        (activity as MainActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.save -> {
                val taskDataBase = TaskDatabase.getInstance(requireContext())

                val taskDatabaseDao = taskDataBase.taskDao()

                val task = Task()
                task.taskTitle = enterTaskName.text.toString()
                task.taskDescription = describeTask.text.toString()

                taskDatabaseDao?.insert(task)

                findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
            }

        }

        return false
    }
}