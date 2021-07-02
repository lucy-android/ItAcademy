package site.budanitskaya.todolist.impl;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.constraintlayout.motion.widget.MotionLayout;

import site.budanitskaya.todolist.R;
public class OnSwipeTouchListener implements View.OnTouchListener {
    GestureDetector gestureDetector;
    public OnSwipeTouchListener(Context c) {
        gestureDetector = new GestureDetector(c, new GestureListener());
    }
    public boolean onTouch(final View view, final MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if (view instanceof MotionLayout) {

                if (((MotionLayout) view).getCurrentState() == R.id.start) {
                    ((MotionLayout) view).setTransition(R.id.start, R.id.click_end);
                } else {
                    ((MotionLayout) view).setTransition(R.id.click_end, R.id.start);
                }
                ((MotionLayout) view).transitionToEnd();
            }
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            if (view instanceof MotionLayout) {

                if (((MotionLayout) view).getCurrentState() == R.id.swipe_end) {
                    ((MotionLayout) view).setTransition(R.id.swipe_end, R.id.start);
                } else {
                    ((MotionLayout) view).setTransition(R.id.start, R.id.swipe_end);
                }
                ((MotionLayout) view).transitionToEnd();
            }
        }


        return gestureDetector.onTouchEvent(motionEvent);
    }



    private final class GestureListener extends
            GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 1;
        private static final int SWIPE_VELOCITY_THRESHOLD = 1;
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onClick();
            return super.onSingleTapUp(e);
        }
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            onDoubleClick();
            return super.onDoubleTap(e);
        }
        @Override
        public void onLongPress(MotionEvent e) {
            onLongClick();
            super.onLongPress(e);
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                }
                else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeDown();
                        } else {
                            onSwipeUp();
                        }
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }
    }
    public void onSwipeRight() {
    }
    public void onSwipeLeft() {
    }
    private void onSwipeUp() {
    }
    private void onSwipeDown() {
    }
    private void onClick() {
    }
    private void onDoubleClick() {
    }
    public void onLongClick() {
    }
}