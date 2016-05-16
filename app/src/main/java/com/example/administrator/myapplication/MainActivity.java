package com.example.administrator.myapplication;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.service.voice.VoiceInteractionSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {
ArrayList<HashMap<String,String>>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list= new ArrayList<HashMap<String,String>>();
        String url="http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("responseeeeee", jsonObject.toString());
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("worldpopulation");
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject objectj = jsonArray.getJSONObject(i);
                        String rank=objectj.getString("rank");
                        String country=objectj.getString("country");
                        String population=objectj.getString("population");
                        HashMap<String,String>map= new HashMap <String,String>();
                        map.put("rank", rank);
                        map.put("country",country);
                        map.put("population",population);
                        list.add(map);



                    }
                    ListAdapter adapter =new SimpleAdapter(MainActivity.this,list,R.layout.layout_list,new String[]{"rank","country","population"},new int[]{R.id.textView,R.id.textView2,R.id.textView3});
                setListAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}
