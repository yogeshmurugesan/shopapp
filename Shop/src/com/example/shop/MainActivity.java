package com.example.shop;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.shop.model.MobileList;



public class MainActivity extends ActionBarActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String url = "http://cadlab.comeze.com/mobilelist?mid=0";
	private static final String img = "http://cadlab.esy.es"; 
    private ProgressDialog proDialog;
    private List<MobileList> mobileList = new ArrayList<MobileList>();
    private ListView listView;
    private CustomListAdapter adapter;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6A00")));
        setContentView(R.layout.activity_main);
 
        listView = (ListView) findViewById(R.id.list);
        
        adapter = new CustomListAdapter(this, mobileList);
        listView.setAdapter(adapter);
 
        proDialog = new ProgressDialog(this);
        proDialog.setMessage("downloading list...");
        proDialog.show();
 

        JsonArrayRequest movieReq = new JsonArrayRequest(Request.Method.GET, url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hideproDialog();

                        for (int i = 0; i < response.length(); i++) {
                            try {
 
                                JSONObject obj = response.getJSONObject(i);
                                MobileList ml= new MobileList();
                                ml.setMid(obj.getString("mid"));
                                ml.setModel(obj.getString("model"));
                                ml.setName(obj.getString("name"));
                                ml.setPrice(obj.getString("price"));
                                ml.setImgurl(img+obj.getString("imgurl"));

                                mobileList.add(ml);
 
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
 
                        }
 
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(), "Sorry, No active network connection", Toast.LENGTH_SHORT).show();
                        hideproDialog();
                    }
                });
        
        AppController.getInstance().addToRequestQueue(movieReq);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent in=new Intent(getApplicationContext(),MobileDetail.class);
				in.putExtra("mobilelist",(MobileList)mobileList.get(position));
				startActivity(in);
			}
		});
    }
 
    @Override
    public void onDestroy() {
        super.onDestroy();
        hideproDialog();
    }
 
    private void hideproDialog() {
        if (proDialog != null) {
            proDialog.dismiss();
            proDialog = null;
        }
    }
 


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
