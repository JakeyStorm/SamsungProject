package com.example.samsungproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {
    int sum2;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        sum2 = getIntent().getIntExtra("SUM",0);
        recyclerView = findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        new MyThread().start();
    }
    protected void loadProduct() {
        ApiService api = RetroClient.getApiService();

        Call<List<InfoGame>> call = api.getGamePC();

        call.enqueue(new Callback<List<InfoGame>>() {
            @Override
            public void onResponse(Call<List<InfoGame>> call, Response<List<InfoGame>> response) {
                List<InfoGame> sort =  response.body();
                int i =0;
                Iterator<InfoGame> it = sort.iterator();
                while (it.hasNext()) {
                    if (it.next().getPrice()<sum2) {
                        it.remove();
                    }
                    else if (it.next().getPrice()>sum2) {
                        break;
                    }

                }

                RVAdapter adapter = new RVAdapter(sort, getApplication(),sum2);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<InfoGame>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
    class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
        private Context context;

        List<InfoGame> persons;

        RVAdapter(List<InfoGame> gameView, Context context, int sum2) {
            this.persons = gameView;
            this.context = context;
        }
        @NonNull
        @Override
        public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pc,parent,false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }
        @Override
        public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
            holder.name.setText(persons.get(position).getName());
            holder.haract.setText(persons.get(position).getHaract());
            holder.price.setText(String.valueOf(persons.get(position).getPrice()));
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }
        class PersonViewHolder extends RecyclerView.ViewHolder{
            TextView name,haract,price;

            PersonViewHolder(View itemView){
                super(itemView);
                name = itemView.findViewById(R.id.name);
                haract = itemView.findViewById(R.id.haract);
                price = itemView.findViewById(R.id.price);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int positionIndex = getAdapterPosition();

                        InfoGame gameView = persons.get(positionIndex);
                        Intent intent = new Intent(GameActivity.this,WorkInfoActivity.class);
                        intent.putExtra("name",gameView.getName());
                        intent.putExtra("price",Integer.toString(gameView.getPrice()));
                        intent.putExtra("cpu",gameView.getCpu());
                        intent.putExtra("cooler",gameView.getCooler());
                        intent.putExtra("motherboard",gameView.getMotherboard());
                        intent.putExtra("ram",gameView.getRam());
                        intent.putExtra("videocard",gameView.getVideoCard());
                        intent.putExtra("hdd",gameView.getHDD());
                        intent.putExtra("ssd",gameView.getSSD());
                        intent.putExtra("opticaldrive",gameView.getOpticalDrive());
                        intent.putExtra("housing",gameView.getHousing());
                        intent.putExtra("powersupply",gameView.getPowerSupply());
                        startActivity(intent);

                    }
                });
            }
        }
        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView){
            super.onAttachedToRecyclerView(recyclerView);
        }

    }
    class MyThread extends  Thread{
        @Override
        public void run() {
            while (true){
                loadProduct();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}