package com.example.taskapp.ui.gallery;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;


public class MyDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("information").
                setMessage("Привет друг").
                setPositiveButton("нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                    }
                });

        builder.setNegativeButton("да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int bekzat) {
                Toast.makeText(getActivity(), "Возможно вы правы", Toast.LENGTH_LONG)
                        .show();




            }
        });


        return builder.create();
    }


}
