package site.budanitskaya.todolist.impl

import android.animation.Animator
import androidx.constraintlayout.motion.widget.MotionLayout
import site.budanitskaya.todolist.R

class MotionLayoutTransitionListener() : MotionLayout.TransitionListener {
    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
    }

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
    }

    override fun onTransitionCompleted(p0: MotionLayout?, currentConstraintId: Int) {
        if (currentConstraintId == R.id.swipe_end) {
            p0?.animate()
                ?.alpha(1f)
                ?.setDuration(10000)
                ?.setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(p0: Animator?) {
                    }

                    override fun onAnimationEnd(p0: Animator?) {

                    }

                    override fun onAnimationCancel(p0: Animator?) {
                    }

                    override fun onAnimationRepeat(p0: Animator?) {
                    }
                })
        }
    }

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
    }
}