package com.example.quemacademy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class NovaDisciplinaCursadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novadisciplinacursada);

        Spinner spinner = findViewById(R.id.spinnerNovaArea);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.area_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btnSalvar = findViewById(R.id.buttonNovaSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("nome", ((TextView)findViewById(R.id.textNovaDisciplina)).getText().toString());
                intent.putExtra("horas", Integer.parseInt(((TextView)findViewById(R.id.textNovaHora)).getText().toString()));
                intent.putExtra("area", ((Spinner)findViewById(R.id.spinnerNovaArea)).getSelectedItem().toString());

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
