package com.project.easyclock;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	// Define Date Format
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    private TextView mClock;
    
    private boolean mActive;
    
    private final Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mClock = (TextView) findViewById(R.id.system_time);
		mClock.setTextSize(250.0f); // set size
		mClock.setTextColor(Color.WHITE); // set font color
		
		// Start clock
        startClock();
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
	
	/*
	 * Update UI Time
	 */
	private final Runnable mRunnable = new Runnable() {
        public void run() {
            if (mActive) {
                if (mClock != null) {
                    mClock.setText(getTime());
                }
                mHandler.postDelayed(mRunnable, 1000);
            }
        }
    };
	
    /*
     * Get System time
     */
	private String getTime() {
        return sdf.format(new Date(System.currentTimeMillis()));
    }

	/*
	 * Start clock
	 */
    private void startClock() {
        mActive = true;
        mHandler.post(mRunnable);
    }
}
