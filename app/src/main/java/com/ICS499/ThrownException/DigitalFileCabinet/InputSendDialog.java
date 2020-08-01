package com.ICS499.ThrownException.DigitalFileCabinet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.view.ContextThemeWrapper;

class InputSenderDialog extends AlertDialog.Builder {

     public interface InputSenderDialogListener{
        public abstract void onApply(String number);
        public abstract void onCancel(String number);
    }

    private EditText documentNameEdit;

    public InputSenderDialog(Activity activity, final InputSenderDialogListener listener) {
        super( new ContextThemeWrapper(activity, R.style.AppTheme) );

        View dialogLayout = LayoutInflater.from(activity).inflate(R.layout.dialog_naming_doc, null);
        setView(dialogLayout);

        documentNameEdit = dialogLayout.findViewById(R.id.documentName);

        setPositiveButton(R.string.apply, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if( listener != null ) {
                    String name = documentNameEdit.getText().toString();
                    if (name.equals("")) {
                        documentNameEdit.setError("Must provide a name!");
                    } else {
                        dialog.dismiss();
                        listener.onApply(String.valueOf(documentNameEdit.getText()));
                    }
                }
            }
        });

        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if( listener != null )
                    listener.onCancel(String.valueOf(documentNameEdit.getText()));
            }
        });
    }

    public InputSenderDialog setName(String docName){
        documentNameEdit.setText( docName );
        return this;
    }

    @Override
    public AlertDialog show() {
        AlertDialog dialog = super.show();
        Window window = dialog.getWindow();
        if( window != null )
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        return dialog;
    }
}

