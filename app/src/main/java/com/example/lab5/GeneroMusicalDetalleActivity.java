package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class GeneroMusicalDetalleActivity extends AppCompatActivity {

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero_musical_detalle);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null){
            id  = parametros.getInt("id");
            Log.i("===>", id+"");
        }

        GeneroMusicalDAO dao = new GeneroMusicalDAO(getBaseContext());
        try {
            GeneroMusical objeto = dao.obtener(id);
            Log.i("===>", objeto.toString());
            EditText etTitulo = findViewById(R.id.titulo);
            etTitulo.setText(objeto.getTitulo());
            EditText etDescripcion = findViewById(R.id.descripcion);
            etDescripcion.setText(objeto.getDescripcion());

        } catch (DAOException e) {
            Log.i("GeneroMusicalDetalle", "====> " + e.getMessage());
        }

    }

    public void actualizar(View view) {
        Log.i("===>", "Actualizar()");
        GeneroMusicalDAO dao = new GeneroMusicalDAO(getBaseContext());
        try {
            EditText etTitulo = findViewById(R.id.titulo);
            EditText etDescripcion = findViewById(R.id.descripcion);
            dao.actualizar(id, etTitulo.getText().toString(), etDescripcion.getText().toString());
            startActivity(new Intent(getBaseContext(), GeneroMusicalBuscarActivity.class));
        } catch (DAOException e) {
            Log.i("GeneroMusicalDetalle", "====> " + e.getMessage());
        }
    }

    public void eliminar(View view) {
        Log.i("===>", "Eliminar()");
        GeneroMusicalDAO dao = new GeneroMusicalDAO(getBaseContext());
        try {
            dao.eliminar(id);
            startActivity(new Intent(getBaseContext(), GeneroMusicalBuscarActivity.class));
        } catch (DAOException e) {
            Log.i("GeneroMusicalDetalle", "====> " + e.getMessage());
        }
    }
}