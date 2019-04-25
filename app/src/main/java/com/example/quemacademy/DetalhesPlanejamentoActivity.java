package com.example.quemacademy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.quemacademy.models.Area;
import com.example.quemacademy.models.Planejamento;

import java.util.Map;

public class DetalhesPlanejamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novoplan);

        Bundle bundle = this.getIntent().getExtras();
        int index = bundle.getInt("position");
        final Planejamento planejamento = MainActivity.planejamentos.get(index);

        ((TextView)findViewById(R.id.textNovoSemestre)).setText(planejamento.semestre);
        ((TextView)findViewById(R.id.textNovoAno)).setText(planejamento.ano);
        ((TextView)findViewById(R.id.textNovoHoras)).setText(String.valueOf(planejamento.horas));
        ((TextView)findViewById(R.id.textLinguas)).setText(planejamento.porcentagens.get(Area.LINGUAS).toString());
        ((TextView)findViewById(R.id.textExatas)).setText(planejamento.porcentagens.get(Area.EXATAS).toString());
        ((TextView)findViewById(R.id.textSaude)).setText(planejamento.porcentagens.get(Area.SAUDE).toString());
        ((TextView)findViewById(R.id.textHuman)).setText(planejamento.porcentagens.get(Area.HUMANIDADES).toString());

        (findViewById(R.id.buttonSaveNovoPlan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planejamento.ano = ((TextView)findViewById(R.id.textNovoAno)).getText().toString();
                planejamento.semestre = ((TextView)findViewById(R.id.textNovoSemestre)).getText().toString();
                planejamento.horas = Integer.parseInt(((TextView)findViewById(R.id.textNovoHoras)).getText().toString());
                Map<Area, Integer> map = planejamento.getPorcentagens();

                map.put(Area.LINGUAS, Integer.valueOf(((TextView)findViewById(R.id.textLinguas)).getText().toString()));
                map.put(Area.EXATAS, Integer.valueOf(((TextView)findViewById(R.id.textExatas)).getText().toString()));
                map.put(Area.SAUDE, Integer.valueOf(((TextView)findViewById(R.id.textSaude)).getText().toString()));
                map.put(Area.HUMANIDADES, Integer.valueOf(((TextView)findViewById(R.id.textHuman)).getText().toString()));
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
