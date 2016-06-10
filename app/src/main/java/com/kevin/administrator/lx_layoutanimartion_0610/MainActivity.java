package com.kevin.administrator.lx_layoutanimartion_0610;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private GridLayout gridLayout;
    private Context mContext = this;
    int count = 0;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = (GridLayout) findViewById(R.id.gridView);
        gridLayout.setLayoutTransition(createTransition());
    }

    private LayoutTransition createTransition() {
        LayoutTransition layoutTransition = new LayoutTransition();
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "scaleX", 0, 1).setDuration(1000);
        layoutTransition.setAnimator(LayoutTransition.APPEARING, animator);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "rotation", 0, 720).setDuration(1000);
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, animator1);
        layoutTransition.setStagger(LayoutTransition.CHANGE_APPEARING, 20);
        layoutTransition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);



        PropertyValuesHolder pvLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvRight = PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder pvBottom = PropertyValuesHolder.ofInt("bottom", 0, 1);
//      PropertyValuesHolder pvScaleX = PropertyValuesHolder.ofFloat("rotation", 0,30,0,-30,0);
        PropertyValuesHolder pvScaleY = PropertyValuesHolder.ofFloat("scaleY", 1, 0, 1);
        ObjectAnimator change_appear = ObjectAnimator.ofPropertyValuesHolder(this, pvLeft, pvRight, pvTop, pvBottom, pvScaleY);
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, change_appear);
        change_appear.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
               View  view= (View) ((ObjectAnimator) animation).getTarget();

                view.setScaleY(1);
            }
        });


        return layoutTransition;

    }

    public void buttonclick(View view) {
        button = new Button(mContext);
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        count++;
        button.setText("第" + count + "个");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridLayout.removeView(v);
            }
        });
        gridLayout.addView(button, 0);

    }
}
