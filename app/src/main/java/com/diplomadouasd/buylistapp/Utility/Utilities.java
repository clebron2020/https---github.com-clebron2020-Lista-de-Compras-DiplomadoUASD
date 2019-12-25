package com.diplomadouasd.buylistapp.Utility;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import com.diplomadouasd.buylistapp.R;

public  class Utilities {
    Context context;
    AlertDialog.Builder dialogBuilder;
    public AlertDialog CurrentDialog;
    public enum DialogType { Warning, Error,Information  }

    public Utilities(Context context) {
        this.context = context;
    }

    public void ModalPopup(String Title,
                           String Message,
                           View Content,
                           DialogInterface.OnClickListener positiveButton,
                           DialogInterface.OnClickListener negativeButton
    )
    {
        dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(Title);
        dialogBuilder.setMessage(Message);
        dialogBuilder.setView(Content);

        if (positiveButton!=null)
            dialogBuilder.setPositiveButton("Save",positiveButton);
        if (negativeButton!=null)
            dialogBuilder.setNegativeButton("Cancel",negativeButton);

        CurrentDialog = dialogBuilder.create();
        CurrentDialog.setCancelable(false);
        CurrentDialog.show();
    }

    public void DialogConfirm(String Title,
                              String Message,
                              DialogType dialogType,
                              DialogInterface.OnClickListener positiveButton,
                              DialogInterface.OnClickListener negativeButton
    )
    {
        dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(Title);
        switch (dialogType) {
            case Warning:
                dialogBuilder.setIcon(R.drawable.ic_warning_black_24dp);
                break;
            case Error:
                dialogBuilder.setIcon(R.drawable.ic_error_black_24dp);
                break;
            case Information:
                dialogBuilder.setIcon(R.drawable.ic_info_black_24dp);
                break;
        }

        dialogBuilder.setMessage(Message);
        dialogBuilder.setPositiveButton("Yes",positiveButton);
        dialogBuilder.setNegativeButton("No",negativeButton);
        CurrentDialog = dialogBuilder.create();
        CurrentDialog.setCancelable(false);
        CurrentDialog.show();
    }

    public void MessageBox(String Title,
                           String Message
    )
    {
        dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(Title);
        dialogBuilder.setIcon(R.drawable.ic_info_black_24dp);
        dialogBuilder.setMessage(Message);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CurrentDialog.dismiss();
            }
        });
        CurrentDialog = dialogBuilder.create();
        CurrentDialog.setCancelable(false);
        CurrentDialog.show();
    }
}
