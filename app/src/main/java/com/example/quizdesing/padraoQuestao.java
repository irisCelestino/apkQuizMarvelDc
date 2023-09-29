package com.example.quizdesing;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class padraoQuestao extends AppCompatActivity /*implements View.OnClickListener*/ {
    public TextView txtNomePlayer;
    public TextView txtPontuacaoPlayer, campoPerguntas, qtdQuestoes;

    public Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;

    int score = 0;
    int totalPerguntas = PerguntasRespostas.perguntas.length;
    int questaoIndex = 0;
    int percentualAcertos = 0;


    int questaoPorJogada = 10;
    String[] perguntasJogada = new String[questaoPorJogada];
    String[][] respostasJogada = new String[questaoPorJogada][4];
    String[] respostasCorretasJogada = new String[questaoPorJogada];

    Random rand = new Random();

    int rdm;
    int[] vetorNumeros = new int[questaoPorJogada];

    boolean inserirNumero = false;

    String btnValue = "";
    String respostaSelecionada = "";

  //  @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padrao_questao);

        txtNomePlayer = (TextView) findViewById(R.id.txtNomePlayer);
        txtPontuacaoPlayer = (TextView) findViewById(R.id.txtScorePlayer);
        campoPerguntas = (TextView) findViewById(R.id.txtCampoPerguntas);
        qtdQuestoes = (TextView) findViewById(R.id.txtQtdQuestao);
        btnAnswer1 = (Button) findViewById(R.id.btnAnswer1);
        btnAnswer2 = (Button) findViewById(R.id.btnAnswer2);
        btnAnswer3 = (Button) findViewById(R.id.btnAnswer3);
        btnAnswer4 = (Button) findViewById(R.id.btnAnswer4);

       /* String vetor = "errado";
        for (int i=0; i < questaoPorJogada-1; i++){
            vetor = vetor + vetorNumeros[i] + "-";
        }
        Toast.makeText(this,vetor,Toast.LENGTH_LONG).show();
*/

       for (int i=0; i < questaoPorJogada; i++){
           rdm = rand.nextInt(PerguntasRespostas.perguntas.length-1);
           inserirNumero = false;
           for(int j=0; j< questaoPorJogada; j++){
               if(vetorNumeros[j] == rdm){
                   inserirNumero = false;
                   continue;
               } else {
                   inserirNumero = true;
               }
           } // fim for j
           if((rdm != 0)&&(inserirNumero)){
               vetorNumeros[i]=rdm;
           }

       } // fim for i
     // comentar o teste de diferença
     /*   String vetor = "";
        for (int i=0; i < questaoPorJogada; i++){
            vetor = vetor + vetorNumeros[i] + "-";
        }
        Toast.makeText(this,vetor,Toast.LENGTH_LONG).show();*/
    // até aki





        for(int k = 0; k < questaoPorJogada; k++){

            perguntasJogada[k] = PerguntasRespostas.perguntas[vetorNumeros[k]].toString();

            respostasJogada[k][0] = PerguntasRespostas.respostas[vetorNumeros[k]][0].toString();
            respostasJogada[k][1] = PerguntasRespostas.respostas[vetorNumeros[k]][1].toString();
            respostasJogada[k][2] = PerguntasRespostas.respostas[vetorNumeros[k]][2].toString();
            respostasJogada[k][3] = PerguntasRespostas.respostas[vetorNumeros[k]][3].toString();
            respostasCorretasJogada[k] = PerguntasRespostas.respostas_corretas[vetorNumeros[k]].toString();


         //   rdm = rand.nextInt(PerguntasRespostas.perguntas.length-1);
            //perguntasJogada[k] = PerguntasRespostas.perguntas[VETORnUMEROSdIFERENTES[K]].toString();
     /*       perguntasJogada[k] = PerguntasRespostas.perguntas[rdm].toString();

            respostasJogada[k][0] = PerguntasRespostas.respostas[rdm][0].toString();
            respostasJogada[k][1] = PerguntasRespostas.respostas[rdm][1].toString();
            respostasJogada[k][2] = PerguntasRespostas.respostas[rdm][2].toString();
            respostasJogada[k][3] = PerguntasRespostas.respostas[rdm][3].toString();
            respostasCorretasJogada[k] = PerguntasRespostas.respostas_corretas[rdm].toString();
*/
        }

        nextQuestion();


        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnValue = btnAnswer1.getText().toString();
                verificarResposta(btnValue, btnAnswer1);

            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnValue = btnAnswer2.getText().toString();
                verificarResposta(btnValue, btnAnswer2);
            }
        });

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnValue = btnAnswer3.getText().toString();
                verificarResposta(btnValue, btnAnswer3);
            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnValue = btnAnswer4.getText().toString();
                verificarResposta(btnValue, btnAnswer4);
            }
        });

       qtdQuestoes.setText("Perguntas: "+ questaoIndex+"/"+questaoPorJogada);


        //String nomePLayer = "Player: " + getIntent().getStringExtra("Chave");
        String nome = "Player: " + getIntent().getStringExtra("Chave");
        //Integer score = 0;

        txtNomePlayer.setText(nome);

    }

 /*   @Override
    public void onClick(View v) {

    }
*/


    public void fimQuiz(){
      //  float percentual_acertos = (percentualAcertos * 100) / totalPerguntas;
        float percentual_acertos = (percentualAcertos * 100) / questaoPorJogada;
        percentual_acertos = Math.round(percentual_acertos);
        Intent intentTelaFim = new Intent(padraoQuestao.this, FimTela.class);
        intentTelaFim.putExtra("ChavePercentual", String.valueOf(percentual_acertos));
        startActivity(intentTelaFim);
        finish();
    }

    public void nextQuestion(){
     //   if(questaoIndex != totalPerguntas) {
        if(questaoIndex != questaoPorJogada) {
            btnAnswer1.setBackgroundColor(Color.WHITE);
            btnAnswer2.setBackgroundColor(Color.WHITE);
            btnAnswer3.setBackgroundColor(Color.WHITE);
            btnAnswer4.setBackgroundColor(Color.WHITE);

            campoPerguntas.setText(perguntasJogada[questaoIndex]);
            btnAnswer1.setText(respostasJogada[questaoIndex][0]);
            btnAnswer2.setText(respostasJogada[questaoIndex][1]);
            btnAnswer3.setText(respostasJogada[questaoIndex][2]);
            btnAnswer4.setText(respostasJogada[questaoIndex][3]);
 /*           campoPerguntas.setText(PerguntasRespostas.perguntas[questaoIndex]);
            btnAnswer1.setText(PerguntasRespostas.respostas[questaoIndex][0]);
            btnAnswer2.setText(PerguntasRespostas.respostas[questaoIndex][1]);
            btnAnswer3.setText(PerguntasRespostas.respostas[questaoIndex][2]);
            btnAnswer4.setText(PerguntasRespostas.respostas[questaoIndex][3]);
*/
        }else{
            fimQuiz();
        }
        }



    public void verificarResposta(String clickedBtn, Button btnAnswer) {
//        if(clickedBtn.equals(PerguntasRespostas.respostas_corretas[questaoIndex])){
        if(clickedBtn.equals(respostasCorretasJogada[questaoIndex])){
            btnAnswer.setBackgroundColor(Color.GREEN);
            percentualAcertos+=1;
            score += 100;
            questaoIndex++;
            qtdQuestoes.setText("Perguntas: "+ questaoIndex+"/"+questaoPorJogada);
            txtPontuacaoPlayer.setText("Score: " + String.valueOf(score)+" pts");

            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            nextQuestion();
                        }
                    },
                    1000);
        }else{
            btnAnswer.setBackgroundColor(Color.RED);
            questaoIndex++;
            qtdQuestoes.setText("Perguntas: "+ questaoIndex+"/"+questaoPorJogada);
            txtPontuacaoPlayer.setText("Score: " + String.valueOf(score)+" pts");
            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            nextQuestion();
                        }
                    },
                    1000);
        }
    }

    }

