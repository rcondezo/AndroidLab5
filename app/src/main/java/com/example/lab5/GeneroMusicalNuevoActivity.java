package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class GeneroMusicalNuevoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero_musical_nuevo);
    }

    public void grabar(View view) {

        EditText titulo = (EditText) findViewById(R.id.titulo);
        EditText descripcion = (EditText) findViewById(R.id.descripcion);

        GeneroMusicalDAO dao = new GeneroMusicalDAO(getBaseContext());
        try {
            dao.insertar(titulo.getText().toString(), descripcion.getText().toString());
            startActivity(new Intent(getBaseContext(), GeneroMusicalBuscarActivity.class));
        } catch (DAOException e) {
            Log.i("GeneroMusicalNuevoActi", "====> " + e.getMessage());
        }
    }

}
