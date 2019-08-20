package com.k3mshiro.demofocus;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

/**
 * Created by k3mshiro on 2019-08-19.
 */
public class MainActivity extends Activity {

    private CustomConstraintLayout mRootView;
    private Button mLeftButton, mRightButton, mBottomButton;
    private ConstraintLayout mLeftMenu;
    private RecyclerView mRcvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setEventListeners();
    }

    private void initViews() {
        mRootView = findViewById(R.id.rootView);
        mLeftButton = findViewById(R.id.leftButton);
        mRightButton = findViewById(R.id.rightButton);
        mLeftMenu = findViewById(R.id.leftMenu);
        mRcvContent = findViewById(R.id.rcvContent);
        mBottomButton = findViewById(R.id.bottomButton);

        //Focus vào nút trái lúc khởi tạo
        mLeftButton.requestFocus();
    }

    private void setEventListeners() {
        mRootView.setOnFocusSearchListener(new OnFocusSearchListener() {
            @Override
            public View onFocusSearch(View focused, int direction) {

                if (focused.getId() == R.id.leftButton && direction == View.FOCUS_RIGHT) {
                    //Nếu đang ở nút trái và focus sang phải => focus nút phải
                    return mRightButton;
                } else if (focused.getId() == R.id.leftButton && direction == View.FOCUS_DOWN) {
                    return mBottomButton;
                } else if (focused.getId() == R.id.rightButton && direction == View.FOCUS_LEFT) {
                    //Nếu đang ở nút phải và focus sang trái => focus nút trái
                    return mLeftButton;
                } else if (focused.getId() == R.id.rightButton && direction == View.FOCUS_RIGHT) {
                    //Nếu đang ở nút phải và focus sang phải => ẩn leftmenu + focus nút phải
                    mLeftMenu.setVisibility(View.GONE);
                    return mRcvContent;
                } else if (focused.getId() == R.id.rightButton && direction == View.FOCUS_DOWN) {
                    //Nếu đang ở nút phải và focus xuống dưới => focus nút dưới
                    return mBottomButton;
                } else if (focused.getId() == R.id.rcvContent && direction == View.FOCUS_LEFT) {
                    //Nếu đang ở RecyclerView và focus sang trái => hiện leftmenu + focus left menu
                    mLeftMenu.setVisibility(View.VISIBLE);
                    return mLeftMenu;
                } else if (focused.getId() == R.id.bottomButton && direction == View.FOCUS_RIGHT) {
                    //Nếu đang ở nút dưới và focus sang phải => ẩn leftmenu + focus RecyclerView
                    mLeftMenu.setVisibility(View.GONE);
                    return mRcvContent;
                } else if (focused.getId() == R.id.bottomButton && direction == View.FOCUS_UP) {
                    //Nếu đang ở nút dưới và focus lên trên => focus nút trái
                    return mLeftButton;
                }

                //nếu không làm các hành động trên thì focus vào nút hiện tại
                return focused;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mLeftMenu.getVisibility() == View.GONE){
            mLeftMenu.setVisibility(View.VISIBLE);
            mLeftMenu.requestFocus();
        }
        else
            super.onBackPressed();
    }
}
