package course.labs.activitylab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOne extends Activity {

	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";
	
	private static final String ON_CREATE = "Entered the onCreate() method";
	private static final String ON_START = "Entered the onStart() method";
	private static final String ON_RESUME = "Entered the onResume() method";
	private static final String ON_PAUSE = "Entered the onPause() method";
	private static final String ON_STOP = "Entered the onStop() method";
	private static final String ON_RESTART = "Entered the onRestart() method";
	private static final String ON_DESTROY = "Entered the onDestroy() method";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";

	// Lifecycle counters
	
	private static int mCreate;
	private static int mStart;
	private static int mResume;
	private static int mRestart;

	private static TextView mTvCreate;
	private static TextView mTvStart;
	private static TextView mTvResume;
	private static TextView mTvRestart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		
		mTvCreate = (TextView) findViewById(R.id.create);
		mTvStart = (TextView) findViewById(R.id.start);
		mTvResume = (TextView) findViewById(R.id.resume);
		mTvRestart = (TextView) findViewById(R.id.restart);
		
		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo); 
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentActivityTwo = new Intent(ActivityOne.this, ActivityTwo.class);
				startActivity(intentActivityTwo);
			}
		});
		
		// Check for previously saved state
		if (savedInstanceState != null) {
			mCreate = savedInstanceState.getInt(CREATE_KEY);
			mRestart = savedInstanceState.getInt(RESTART_KEY);
			mStart = savedInstanceState.getInt(START_KEY);
			mResume = savedInstanceState.getInt(RESUME_KEY);	
		
		}
		
		Log.i(TAG,ON_CREATE);
		// Update the appropriate count variable
		mCreate++;
		// Update the user interface via the displayCounts() method
		displayCounts();	

	}

	// Lifecycle callback overrides

	@Override
	public void onStart() {
		super.onStart();
		Log.i(TAG,ON_START);

		// Update the appropriate count variable
		mStart++;
		// Update the user interface
		displayCounts();

	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG,ON_RESUME);

		// Update the appropriate count variable
		mResume++;
		// Update the user interface
		displayCounts();

	}

	@Override
	public void onPause() {
		super.onPause();

		Log.i(TAG,ON_PAUSE);

	}

	@Override
	public void onStop() {
		super.onStop();

		Log.i(TAG,ON_STOP);

	}

	@Override
	public void onRestart() {
		super.onRestart();
		Log.i(TAG,ON_RESTART);
		// Update the appropriate count variable
		mRestart++;
		// Update the user interface
		displayCounts();


	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG,ON_DESTROY);
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putInt(CREATE_KEY, mCreate);
		savedInstanceState.putInt(RESTART_KEY, mRestart);
		savedInstanceState.putInt(START_KEY, mStart);
		savedInstanceState.putInt(RESUME_KEY, mResume);

	}
	
	// Updates the displayed counters
	public void displayCounts() {

		mTvCreate.setText("onCreate() calls: " + mCreate);
		mTvStart.setText("onStart() calls: " + mStart);
		mTvResume.setText("onResume() calls: " + mResume);
		mTvRestart.setText("onRestart() calls: " + mRestart);
	
	}
}
