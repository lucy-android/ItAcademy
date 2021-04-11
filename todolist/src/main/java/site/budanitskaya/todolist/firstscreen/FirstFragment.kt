package site.budanitskaya.todolist.firstscreen

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.adapter.ToDoListAdapter
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.util.TaskDataSource


class FirstFragment : MvpAppCompatFragment(), FirstScreenView {

    @InjectPresenter
    lateinit var presenter: FirstScreenPresenter

    private var it: Int? = null
    private lateinit var adapter: ToDoListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var tasks: List<Task>
    private var firstFragmentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firstFragmentView = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = firstFragmentView!!.findViewById<RecyclerView>(R.id.recycler_view)
        tasks = TaskDataSource.taskList

        adapter = ToDoListAdapter(
            tasks
        ) {
            this.it = it
            val actionModeCallback = ActionModeCallBackImpl(requireContext(), it)
            when (actionModeCallback.actionMode) {
                null -> {
                    // Start the CAB using the ActionMode.Callback defined above
                    actionModeCallback.actionMode = activity?.startActionMode(actionModeCallback)
                    firstFragmentView!!.isSelected = true
                }
            }
            return@ToDoListAdapter true
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val fab = firstFragmentView!!.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                    -1,
                    true
                )
            )
        }

        return firstFragmentView!!
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.context, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    inner class ActionModeCallBackImpl(val context: Context, val position: Int) :
        ActionMode.Callback {

        var actionMode: ActionMode? = null

        override fun onCreateActionMode(p0: ActionMode?, p1: Menu?): Boolean {
            val inflater: MenuInflater = p0!!.menuInflater
            inflater.inflate(R.menu.context, p1)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.delete -> {
                    val task: Task = tasks[it!!]
                    TaskDataSource.deleteTask(task)
                    recyclerView.removeViewAt(it!!)
                    adapter.notifyItemRemoved(it!!)
                    adapter.notifyItemRangeChanged(it!!, tasks.size)
                }
                R.id.edit -> {
                    firstFragmentView!!.findNavController().navigate(
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                            it!!,
                            false
                        )
                    )
                }
            }
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }
}