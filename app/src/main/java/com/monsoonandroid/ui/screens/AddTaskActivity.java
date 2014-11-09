package com.monsoonandroid.ui.screens;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.monsoonandroid.R;
import com.monsoonandroid.ui.views.ColorSelectorView;

/**
 * Created by piotr on 08/11/14.
 */
public class AddTaskActivity extends BaseActivity {

    private ColorSelectorView colorSelectorView;
    private EditText editText;

    public static final String ARG_TEXT = "arg_text";
    public static final String ARG_BCG_COLOR = "arg_color";
    public static final String ARG_TXT_COLOR = "arg_txt_color";

    private int[] textColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_task);
        Button submitButton = (Button) findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitClicked();
            }
        });
        this.colorSelectorView = (ColorSelectorView) findViewById(R.id.colorSelectorView);
        this.editText = (EditText) findViewById(R.id.editText);

        findViewById(R.id.viewDim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.hasFocus())
                {
                    editText.clearFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                } else {
                    onBackPressed();
                }
            }
        });

        String[] textColorsAsText = getResources().getStringArray(R.array.default_text_colors);
        this.textColors = new int[textColorsAsText.length];

        for (int i = 0; i < textColorsAsText.length; i++)
        {
            textColors[i] = Color.parseColor(textColorsAsText[i]);
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void onSubmitClicked()
    {
        if (editText.getText().length() == 0)
        {
            Toast.makeText(this, R.string.invalid_length, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(ARG_TEXT, editText.getText().toString());
        intent.putExtra(ARG_BCG_COLOR, colorSelectorView.selectedColor());
        intent.putExtra(ARG_TXT_COLOR, textColors[colorSelectorView.selectedIndex()]);

        setResult(RESULT_OK, intent);
        finish();
    }
}
