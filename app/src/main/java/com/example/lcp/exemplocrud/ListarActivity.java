package com.example.lcp.exemplocrud;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
    }

    public void onResume(){
        super.onResume();

        SQLiteDatabase db = openOrCreateDatabase("clientes.db",
                Context.MODE_PRIVATE, null);

        StringBuilder SqlClientes = new StringBuilder();
        SqlClientes.append("CREATE TABLE IF NOT EXISTS clientes(");
        SqlClientes.append("_id INTEGER PRIMARY KEY,");
        SqlClientes.append("nome VARCHAR(30),");
        SqlClientes.append("email VARCHAR(30));");
        db.execSQL(SqlClientes.toString());

        Cursor cursor = db.rawQuery("SELECT * FROM clientes", null);

        String[] de = {"_id", "nome"};
        int[] para = {R.id.txtID, R.id.txtNome};

        android.widget.SimpleCursorAdapter ad = new android.widget.SimpleCursorAdapter(
                getBaseContext(), R.layout.listar_model, cursor, de, para);

        ListView ltwDados = (ListView) findViewById(R.id.ltwDados);

        ltwDados.setAdapter(ad);

        ltwDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SQLiteCursor c = (SQLiteCursor) parent.getAdapter().getItem(position);

                Intent it = new Intent(getBaseContext(), Editar.class);
                it.putExtra("id", c.getInt(0));
                startActivity(it);
            }
        });
        db.close();
    }

}
