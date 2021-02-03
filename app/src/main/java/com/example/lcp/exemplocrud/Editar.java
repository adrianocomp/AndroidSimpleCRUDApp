package com.example.lcp.exemplocrud;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Intent it = getIntent();
        int id = it.getIntExtra("id", 0);

        SQLiteDatabase db = openOrCreateDatabase("clientes.db", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM clientes WHERE _id = ?", new String[]{String.valueOf(id)});

        if(cursor.moveToFirst()){
            EditText txtNome = (EditText) findViewById(R.id.TxtNome);
            EditText txtEmail = (EditText) findViewById(R.id.TxtEmail);

            txtNome.setText(cursor.getString(1));
            txtEmail.setText(cursor.getString(2));
        }

    }

    public void AtualizarClick(View v){
        EditText txtNome = (EditText) findViewById(R.id.TxtNome);
        EditText txtEmail = (EditText) findViewById(R.id.TxtEmail);

        if (txtNome.getText().toString().length() <= 0){
            txtNome.setError("Preencha o campo nome");
            txtNome.requestFocus();
        } else if(txtEmail.getText().toString().length() <= 0){
            txtEmail.setError("Preencha o campo email");
            txtEmail.requestFocus();
        }else{
            try{
                SQLiteDatabase db = openOrCreateDatabase("clientes.db", Context.MODE_PRIVATE, null);
                ContentValues ctv = new ContentValues();
                ctv.put("nome", txtNome.getText().toString());
                ctv.put("email", txtEmail.getText().toString());

                Intent it = getIntent();
                int id = it.getIntExtra("id", 0);

                if(db.update("clientes", ctv, "_id=?", new String[] {String.valueOf(id)}) > 0){
                    Toast.makeText(getBaseContext(), "Sucesso ao atualizar o cliente.", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getBaseContext(), "Ocorreu um erro ao atualizar o cliente.", Toast.LENGTH_SHORT).show();
                }

            }catch(Exception e){
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void ApagarClick(View v){
        try {
            final SQLiteDatabase db = openOrCreateDatabase("clientes.db", Context.MODE_PRIVATE, null);

            Intent it = getIntent();
            final int id = it.getIntExtra("id", 0);

            AlertDialog.Builder msg = new AlertDialog.Builder(Editar.this);
            msg.setMessage("Deseja excluir este cliente?");
            msg.setNegativeButton("Não", null);
            msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (db.delete("clientes", "_id=?", new String[]{String.valueOf(id)}) > 0) {
                        Toast.makeText(getBaseContext(), "Sucesso ao apagar o cliente.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), "Erro ao excluir cliente.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            msg.show();
        }catch (Exception e){
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
}
