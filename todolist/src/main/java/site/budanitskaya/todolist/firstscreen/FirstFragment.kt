package site.budanitskaya.todolist.firstscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        val taskDatabaseDao = taskDataBase.taskDao() !!

        Log.d("" ,"onCreateView: $taskDatabaseDao")

        val taskList = taskDatabaseDao.getTaskList()
        val adapter = ToDoListAdapter(taskList
        ) {

            Toast.makeText(requireContext(), "$it", Toast.LENGTH_LONG).show()
            return@ToDoListAdapter true
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return view
    }
}