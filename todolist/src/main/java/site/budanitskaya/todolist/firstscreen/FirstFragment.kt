package site.budanitskaya.todolist.firstscreen

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.adapter.ToDoListAdapter
import site.budanitskaya.todolist.database.TaskDatabase
import javax.security.auth.callback.Callback


class FirstFragment : Fragment() {

    var actionMode: ActionMode? = null
    val LOG_TAG = "myLogs"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val taskDataBase = TaskDatabase.getInstance(requireContext())

        val taskDatabaseDao = taskDataBase.taskDao()!!

        Log.d("", "onCreateView: $taskDatabaseDao")

        val taskList = taskDatabaseDao.getTaskList()
        val adapter = ToDoListAdapter(
            taskList
        ) {
            when (actionMode) {
                null -> {
                    // Start the CAB using the ActionMode.Callback defined above
                    actionMode = activity?.startActionMode(actionModeCallback)
                    view.isSelected = true
                    true
                }
                else -> false
            }

            return@ToDoListAdapter true


        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.context, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private val actionModeCallback = object : ActionMode.Callback {
        // Called when the action mode is created; startActionMode() was called
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.context, menu)
            return true
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            TODO()
        }

        // Called when the user exits the action mode
        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }
}