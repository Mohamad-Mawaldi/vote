package com.example.hero.newevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity{
    final static String GET_SONGS_LIST_URL = "https://future-restaurant.glitch.me/";



    private ArrayList<Song> songs;
    private CustomAdapter customAdapter;
    private ListView listView;
    private CustomAdapter adapter;
    private RequestParams params;
    private AsyncHttpClient client;
    static String VOTE_SONGS_URL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songs = new ArrayList<>();
        listView = (ListView)findViewById(R.id.list_item);
        params= new RequestParams();
        adapter = new CustomAdapter(MainActivity.this,songs);
        client = new AsyncHttpClient(443);

        LetsDoSomNetworking();



    }
    private void LetsDoSomNetworking(){
        client = new AsyncHttpClient(443);
        client.get(GET_SONGS_LIST_URL+""+"songs_list", new JsonHttpResponseHandler(){

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                Log.d("sta",statusCode+"");

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                int lengthOfResponse = response.length();
                for(int i =0; i < lengthOfResponse; i++){
                    try {
                        JSONObject currentResponse =  response.getJSONObject(i);

                        songs.add(new Song(currentResponse.getString("name"), currentResponse.getInt("id")));
                        Log.d("works",currentResponse+"");
                        Log.d("array", songs.size()+"");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new CustomAdapter(MainActivity.this,songs);
                listView.setAdapter(adapter);

                adapter.setIdButtonClickedListener(new IdButtonClickedListener() {

                    @Override
                    public void onIdButtonClicked(int id) {
                        boolean Clicked = false;

                        if (id == 1) {
                            VOTE_SONGS_URL = "https://future-restaurant.glitch.me/up_vote?id=1";
                            client.put(VOTE_SONGS_URL,new JsonHttpResponseHandler());}
                        if (id == 2) {
                            VOTE_SONGS_URL = "https://future-restaurant.glitch.me/up_vote?id=2";
                            client.put(VOTE_SONGS_URL,new JsonHttpResponseHandler());}
                        if (id == 3) {
                            VOTE_SONGS_URL = "https://future-restaurant.glitch.me/up_vote?id=3";
                            client.put(VOTE_SONGS_URL,new JsonHttpResponseHandler());}
                        if (id == 4) {
                            VOTE_SONGS_URL = "https://future-restaurant.glitch.me/up_vote?id=4";
                            client.put(VOTE_SONGS_URL,new JsonHttpResponseHandler());}}

                              })
                       ;}

        } );
} }
