package com.example.buoi6_19dthd4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libs.Interfaces.Methods;
import com.example.libs.Model.ResponseBase;
import com.example.libs.Model.Room;
import com.example.libs.Model.RoomListResponse;
import com.example.libs.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityDetail extends AppCompatActivity {
    EditText txtName, txtCode;
    Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);
        txtName = findViewById(R.id.txtName);
        txtCode = findViewById(R.id.txtCode);
    }

    public void addNewRoom(View view) {
        Room r = new Room();
        r.setName(txtName.getText().toString());
        r.setCode(txtCode.getText().toString());
        Call<ResponseBase> call = methods.insertRoom(r);
        call.enqueue(new Callback<ResponseBase>() {
            @Override
            public void onResponse(Call<ResponseBase> call, Response<ResponseBase> response) {
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBase> call, Throwable t) {
                Toast.makeText(MainActivityDetail.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}