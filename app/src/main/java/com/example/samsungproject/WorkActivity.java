package com.example.samsungproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkActivity extends AppCompatActivity {
    int sum2;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        sum2 = getIntent().getIntExtra("SUM",0);
        recyclerView = findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        loadProduct();
    }
    protected void loadProduct() {
        ApiService api = RetroClient.getApiService();

        retrofit2.Call<List<InfoWork>> call = api.getWorkPC();

        call.enqueue(new Callback<List<InfoWork>>() {
            @Override
            public void onResponse(Call<List<InfoWork>> call, Response<List<InfoWork>> response) {
                List<InfoWork> sort =  response.body();
                int i =0;
                Iterator<InfoWork> it = sort.iterator();
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
            public void onFailure(Call<List<InfoWork>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
    class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
        private Context context;

        List<InfoWork> persons;

        RVAdapter(List<InfoWork> workView, Context context, int sum2) {
            this.persons = workView;
            this.context = context;
        }
        @NonNull
        @Override
        public RVAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pc,parent,false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }
        @Override
        public void onBindViewHolder(@NonNull RVAdapter.PersonViewHolder holder, int position) {
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

                        InfoWork workView = persons.get(positionIndex);
                        Intent intent = new Intent(WorkActivity.this,WorkInfoActivity.class);
                        intent.putExtra("name",workView.getName());
                        intent.putExtra("price",Integer.toString(workView.getPrice()));
                        intent.putExtra("cpu",workView.getCpu());
                        intent.putExtra("cooler",workView.getCooler());
                        intent.putExtra("motherboard",workView.getMotherboard());
                        intent.putExtra("ram",workView.getRam());
                        intent.putExtra("videocard",workView.getVideoCard());
                        intent.putExtra("hdd",workView.getHDD());
                        intent.putExtra("ssd",workView.getSSD());
                        intent.putExtra("opticaldrive",workView.getOpticalDrive());
                        intent.putExtra("housing",workView.getHousing());
                        intent.putExtra("powersupply",workView.getPowerSupply());
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
}