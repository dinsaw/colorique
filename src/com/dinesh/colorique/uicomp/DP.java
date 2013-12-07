/**
 * 
 *//*
package com.dinesh.colorique.uicomp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

*//**
 * @author dsawant
 * 
 *//*
public class DP extends SurfaceView implements SurfaceHolder.Callback {
	PanelThread _thread;
	Canvas canvas;
	
	public DP(Context context) {
		super(context);
		getHolder().addCallback(this);
	}
	public DP(Context context,AttributeSet attr) {
		super(context);
		getHolder().addCallback(this);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		this.canvas = canvas;
	}
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		setWillNotDraw(false); // Allows us to use invalidate() to call onDraw()

		_thread = new PanelThread(getHolder(), this); // Start the thread that
		_thread.setRunning(true); // will make calls to
		_thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		try {
			_thread.setRunning(false); // Tells thread to stop
			_thread.join(); // Removes thread from mem.
		} catch (InterruptedException e) {
		}
	}
}
*/