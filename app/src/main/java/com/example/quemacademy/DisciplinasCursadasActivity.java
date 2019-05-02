package com.example.quemacademy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quemacademy.models.Area;
import com.example.quemacademy.models.Disciplina;
import com.example.quemacademy.models.Planejamento;

public class DisciplinasCursadasActivity extends AppCompatActivity {

    static final int EDIT_PLANEJAMENTO = 1;
    static final int NOVA_DISCIPLINA = 2;

    Planejamento planejamento;
    DAdapter dAdapter = new DAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplinascursadas);

        Bundle bundle = this.getIntent().getExtras();
        final int index = bundle.getInt("position");
        planejamento = MainActivity.planejamentos.get(index);
        setTextData(planejamento);

        RecyclerView rv = findViewById(R.id.rvDisciplinasCursadas);
        rv.setAdapter(dAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Button btnNovaDisc = findViewById(R.id.buttonDiscCursadaNovaDisc);
        btnNovaDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisciplinasCursadasActivity.this, NovaDisciplinaCursadaActivity.class);
                startActivityForResult(intent, NOVA_DISCIPLINA);
            }
        });

        Button btnEditPlan = findViewById(R.id.buttonDiscCursadaEdit);
        btnEditPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisciplinasCursadasActivity.this, DetalhesPlanejamentoActivity.class);
                intent.putExtra("position", index);
                startActivityForResult(intent, EDIT_PLANEJAMENTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (Activity.RESULT_OK == resultCode){
            switch (requestCode){
                case EDIT_PLANEJAMENTO:
                    setTextData(planejamento);
                    break;
                case NOVA_DISCIPLINA:
                    if (data != null) {
                        int hora = data.getIntExtra("horas", 0);
                        String nome = data.getStringExtra("nome");
                        String area = data.getStringExtra("area");
                        planejamento.disciplinas.add(new Disciplina(nome, hora, Area.valueOf(area)));
                        dAdapter.notifyDataSetChanged();

                    }
                    break;
            }
        }
    }

    public void setTextData(Planejamento planejamento) {
        ((TextView)findViewById(R.id.textDiscAnoSemestre)).setText(planejamento.getAnoSemestre());
        ((TextView)findViewById(R.id.textDiscCargaHoraria)).setText(Integer.toString(planejamento.horas));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public class DAdapter extends RecyclerView.Adapter<DAdapter.ViewHolder> {

        @NonNull
        @Override
        public DAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            Context context = viewGroup.getContext();
            LayoutInflater infl = LayoutInflater.from(context);
            View linha = infl.inflate(R.layout.disccursada_item, viewGroup, false);
            return new DAdapter.ViewHolder(linha);
        }

        @Override
        public void onBindViewHolder(@NonNull DAdapter.ViewHolder viewHolder, int i) {
            viewHolder.setData(planejamento.disciplinas.get(i));
        }

        @Override
        public int getItemCount() {
            return planejamento.disciplinas.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            TextView area;
            TextView nome;
            TextView horas;


            ViewHolder(@NonNull View itemView) {
                super(itemView);
                area = itemView.findViewById(R.id.textDiscCursadaArea);
                nome = itemView.findViewById(R.id.textDiscCursadaNome);
                horas = itemView.findViewById(R.id.textDiscCursadaHoras);
            }

            void setData(Disciplina disciplina){
                area.setText(disciplina.area.name());
                nome.setText(disciplina.titulo);
                horas.setText(Integer.toString(disciplina.horas));
            }
        }
    }
}
