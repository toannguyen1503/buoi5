package com.example.buoi6_19dthd4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.libs.Interfaces.Methods;
import com.example.libs.Model.ProductListResponse;
import com.example.libs.Model.RoomListResponse;
import com.example.libs.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview= findViewById(R.id.listview);

    }

    @Override
    protected void onStart() {
        super.onStart();

        loadData();
    }

    void loadData(){
        List<String> productList = new ArrayList<String>();
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<ProductListResponse> call = methods.getProductList();
        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                for(int i = 0; i< response.body().data.size();i++){
                    productList.add(response.body().data.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, productList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {
                Log.v("event", t.getMessage());
            }
        });

    }

    public void InitNewRoom(View view) {
        Intent intent = new Intent(MainActivity.this,MainActivityDetail.class);
        startActivity(intent);
    }
}