package com.example.safehome.util;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SwipeMovement implements View.OnTouchListener {

    private float downY;
    private View viewToMove;
//    private TextView textView;
    Context context;
    public SwipeMovement(View view, TextView v2, Context context) {
        this.viewToMove = view;
//        this.textView = v2;
        this.context = context;
        viewToMove.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float viewLoc = viewToMove.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float deltaY = event.getY() - downY;
                viewToMove.setTranslationY(deltaY);
//                textView.setText("y movement: "+event.getY()+" loc "+viewLoc);
                if(viewLoc < 200) {
//                    new NotificationHelper(context).sendNotification();
                    new AlarmHelper(context).stopAlarm(context);
                }
                return true;
            case MotionEvent.ACTION_UP:
                viewToMove.animate().translationY(0).start();
                return true;
        }
        return false;
    }
}
