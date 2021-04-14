package site.budanitskaya.todolist.firstscreen

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.adapter.ToDoListAdapter
import site.budanitskaya.todolist.databinding.FragmentFirstBinding

class FirstFragment : MvpAppCompatFragment(), FirstScreenView {

    @InjectPresenter
    lateinit var presenter: FirstScreenPresenter
    private lateinit var binding: FragmentFirstBinding
    private lateinit var adapter: ToDoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater)

        adapter = ToDoListAdapter(
            presenter.tasks
        ) {
            startThisAcionMode(it)
            return@ToDoListAdapter true
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            navigateToFragementTwo(-1, true)
        }

        return binding.root
    }

    private fun startThisAcionMode(position: Int) {
        val actionModeCallback = ActionModeCallBackImpl(requireContext(), position)
        when (actionModeCallback.actionMode) {
            null -> {
                actionModeCallback.actionMode = activity?.startActionMode(actionModeCallback)
                binding.coordLayout.isSelected = true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.context, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    inner class ActionModeCallBackImpl(val context: Context, private val position: Int) :
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
                    presenter.deleteTask(position)
                }
                R.id.edit -> {
                    navigateToFragementTwo(position, false)
                }
            }
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }

    override fun onItemRemoved(position: Int) {
        binding.recyclerView.removeViewAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, presenter.tasks.size)
    }

    private fun navigateToFragementTwo(position: Int, isNew: Boolean) {
        findNavController().navigate(
            FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                position,
                isNew
            )
        )
    }
}