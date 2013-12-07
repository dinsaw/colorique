package com.dinesh.colorique;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.dinesh.colorique.uicomp.DrawingView;
import com.ljrnegot.ujvxmmkq168203.AirSDK;

public class MainActivity extends Activity {
	EditText editTextR,editTextG,editTextB,editTextHash;
	Button hashButton;
	SeekBar seekBarR,seekBarG,seekBarB;
	DrawingView drawingView;
	private boolean progressChange,hashChange,rgbChange;
	private AirSDK airsdk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if(airsdk==null)
        	airsdk=new AirSDK(getApplicationContext(), null, true);
        
        ini();
    }


    private void ini() {
    	editTextR = (EditText) findViewById(R.id.editTextR);
    	editTextG = (EditText) findViewById(R.id.editTextG);
    	editTextB = (EditText) findViewById(R.id.editTextB);
    	editTextHash = (EditText) findViewById(R.id.editTextHash);
    	
    	editTextHash.setText("000000");
    	
    	seekBarR = (SeekBar) findViewById(R.id.seekBarR);
    	seekBarG = (SeekBar) findViewById(R.id.seekBarG);
    	seekBarB = (SeekBar) findViewById(R.id.seekBarB);
    	
    	hashButton = (Button) findViewById(R.id.buttonHash);
 
    	drawingView = (DrawingView) findViewById(R.id.drawingView);
    	drawingView.setBackgroundColor(Color.BLACK);
 
    	editTextR.addTextChangedListener(new RGBTextWatcher());
    	editTextG.addTextChangedListener(new RGBTextWatcher());
    	editTextB.addTextChangedListener(new RGBTextWatcher());
    	
    	seekBarR.setOnSeekBarChangeListener(new RGBOnSeekBarChangeListener());
    	seekBarG.setOnSeekBarChangeListener(new RGBOnSeekBarChangeListener());
    	seekBarB.setOnSeekBarChangeListener(new RGBOnSeekBarChangeListener());
    	
    	hashButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				setHashChange(true);
				try {
					handleHashChange();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), R.string.errorTextHash, Toast.LENGTH_SHORT).show();				
				}
				setHashChange(false);
			}
		});
	}

    private void setRGBText() {
    	if (!isRgbChange()) {
    		editTextR.setText(""+seekBarR.getProgress());
    		editTextG.setText(""+seekBarG.getProgress());
    		editTextB.setText(""+seekBarB.getProgress());
		}	
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	private void handleRGBChange() {
		int r = Integer.parseInt(editTextR.getText().toString());
		int g = Integer.parseInt(editTextG.getText().toString());
		int b = Integer.parseInt(editTextB.getText().toString());
		
		int colorInt = Color.rgb(r, g, b);
		drawingView.setBackgroundColor(colorInt);
	}
	private void setSeekBars() {
		if (!isProgressChange()) {
			seekBarR.setProgress(Integer.parseInt(editTextR.getText().toString()));
			seekBarG.setProgress(Integer.parseInt(editTextG.getText().toString()));
			seekBarB.setProgress(Integer.parseInt(editTextB.getText().toString()));
		}
	}
	public void setHashText() {
		if (!isHashChange()) {
			int r = Integer.parseInt(editTextR.getText().toString());
			int g = Integer.parseInt(editTextG.getText().toString());
			int b = Integer.parseInt(editTextB.getText().toString());
			
			int colorInt = Color.rgb(r, g, b);
			String hexColor = String.format("0x%08X", (0xFFFFFFFF & colorInt));
			editTextHash.setText(hexColor.substring(4));
		}
	}
	private void handleHashChange() throws Exception {
		String hashColor = editTextHash.getText().toString();
		int c = Color.parseColor("#"+hashColor);
		editTextR.setText(Color.red(c)+"");
		editTextG.setText(Color.green(c)+"");
		editTextB.setText(Color.blue(c)+"");
	}
	
	public boolean isProgressChange() {
		return progressChange;
	}


	public void setProgressChange(boolean progressChange) {
		this.progressChange = progressChange;
	}

	public boolean isHashChange() {
		return hashChange;
	}


	public void setHashChange(boolean hashChange) {
		this.hashChange = hashChange;
	}

	public boolean isRgbChange() {
		return rgbChange;
	}


	public void setRgbChange(boolean rgbChange) {
		this.rgbChange = rgbChange;
	}

	class RGBOnKeyListener implements OnKeyListener {

		@Override
		public boolean onKey(View view, int i, KeyEvent event) {
			return false;
		}
		
	}
	class RGBOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			setProgressChange(true);
			setRGBText();
			setHashText();
			handleRGBChange();
			setProgressChange(false);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
		
	}
	class RGBTextWatcher implements TextWatcher {
		@Override
		public void afterTextChanged(Editable e) {
			int lenR = editTextR.getText().toString().length();
			int lenG = editTextG.getText().toString().length();
			int lenB = editTextB.getText().toString().length();
			if (lenR==0 || lenG==0 || lenB==0) {
				System.out.println("One of rgb is zero");
			} else {
				setRgbChange(true);
				setSeekBars();
				setRgbChange(false);
			}
				
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {}
		
	}
}
