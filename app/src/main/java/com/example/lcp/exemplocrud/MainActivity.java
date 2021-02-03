package com.example.lcp.exemplocrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void CadastrarClienteClick(View v){
        Intent it = new Intent(getBaseContext(), Inserir.class);
        startActivity(it);
    }

    public void ListarClienteClick(View v){
        Intent it = new Intent(getBaseContext(), ListarActivity.class);
        startActivity(it);
    }
}
