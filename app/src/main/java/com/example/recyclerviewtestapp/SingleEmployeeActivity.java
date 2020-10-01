package com.example.recyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

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

public class SingleEmployeeActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    RecyclerView recycler_view;
    RecyclerView.Adapter madapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ListModelClass> classArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_employee);
        initView();
        setApI();
    }

    private void setApI() {
        String url = "http://dummy.restapiexample.com/api/v1/employees";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ListModelClass modelClass = new ListModelClass();

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0 ; i< jsonArray.length();i++){
                        JSONObject det = jsonArray.getJSONObject(i);
                        modelClass.setId(det.getString("id"));
                        modelClass.setName(det.getString("employee_name"));
                        modelClass.setSalary(det.getString("employee_salary"));
                        modelClass.setAge(det.getString("employee_age"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                classArrayList.add(modelClass);
                madapter = new AdaptorClass(SingleEmployeeActivity.this,classArrayList);
                recycler_view.setAdapter(madapter);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void initView() {
        requestQueue = Volley.newRequestQueue(this);
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);
        classArrayList = new ArrayList<>();
    }
}