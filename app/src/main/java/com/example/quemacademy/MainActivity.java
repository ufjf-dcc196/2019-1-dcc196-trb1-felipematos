package com.example.quemacademy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quemacademy.models.Area;
import com.example.quemacademy.models.Disciplina;
import com.example.quemacademy.models.Planejamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Planejamento> planejamentos = new ArrayList<>();

    void setInitialData() {
        Planejamento p1 = new Planejamento("2018", "2", 100);
        Planejamento p2 = new Planejamento("2019", "1", 80);
        //Planejamento p3 = new Planejamento("2019", "2", 10);

        Disciplina d1 = new Disciplina("AA", 10, Area.EXATAS);
        Disciplina d2 = new Disciplina("BB", 13, Area.EXATAS);
        Disciplina d3 = new Disciplina("CC", 14, Area.SAUDE);
        Disciplina d4 = new Disciplina("DD", 8, Area.SAUDE);
        Disciplina d5 = new Disciplina("EE", 15, Area.HUMANIDADES);
        Disciplina d6 = new Disciplina("GG", 20, Area.LINGUAS);

        p1.setPorcentagens(new HashMap<Area, Integer>(){{
            put(Area.EXATAS, 50);
            put(Area.SAUDE, 10);
            put(Area.HUMANIDADES, 15);
            put(Area.LINGUAS, 25);
        }});
        List<Disciplina> pd1 = p1.getDisciplinas();
        pd1.add(d1);
        pd1.add(d3);
        pd1.add(d4);
        pd1.add(d6);

        p2.setPorcentagens(new HashMap<Area, Integer>(){{
            put(Area.EXATAS, 20);
            put(Area.SAUDE, 40);
            put(Area.HUMANIDADES, 30);
            put(Area.LINGUAS, 10);
        }});
        List<Disciplina> pd2 = p2.getDisciplinas();
        pd2.add(d1);
        pd2.add(d2);
        pd2.add(d5);
        pd2.add(d6);

        planejamentos.add(p1);
        planejamentos.add(p2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialData();

        RecyclerView rv = findViewById(R.id.rvPlanejamento);
        PAdapter pAdapter = new PAdapter();
        rv.setAdapter(pAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    public class PAdapter extends RecyclerView.Adapter<PAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            Context context = viewGroup.getContext();
            LayoutInflater infl = LayoutInflater.from(context);
            View linha = infl.inflate(R.layout.planejamento_item, viewGroup, false);
            return new ViewHolder(linha);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.setData(planejamentos.get(i));
        }

        @Override
        public int getItemCount() {
            return planejamentos.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView anoSemestre;
            TextView horasPlanejadas;
            TextView horasComputadas;
            TextView percent;
            TextView percentE;
            TextView percentS;
            TextView percentH;
            TextView percentL;


            ViewHolder(@NonNull View itemView) {
                super(itemView);
                anoSemestre = itemView.findViewById(R.id.textAnoSemestre);
                horasPlanejadas = itemView.findViewById(R.id.textHorasPlanejadas);
                horasComputadas = itemView.findViewById(R.id.textHorasComputadas);
                percent = itemView.findViewById(R.id.textHorasPorcentagem);
                percentE = itemView.findViewById(R.id.textPercentE);
                percentS = itemView.findViewById(R.id.textPercentS);
                percentH = itemView.findViewById(R.id.textPercentH);
                percentL = itemView.findViewById(R.id.textPercentL);
            }

            void setData(Planejamento planejamento){
                @SuppressLint("DefaultLocale")
                String textAnoSemestre = String.format("%s/%s", planejamento.getAno(), planejamento.getSemestre());
                anoSemestre.setText(textAnoSemestre);
                horasPlanejadas.setText(Integer.toString(planejamento.getHoras()));
                horasComputadas.setText(Integer.toString(planejamento.getHorasComputadas()));
                percent.setText(Float.toString((planejamento.getPercent())));
                percentE.setText(Float.toString((planejamento.getPercent(Area.EXATAS))));
                percentS.setText(Float.toString((planejamento.getPercent(Area.SAUDE))));
                percentH.setText(Float.toString((planejamento.getPercent(Area.HUMANIDADES))));
                percentL.setText(Float.toString((planejamento.getPercent(Area.LINGUAS))));

            }

            @Override
            public void onClick(View v) {
                /*
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onPalavraClick(v, position);
                }
                */
            }
        }

    }
}
