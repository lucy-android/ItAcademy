package site.budanitskaya.todolist.firstscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.adapter.ToDoListAdapter
import site.budanitskaya.todolist.database.TaskDatabase


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val taskDataBase = TaskDatabase.getInstance(requireContext())

        val taskDatabaseDao = taskDataBase.taskDao()

        val taskList = taskDatabaseDao?.getTaskList()

/*        val adapter = ToDoListAdapter()*/


        return view
    }
}