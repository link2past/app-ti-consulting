package com.oalvarez.appticonsulting1.views;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by oalvarez on 10/01/2017.
 */

@CoordinatorLayout.DefaultBehavior(MoveUpwardBehaviour.class)
public class MovableButton extends Button {
    public MovableButton(Context context) {
        super(context);
    }

    public MovableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
