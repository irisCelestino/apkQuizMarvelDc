package com.example.quizdesing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FimTela extends AppCompatActivity {

   public Button btnReiniciar;
   public TextView scorePercentual;
   public TextView fraseMotivacional;

   public TextView fraseParabens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_encerramento);
        btnReiniciar = (Button) findViewById(R.id.btReiniciar);
        scorePercentual = (TextView) findViewById(R.id.txtTotalScore);
        fraseMotivacional = (TextView) findViewById(R.id.txtFrase);
        fraseParabens = (TextView) findViewById(R.id.txtParabens);

        String nome =  getIntent().getStringExtra("ChavePercentual") + "%";
        scorePercentual.setText(nome);


        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTelaRei = new Intent(FimTela.this, telaPrincipal.class);
                startActivity(intentTelaRei);
                finish();
            }
        });






    }
}