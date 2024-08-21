package com.guimolinas.quiztransito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ImageView imgPlaca;

    public Button btnAlternativa1;
    public Button btnAlternativa2;
    public Button btnAlternativa3;
    public Button btnAlternativa4;

    public Button btnProxima;

    public Random rand;

    public static List<ItemPlaca> lista;

    public int vez = 1;

    public static int certas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        certas = 0;

        lista = new ArrayList<>();

        rand = new Random();

        imgPlaca = (ImageView) findViewById(R.id.imgPlaca);

        btnAlternativa1 = (Button) findViewById(R.id.btnAlternativa1);
        btnAlternativa2 = (Button) findViewById(R.id.btnAlternativa2);
        btnAlternativa3 = (Button) findViewById(R.id.btnAlternativa3);
        btnAlternativa4 = (Button) findViewById(R.id.btnAlternativa4);

        btnProxima = (Button) findViewById(R.id.btnProxima);

        for(int i = 0; i < new BancoDeDados().respostas.length; i++) {
            lista.add(new ItemPlaca(new BancoDeDados().respostas[i], new BancoDeDados().placas[i]));
        }

        //Misturando os nomes
        Collections.shuffle(lista);

        novaPergunta(vez);

        btnProxima.setEnabled(false);

        View.OnClickListener alternativaClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button selectedButton = (Button) view;
                if(selectedButton.getText().toString().equalsIgnoreCase(lista.get(vez - 1).getNome())) {
                    //Toast.makeText(MainActivity.this, "Correto!", Toast.LENGTH_SHORT).show();
                    certas++; // Incrementa a variável 'certas' quando a resposta está correta
                } else {
                    //Toast.makeText(MainActivity.this, "Errado!", Toast.LENGTH_SHORT).show();
                }

                btnProxima.setEnabled(true); // Habilita o botão "Próxima" após uma resposta ser selecionada
            }
        };

        btnAlternativa1.setOnClickListener(alternativaClickListener);
        btnAlternativa2.setOnClickListener(alternativaClickListener);
        btnAlternativa3.setOnClickListener(alternativaClickListener);
        btnAlternativa4.setOnClickListener(alternativaClickListener);

        btnProxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vez < lista.size()) {
                    vez++;
                    novaPergunta(vez);
                } else {
                    Toast.makeText(MainActivity.this, "Você finalizou o quiz!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Resposta.class);
                    intent.putExtra("CERTAS", certas);
                    intent.putExtra("TOTAL", lista.size()); // Passa o total de perguntas
                    startActivity(intent);
                    finish(); // Finaliza a MainActivity para evitar voltar a ela
                }
            }
        });
    }

    private void novaPergunta(int numero) {
        // Desabilita o botão "Próxima" até que uma resposta seja selecionada
        btnProxima.setEnabled(false);

        imgPlaca.setImageResource(lista.get(numero - 1).getImgs());

        int respostaCerta = rand.nextInt(4) + 1;

        int primeiroBotao = numero - 1;
        int segundoBotao;
        int terceiroBotao;
        int quartoBotao;

        switch(respostaCerta) {
            case 1:
                btnAlternativa1.setText(lista.get(primeiroBotao).getNome());

                do {
                    segundoBotao = rand.nextInt(lista.size());
                } while(segundoBotao == primeiroBotao);

                do {
                    terceiroBotao = rand.nextInt(lista.size());
                } while(terceiroBotao == primeiroBotao || terceiroBotao == segundoBotao);

                do {
                    quartoBotao = rand.nextInt(lista.size());
                } while(quartoBotao == primeiroBotao || quartoBotao == segundoBotao || quartoBotao == terceiroBotao);

                btnAlternativa2.setText(lista.get(segundoBotao).getNome());
                btnAlternativa3.setText(lista.get(terceiroBotao).getNome());
                btnAlternativa4.setText(lista.get(quartoBotao).getNome());

                break;

            case 2:
                btnAlternativa2.setText(lista.get(primeiroBotao).getNome());

                do {
                    segundoBotao = rand.nextInt(lista.size());
                } while(segundoBotao == primeiroBotao);

                do {
                    terceiroBotao = rand.nextInt(lista.size());
                } while(terceiroBotao == primeiroBotao || terceiroBotao == segundoBotao);

                do {
                    quartoBotao = rand.nextInt(lista.size());
                } while(quartoBotao == primeiroBotao || quartoBotao == segundoBotao || quartoBotao == terceiroBotao);

                btnAlternativa1.setText(lista.get(segundoBotao).getNome());
                btnAlternativa3.setText(lista.get(terceiroBotao).getNome());
                btnAlternativa4.setText(lista.get(quartoBotao).getNome());

                break;

            case 3:
                btnAlternativa3.setText(lista.get(primeiroBotao).getNome());

                do {
                    segundoBotao = rand.nextInt(lista.size());
                } while(segundoBotao == primeiroBotao);

                do {
                    terceiroBotao = rand.nextInt(lista.size());
                } while(terceiroBotao == primeiroBotao || terceiroBotao == segundoBotao);

                do {
                    quartoBotao = rand.nextInt(lista.size());
                } while(quartoBotao == primeiroBotao || quartoBotao == segundoBotao || quartoBotao == terceiroBotao);

                btnAlternativa2.setText(lista.get(segundoBotao).getNome());
                btnAlternativa1.setText(lista.get(terceiroBotao).getNome());
                btnAlternativa4.setText(lista.get(quartoBotao).getNome());

                break;

            case 4:
                btnAlternativa4.setText(lista.get(primeiroBotao).getNome());

                do {
                    segundoBotao = rand.nextInt(lista.size());
                } while(segundoBotao == primeiroBotao);

                do {
                    terceiroBotao = rand.nextInt(lista.size());
                } while(terceiroBotao == primeiroBotao || terceiroBotao == segundoBotao);

                do {
                    quartoBotao = rand.nextInt(lista.size());
                } while(quartoBotao == primeiroBotao || quartoBotao == segundoBotao || quartoBotao == terceiroBotao);

                btnAlternativa2.setText(lista.get(segundoBotao).getNome());
                btnAlternativa3.setText(lista.get(terceiroBotao).getNome());
                btnAlternativa1.setText(lista.get(quartoBotao).getNome());

                break;
        }
    }
}
