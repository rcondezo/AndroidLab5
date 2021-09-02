package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class GeneroMusicalBuscarActivity extends AppCompatActivity {

    ArrayList<GeneroMusical> resultados = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero_musical_buscar);
    }

    public void buscar(View view) {
        EditText criterio = (EditText) findViewById(R.id.criterio);
        GeneroMusicalDAO dao = new GeneroMusicalDAO(getBaseContext());
        try {
            resultados = dao.buscar(criterio.getText().toString());

            String[] encontrados = new String[resultados.size()];
            int i = 0;
            for (GeneroMusical gm : resultados){
                encontrados[i++] = gm.getTitulo() + " | " + gm.getDescripcion();
            }

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this.getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    encontrados);

            ListView listaResultados = (ListView)findViewById(R.id.listaResultados);
            listaResultados.setAdapter(adaptador);
            listaResultados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GeneroMusical objeto = resultados.get(position);
                    Log.i("===>",""+objeto.getId());
                    Intent i = new Intent(getBaseContext(), GeneroMusicalDetalleActivity.class);
                    i.putExtra("id", objeto.getId());
                    startActivity(i);
                }
            });

        } catch (DAOException e) {
            Log.i("GeneroMusicalBuscarAc", "====> " + e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_nuevo:
                startActivity(new Intent(getBaseContext(), GeneroMusicalNuevoActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


