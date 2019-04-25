package com.example.quemacademy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class NovoPlanejamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novoplan);

        (findViewById(R.id.buttonSaveNovoPlan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] result = new String[] {((TextView)findViewById(R.id.textNovoAno)).getText().toString(),
                        ((TextView)findViewById(R.id.textNovoSemestre)).getText().toString(),
                        ((TextView)findViewById(R.id.textNovoHoras)).getText().toString(),
                        ((TextView)findViewById(R.id.textLinguas)).getText().toString(),
                        ((TextView)findViewById(R.id.textExatas)).getText().toString(),
                        ((TextView)findViewById(R.id.textSaude)).getText().toString(),
                        ((TextView)findViewById(R.id.textHuman)).getText().toString()};
                Intent response = new Intent();
                response.putExtra("planejamento", result);
                setResult(Activity.RESULT_OK, response);
                finish();
            }
        });
    }


}
