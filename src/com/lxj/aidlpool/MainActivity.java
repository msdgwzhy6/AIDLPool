package com.lxj.aidlpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;

import com.lxj.aidl.AddImpl;
import com.lxj.aidl.IAddManager;
import com.lxj.aidl.IPulsManager;
import com.lxj.aidl.PulsImpl;

public class MainActivity extends Activity {
	private BinderPool mPool;
	//private IBinder binder;
	private IAddManager addManager;
	private IPulsManager pulsManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);  
	       fixedThreadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mPool = BinderPool.getInstance(MainActivity.this);
				IBinder addBinder = null;
				addBinder = mPool.queryBinder(1);
				IBinder plusBinder = null;
				plusBinder = mPool.queryBinder(2);
				addManager = (IAddManager)AddImpl.asInterface(addBinder);
				pulsManager = (IPulsManager)PulsImpl.asInterface(plusBinder);
				try {
					Log.e("3 add 5 =", addManager.add(3, 5)+"");
					Log.e("10 add 5 =", pulsManager.puls(10, 5)+"");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
