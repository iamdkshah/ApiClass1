package com.example.apiclass1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apiclass1.api.EmpInter;
import com.example.apiclass1.models.Flag;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlaggApiActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    Retrofit retrofit;
    EmpInter empInter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flagg_api);

        textView = findViewById(R.id.txtView);
        imageView = findViewById(R.id.imgFlag);

        getInstance();
        getCountryById(5);

    }

    private void getInstance(){
        retrofit = new Retrofit.Builder().baseUrl("http://sujitg.com.np/api/").addConverterFactory(GsonConverterFactory.create()).build();
        empInter = retrofit.create(EmpInter.class);
    }

    private void getCountryById(int id){
        Call<Flag> flagCall = empInter.getFlagById(id);

        flagCall.enqueue(new Callback<Flag>() {
            @Override
            public void onResponse(Call<Flag> call, Response<Flag> response) {
                textView.setText(response.body().getCountry());
                Picasso.with(FlaggApiActivity.this).load("http://sujitg.com.np/wc/teams/"+ response.body().getFile()).into(imageView);
            }

            @Override
            public void onFailure(Call<Flag> call, Throwable t) {
                Log.d("Ex", t.getMessage());

                
            }
        });
    }
}
