package site.budanitskaya.todolist.firstscreen

import android.app.AlertDialog
import android.app.Dialog
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
import site.budanitskaya.todolist.impl.ActionModeCallBackImpl

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
            showAcionMode(it)
            return@ToDoListAdapter false
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            navigateToFragementTwo(-1, true)
        }

        return binding.root
    }

    override fun showAcionMode(position: Int) {
        val actionModeCallback = ActionModeCallBackImpl(this, position)
        when (actionModeCallback.actionMode) {
            null -> {
                actionModeCallback.actionMode = activity?.startActionMode(actionModeCallback)
                binding.coordLayout.isSelected = true
            }
        }
    }

    override fun showTaskDeleteDialog(position: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.note_deletion_message))
            .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                when (which) {
                    Dialog.BUTTON_POSITIVE -> {
                        presenter.deleteTask(position)
                    }
                }
            }
            .setNegativeButton(getString(R.string.no)){
                    dialog, which ->
                when (which) {
                    Dialog.BUTTON_NEGATIVE -> { }
                }
            }
            .create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.context, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onUpdateView(position: Int) {
        binding.recyclerView.removeViewAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, presenter.tasks.size)
    }

    fun navigateToFragementTwo(position: Int, isNew: Boolean) {
        findNavController().navigate(
            FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                position,
                isNew
            )
        )
    }
}