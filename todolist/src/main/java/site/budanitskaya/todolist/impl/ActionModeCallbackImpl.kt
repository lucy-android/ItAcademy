package site.budanitskaya.todolist.impl

import android.view.ActionMode
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.firstscreen.FirstFragment

class ActionModeCallBackImpl(var fragment: FirstFragment, private val position: Int) :
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
                fragment.showTaskDeleteDialog(position)
            }
            R.id.edit -> {
                fragment.navigateToFragementTwo(position, false)
            }
        }
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode) {
        actionMode = null
    }
}