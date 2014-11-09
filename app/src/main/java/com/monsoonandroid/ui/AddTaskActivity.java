package com.monsoonandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.monsoonandroid.R;

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
                onBackPressed();
            }
        });

        textColors = getResources().getIntArray(R.array.default_text_colors);
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
