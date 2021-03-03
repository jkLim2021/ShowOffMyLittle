package com.jk.showoffmylittle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {
    static String baseUrl="http://jk2101.dothome.co.kr/";

    static Retrofit getRetrofitInstanceGson(){
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit= builder.build();

        return retrofit;
    }

    static Retrofit getRetrofitInstanceScalars(){
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit= builder.build();

        return retrofit;
    }



}
