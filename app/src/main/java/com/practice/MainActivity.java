package com.practice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    Button popupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popupBtn = (Button) findViewById(R.id.popupBtn);
        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });
    }

    private void showPopupWindow(View view) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.dialog_holo_light_frame));
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        View container = popupWindow.getContentView().getRootView();
        container.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) v.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.618f;
                wm.updateViewLayout(v, p);
            }
        });
    }

}
