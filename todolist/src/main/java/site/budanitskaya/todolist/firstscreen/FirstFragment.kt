package site.budanitskaya.todolist.firstscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.adapter.ToDoListAdapter
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase


class FirstFragment : Fragment() {

    val LOG_TAG = "myLogs"

    var it: Int? = null

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
            this.it = it

            val actionModeCallback = ActionModeCallBackImpl(requireContext(), it)

            when (actionModeCallback.actionMode) {
                null -> {
                    // Start the CAB using the ActionMode.Callback defined above
                    actionModeCallback.actionMode = activity?.startActionMode(actionModeCallback)
                    view.isSelected = true
                }
            }

            return@ToDoListAdapter true

        }

        view.invalidate()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        recyclerView.post { adapter.notifyDataSetChanged() }

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
}

class ActionModeCallBackImpl(val context: Context, val position: Int) : ActionMode.Callback {

    var actionMode: ActionMode? = null

    override fun onCreateActionMode(p0: ActionMode?, p1: Menu?): Boolean {
        // Inflate a menu resource providing context menu items
        val inflater: MenuInflater = p0!!.menuInflater
        inflater.inflate(R.menu.context, p1)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        return false // Return false if nothing is done
    }

    // Called when the user selects a contextual menu item
    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.delete -> {

                val taskDataBase = TaskDatabase.getInstance(context)

                val taskDatabaseDao = taskDataBase.taskDao()!!

                val task: Task = taskDatabaseDao.getTaskList()[position]

                taskDatabaseDao.delete(task)

            }
            // как здесь получить элемент, который был нажат?
        }
        return true
    }

    // Called when the user exits the action mode
    override fun onDestroyActionMode(mode: ActionMode) {
        actionMode = null
    }
}


