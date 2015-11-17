package com.example.shop;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.shop.model.MobileList;
import com.example.shop.model.MobileSpec;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MobileDetail extends ActionBarActivity {
	
	private static final String TAG = MobileDetail.class.getSimpleName();
	private String url = "http://cadlab.comeze.com/mobilespec?mid=";
    private ProgressDialog proDialog;
    private List<MobileList> mobileList = new ArrayList<MobileList>();
    private ListView listView;
    ImageLoader imageLoader;
    private CustomListAdapter adapter;
    MobileList ml;
    MobileSpec ms = new MobileSpec();
    NetworkImageView thumbNail; 
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6A00")));
		setContentView(R.layout.activity_mobile_detail);
		
		ml = (MobileList) getIntent().getSerializableExtra("mobilelist");
		url = url + ml.getMid();
		
		 proDialog = new ProgressDialog(this);
	     proDialog.setMessage("downloading spec...");
	     proDialog.show();
	     
	     imageLoader = AppController.getInstance().getImageLoader();
	     thumbNail = (NetworkImageView) findViewById(R.id.spec_thumbnail);    
	     thumbNail.setDefaultImageResId(R.drawable.loading6);
	     
	     JsonArrayRequest movieReq = new JsonArrayRequest(Request.Method.GET, url, (String) null,
	                new Response.Listener<JSONArray>() {
	                    @Override
	                    public void onResponse(JSONArray response) {
	                        Log.d(TAG, response.toString());
	                        hideproDialog();

	                        try {
	                                JSONObject obj = response.getJSONObject(0);
	                                ms.setMid(obj.getString("mid"));
	                                ms.setSim(obj.getString("sim"));
	                                ms.setOs(obj.getString("os"));
	                                ms.setDisplay(obj.getString("display"));
	                                ms.setCamera(obj.getString("camera"));
	                                ms.setBattery(obj.getString("battery"));
	                                ms.setMemory(obj.getString("memory"));
	                                setData(ml, ms); 
	                             } catch (JSONException e) {
	                                e.printStackTrace();
	                            }
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
	        
	        
	        
	}

	protected void setData(MobileList ml, MobileSpec ms) {
		// TODO Auto-generated method stub
	    
        TextView name = (TextView)findViewById(R.id.spec_name);
        TextView sim = (TextView)findViewById(R.id.spec_sim);
        TextView os= (TextView)findViewById(R.id.spec_os);
        TextView display = (TextView)findViewById(R.id.spec_display);
        TextView camera = (TextView)findViewById(R.id.spec_camera);
        TextView battery = (TextView)findViewById(R.id.spec_battery);
        TextView memory = (TextView)findViewById(R.id.spec_memory);
        TextView price = (TextView)findViewById(R.id.spec_price);
        
        thumbNail.setImageUrl(ml.getImgurl(), imageLoader);
        
        
        name.setText(ml.getName() + " - " + ml.getModel());
        sim.setText("Sim: "+ms.getSim());
        os.setText("Os: "+ms.getOs());
        display.setText("Display: "+ms.getDisplay());
        camera.setText("Camera: "+ms.getCamera());
        battery.setText("Battery: "+ms.getBattery());
        memory.setText("Memory: "+ms.getMemory());
        price.setText("Price: "+ml.getPrice());
        
        Button bt1 = (Button)findViewById(R.id.spec_button1);
        bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mobile_detail, menu);
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
}

