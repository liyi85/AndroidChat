package com.example.andrearodriguez.androidchat.addcontact.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.andrearodriguez.androidchat.R;
import com.example.andrearodriguez.androidchat.addcontact.AddContactlistPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactBlankFragment extends DialogFragment implements AddContactView, DialogInterface.OnShowListener {


    @BindView(R.id.editTctEmail)
    EditText editTctEmail;
    @BindView(R.id.progressBarAddContact)
    ProgressBar progressBarAddContact;

    AddContactlistPresenter presenter;

    public AddContactBlankFragment() {
            presenter = new AddContactlistPresenterImp(this);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.addcontact_messagge_title)
                .setPositiveButton(R.string.addcontact_messagge_add, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })

                    .setNegativeButton(R.string.addcontact_messagge_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_contact_blank, null);
        ButterKnife.bind(this, view);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);
        return dialog;
    }


    @Override
    public void showInput() {
        editTctEmail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        editTctEmail.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBarAddContact.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePRogress() {
        progressBarAddContact.setVisibility(View.GONE);
    }

    @Override
    public void contactAdded() {
        Toast.makeText(getActivity(), R.string.addcontact_messagge_contactadded, Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void contactNotAdded() {
        editTctEmail.setText("");
        editTctEmail.setError(getString(R.string.addcontact_erro_messagge));
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        final AlertDialog dialog = (AlertDialog)getDialog();
        if(dialog != null){
            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        presenter.addContact(editTctEmail.getText().toString());
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        presenter.onShow();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
