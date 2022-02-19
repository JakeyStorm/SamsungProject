package com.example.samsungproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WorkInfoActivity extends AppCompatActivity {
    TextView name,price,cpu,cooler,motherboard,ram,videocard,hdd,ssd,opticaldrive,housing,powersupply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_info);
        init();
        getIntentInfo();
    }

    public void getIntentInfo(){
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        price.setText(intent.getStringExtra("price"));
        cpu.setText(intent.getStringExtra("cpu"));
        cooler.setText(intent.getStringExtra("cooler"));
        motherboard.setText(intent.getStringExtra("motherboard"));
        ram.setText(intent.getStringExtra("name"));
        videocard.setText(intent.getStringExtra("videocard"));
        hdd.setText(intent.getStringExtra("hdd"));
        ssd.setText(intent.getStringExtra("ssd"));
        opticaldrive.setText(intent.getStringExtra("opticaldrive"));
        housing.setText(intent.getStringExtra("housing"));
        powersupply.setText(intent.getStringExtra("powersupply"));
    }
    public void init(){
        name = findViewById(R.id.InfoName);
        price = findViewById(R.id.InfoPrice);
        cpu = findViewById(R.id.InfoCpu);
        cooler = findViewById(R.id.InfoCooler);
        motherboard = findViewById(R.id.InfoMotherboard);
        ram = findViewById(R.id.InfoRam);
        videocard = findViewById(R.id.InfoVideoCard);
        hdd = findViewById(R.id.InfoHDD);
        ssd = findViewById(R.id.InfoSSD);
        opticaldrive = findViewById(R.id.InfoOpticalDrive);
        housing = findViewById(R.id.InfoHousing);
        powersupply = findViewById(R.id.InfoPowerSupply);
    }
}