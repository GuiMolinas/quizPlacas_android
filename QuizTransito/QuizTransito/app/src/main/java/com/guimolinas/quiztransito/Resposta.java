package com.guimolinas.quiztransito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Resposta extends AppCompatActivity {

    public TextView txtPontuacao;
    public TextView txtNome;

    public Button btnNovamente;
    public Button btnSair;

    private int certas;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resposta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtPontuacao = (TextView) findViewById(R.id.txtPontuacao);

        btnNovamente = (Button) findViewById(R.id.btnJogarNovamente);
        btnSair = (Button) findViewById(R.id.btnSair);

        Intent intent = getIntent();
        certas = intent.getIntExtra("CERTAS", 0);
        total = intent.getIntExtra("TOTAL", 0);

        txtPontuacao.setText(certas + " / " + total);

        btnNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.certas = 0;
                Intent intent = new Intent(Resposta.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity(); // Fecha a activity
            }
        });
    }
}