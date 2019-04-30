package com.example.quemacademy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_NOVO = 1;
    private static final int PLAN_DETALHES = 2;

    public static List<Planejamento> planejamentos = new ArrayList<>();
    private final PAdapter pAdapter = new PAdapter();

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
        if (planejamentos.size() == 0 ){
            setInitialData();
        }
        RecyclerView rv = findViewById(R.id.rvPlanejamento);
        rv.setAdapter(pAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Button btnNovoPlan = findViewById(R.id.buttonNovoPlan);
        btnNovoPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NovoPlanejamentoActivity.class);
                startActivityForResult(intent, RESULT_NOVO);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (Activity.RESULT_OK == resultCode && data != null){
            switch (requestCode){
                case RESULT_NOVO:
                    String[] parts = data.getStringArrayExtra("planejamento");
                    Planejamento plan = new Planejamento(parts[0], parts[1], Integer.parseInt(parts[2]));
                    Map<Area, Integer> map = plan.getPorcentagens();
                    map.put(Area.LINGUAS, Integer.parseInt(parts[3]));
                    map.put(Area.EXATAS, Integer.parseInt(parts[4]));
                    map.put(Area.SAUDE, Integer.parseInt(parts[5]));
                    map.put(Area.HUMANIDADES, Integer.parseInt(parts[6]));
                    planejamentos.add(plan);
            }
        }
        if (Activity.RESULT_CANCELED == resultCode) {
            pAdapter.notifyDataSetChanged();
        }
    }

    public class PAdapter extends RecyclerView.Adapter<PAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            Context context = viewGroup.getContext();
            LayoutInflater infl = LayoutInflater.from(context);
            View view = infl.inflate(R.layout.planejamento_item, viewGroup, false);
            return new ViewHolder(view);
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
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Intent intent = new Intent(MainActivity.this, DisciplinasCursadasActivity.class);
                            intent.putExtra("position", position);
                            startActivityForResult(intent, PLAN_DETALHES);
                        }
                    }
                });
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
                anoSemestre.setText(planejamento.getAnoSemestre());

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
                 //????
            }
        }
    }
}
