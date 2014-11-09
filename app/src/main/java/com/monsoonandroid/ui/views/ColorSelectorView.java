package com.monsoonandroid.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.monsoonandroid.R;
import com.monsoonandroid.utils.ScreenUtils;

/**
 * Created by piotr on 08/11/14.
 */
public class ColorSelectorView extends FrameLayout {

    public static final int DEFAULT_INDEX = 0;

    private int[] colors;

    private RadioGroup radioGroup;


    public ColorSelectorView(Context context) {
        super(context);
        init();
    }

    public ColorSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorSelectorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public int selectedIndex()
    {
        return radioGroup.getCheckedRadioButtonId();
    }

    public int selectedColor()
    {
        return colors[selectedIndex()];
    }

    protected void init()
    {
        if (isInEditMode())
            return;

        View.inflate(getContext(), R.layout.view_color_selector, this);
        createRadioButtons();
    }

    private void createRadioButtons() {
        this.radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        String[] colors = getResources().getStringArray(R.array.default_background_colors);

        final RadioButton[] rb = new RadioButton[colors.length];
        this.colors = new int[colors.length];
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(getContext(), null);
        params.setMargins(10, 0, 10, 0);
        params.width = (int) ScreenUtils.pxFromDp(getContext(),40);
        params.height = params.width;

        ColorDrawable colorChecked;
        ColorDrawable colorUnchecked;
        int color;

        for(int i=0; i<colors.length; i++){
            rb[i]  = new RadioButton(getContext());
            rb[i].setLayoutParams(params);
            rb[i].setId(i);
            StateListDrawable states = new StateListDrawable();

            color = Color.parseColor(colors[i]);
            this.colors[i] = color;
            colorChecked = new ColorDrawable(color);
            colorUnchecked = new ColorDrawable(ScreenUtils.adjustAlpha(color, 0.25f));

            states.addState(new int[] {android.R.attr.state_checked}, colorChecked);
            states.addState(new int[]{}, colorUnchecked);
            rb[i].setBackgroundDrawable(states);
            rb[i].setButtonDrawable(R.drawable.null_selector);
            radioGroup.addView(rb[i]);
        }
        rb[DEFAULT_INDEX].setChecked(true);
    }

}
