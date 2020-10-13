package com.helloworld.pokedex2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Network.PokemonLoader;
import utils.DialogManager;

public class baseActivity extends AppCompatActivity {
    public PokemonLoader loader;
    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loader = new PokemonLoader();
        progress= new ProgressDialog(this);
    }
    public void showProgress(){
        progress.setCancelable(false);
        progress.setMessage("Cargando...");
        progress.show();
    }
    public void hideProgress(){
        if(progress.isShowing())
            progress.dismiss();
    }
    public void showDialogError(){
        DialogManager manager = new DialogManager(baseActivity.this, "ERROR", "Error del Servidor");

        Dialog dialog = manager.buildDialog();

        dialog.show();

        manager.getBtDialog().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
