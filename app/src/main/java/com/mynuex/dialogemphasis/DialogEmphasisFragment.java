package com.mynuex.dialogemphasis;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogEmphasisFragment extends DialogFragment {

    // Listener for ok and cancel clicked
    interface DialogFragmentListener {
        void userClickedOk();
        void userClickedCancel();
    }

    DialogFragmentListener mDialogListener;

    private final static String MESSAGE_ARG = "Dialog message";

    //Text to be displayed in the dialog's list
    final CharSequence[] EMPHASIS_CHOICE = {"Capitalize", "Add Exclamation Points", "Add Smiley Face"};
    boolean[] checkedItems = {false, false, false};

    public static DialogEmphasisFragment newInstance(String message) {
        DialogEmphasisFragment fragment = new DialogEmphasisFragment();
        Bundle args = new Bundle();
        args.putString(MESSAGE_ARG, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //Set the Activity as the listener
        if (activity instanceof  DialogFragmentListener) {
            mDialogListener = (DialogFragmentListener) activity;
        } else {
            throw new RuntimeException(activity.toString() +
                    " must implement SimpleDialogFragmentListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String message = getArguments().getString(MESSAGE_ARG);
        if (message.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter a text", Toast.LENGTH_LONG).show();
        }

        // Dialogs created by a Builder.
        // And tell builder options to set
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.setTitle("What emphasis would you like? ")
                .setMultiChoiceItems(EMPHASIS_CHOICE, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        emphasisResults(message);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialogListener.userClickedCancel();
                    }
                })
                .create();

        return dialog;
    }
    // Method for emphasized input results
    public void emphasisResults(String emphasizedInput) {

        String results = emphasizedInput;

        if(checkedItems[0]){
            results = results.toUpperCase();
        }
        if(checkedItems[1]){
            results += "!!!!";
        }
        if(checkedItems[2]){
            results += " (^_^)";
        }
        // Then display results with new alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Your emphasized text is...")
                .setMessage(results)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDialogListener.userClickedOk();
                    }
                }).show();
    }
}
