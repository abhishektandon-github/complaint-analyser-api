package com.example.user.complaintanalyser;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RequestQueue requestQueue;
    ProgressDialog progressDialog;

    TextView textView;
    EditText editText;
    Button button;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        button.setOnClickListener(this);
    }

    public void jsonObjReq() {
        String url = "http://192.168.43.133:5000/post";
        Log.e("error", "jsonObjReq was called");
        data = editText.getText().toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String temp = response.toString();
                    textView.setText(temp);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
               }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("data", data);
                        return paramV;
                    }
            };

            requestQueue.add(stringRequest);;
        }

    @Override
    public void onClick(View v) {
        if(v == button) {
            jsonObjReq();
        }
    }
}























































// GET METHOD

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    progressDialog.setMessage("Please wait...");
//                    progressDialog.show();
//
//                    data = response.getString("data");
//                    Log.e("MyError","Message: "+data);
//                    textView.setText(data);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Toast.makeText(MainActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//       }
