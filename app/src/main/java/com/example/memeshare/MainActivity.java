package com.example.memeshare;


// Made By :- Gaurav S. Lokhande
// Email :- gaurravlokhande37@gmail.com
// Linkedin :-https://www.linkedin.com/in/gauravlokhande


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageView memeImage;
    Button shareBtn, nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        memeImage=(ImageView)findViewById(R.id.memeImage);
        shareBtn=(Button)findViewById(R.id.shareBtn);
        nextBtn=(Button) findViewById(R.id.nextBtn);

    }

    // loadMeme is use to fetch meme from volley api -https://meme-api.com/gimme
    public void loadMeme(){
        String url ="https://meme-api.com/gimme";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String url = response.getString("url");

                    //load image in memeImage
                    Glide.with(MainActivity.this).load(url).into(memeImage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // To display the massage error occurred
                Toast.makeText(MainActivity.this, "Error Occurred Meme Load Failed", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        }




    public void shareMeme(View view){

    }

    public void nextMeme(View view){
        //click next load next meme
        loadMeme();

    }


}