package com.example.lcp.exemplocrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Inserir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        SQLiteDatabase db = openOrCreateDatabase("clientes.db",
                Context.MODE_PRIVATE, null);

        StringBuilder SqlClientes = new StringBuilder();
        SqlClientes.append("CREATE TABLE IF NOT EXISTS clientes(");
        SqlClientes.append("_id INTEGER PRIMARY KEY,");
        SqlClientes.append("nome VARCHAR(30),");
        SqlClientes.append("email VARCHAR(30));");
        db.execSQL(SqlClientes.toString());
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
            }

    public void CadastrarClick(View v){
        EditText txtNome = (EditText) findViewById(R.id.txtNome);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);

        if(txtNome.getText().toString().length() <= 0){
            txtNome.setError("Preencha o campo nome.");
            txtNome.requestFocus();
        }else if(txtEmail.getText().toString().length() <= 0){
            txtEmail.setError("Preencha o campo e-mail.");
            txtEmail.requestFocus();
        } else {
            try{
                SQLiteDatabase db = openOrCreateDatabase("clientes.db",
                        Context.MODE_PRIVATE, null);

                ContentValues ctv = new ContentValues();
                ctv.put("nome", txtNome.getText().toString());
                ctv.put("email", txtEmail.getText().toString());

                if(db.insert("clientes", "_id", ctv) > 0){
                    Toast.makeText(getBaseContext(), "Cliente Cadastrado com sucesso.",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getBaseContext(), "Erro ao cadastrar cliente.",
                            Toast.LENGTH_SHORT).show();
                }
            }catch (Exception ex){
                Toast.makeText(getBaseContext(), ex.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }

    }
}
