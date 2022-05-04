package com.akinpelumi.crop2cashtest.repository;

import android.os.AsyncTask;
import android.util.Log;

import com.akinpelumi.crop2cashtest.model.ExhibitModel;
import com.akinpelumi.crop2cashtest.model.ExhibitsLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RestExhibitsLoader extends AsyncTask<String, Void, List<ExhibitModel>> {
    private final ExhibitsLoader callBack;

    public RestExhibitsLoader(ExhibitsLoader callBack) {
        this.callBack = callBack;
    }

    public List<ExhibitModel> fetchAllExhibitAsyncTask(){
        try{
            String url = "https://my-json-server.typicode.com/Reyst/exhibit_db/list";

            OkHttpClient client = new OkHttpClient()
                    .newBuilder()
                    .connectTimeout(0, TimeUnit.SECONDS)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    //.addHeader("Authorization", appToken)
                    .build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String callResponse = response.body().string();
            int statusCode = response.code();

            Gson gson = new Gson();
            //return gson.fromJson(callResponse, TicketModel.class);

            Type userListType = new TypeToken<ArrayList<ExhibitModel>>(){}.getType();

            ArrayList<ExhibitModel> exArray = gson.fromJson(callResponse, userListType);

            //return gson.fromJson(callResponse, (Type) ExhibitModel[].class);
            return exArray;
        }
        catch (Exception ex){
            Log.d("NotifyException::::", ex.getMessage());
        }
        return null;
    }
    @Override
    protected List<ExhibitModel> doInBackground(String... strings) {
        try{
            return fetchAllExhibitAsyncTask();
        }
        catch(Exception ex){
            Log.d("NotifyException::::", ex.getMessage());
        }
        return  null;
    }
    @Override
    protected void onPostExecute(List<ExhibitModel> exhibits) {
        callBack.getExhibitList(exhibits);
    }
}

