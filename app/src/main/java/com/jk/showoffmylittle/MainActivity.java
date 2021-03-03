package com.jk.showoffmylittle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    ArrayList<MarketItem> items= new ArrayList<>();
    RecyclerView recyclerView;
    MarketAdapter adapter;

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycler);
        adapter= new MarketAdapter(this, items);
        recyclerView.setAdapter(adapter);

        refreshLayout = findViewById(R.id.layout_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                refreshLayout.setRefreshing(false);
            }
        });


        //동적퍼미션
        String[] permissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if( ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_DENIED ){
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }


    //이 액티비티가 화면에 보여질 때 리사이클러가 보여줄 아이템들을 서버DB에서 불러오기
    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }

    //서버에서 데이터를 불러오는 기능 메소드
    void loadData(){

        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
        Call<ArrayList<MarketItem>> call= retrofitService.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<MarketItem>>() {
            @Override
            public void onResponse(Call<ArrayList<MarketItem>> call, Response<ArrayList<MarketItem>> response) {

                //기존데이터들 모두 제거
                items.clear();
                adapter.notifyDataSetChanged();

                //결과로 받아온 ArrayList<MarketItem>을 items에 추가
                ArrayList<MarketItem> list= response.body();
                for(MarketItem item: list){
                    items.add(0, item);
                    adapter.notifyItemInserted(0);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<MarketItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }



    public void clickEdit(View view) {
        Intent intent= new Intent(this, EditActivity.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //OptionMenu 의 항목(MenuItem)을 선택하면
    //자동으로 발동하는 콜백 메소드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id= item.getItemId();

        switch (id){
            case R.id.market_favor:

                Intent intent1= new Intent(this, market_favor.class);
                startActivity(intent1);

                break;

            case R.id.market_my:
                Intent intent2= new Intent(this, market_my.class);
                startActivity(intent2);

                break;

            case R.id.market_logout:
//                Toast.makeText(this, "market_logout", Toast.LENGTH_SHORT).show();

                Intent intent3= new Intent(this, LoginActivity.class);
                startActivity(intent3);


                break;
        }



        return super.onOptionsItemSelected(item);
    }
}