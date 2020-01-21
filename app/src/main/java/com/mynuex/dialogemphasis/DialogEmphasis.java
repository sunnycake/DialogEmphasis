package com.mynuex.dialogemphasis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialogEmphasis extends AppCompatActivity implements DialogEmphasisFragment.DialogFragmentListener {

    private TextView mDialogResult;
    private EditText mEditTextInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_emphasis);

        mDialogResult = findViewById(R.id.dialog_result_text);
        mEditTextInput = findViewById(R.id.editText_input);

        Button showDialog = findViewById(R.id.show_dialog_box);
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEmphasisFragment emphasis = new DialogEmphasisFragment().newInstance(mEditTextInput.getText().toString());
                emphasis.show(getSupportFragmentManager(), "Simple Dialog");

            }
        });
    }
    // Methods from DialogEmphasisFragment for ok and cancel listener
    @Override
    public void userClickedOk() {
        mDialogResult.setText("Type a word to continue. Else, have good day!");
    }

    @Override
    public void userClickedCancel() {
        mDialogResult.setText("You clicked cancel. Type a word to add emphasis.");
    }
}
