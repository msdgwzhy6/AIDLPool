package com.lxj.aidlpool;

import com.lxj.aidl.IBinderPool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AidlManagerService extends Service {
	private IBinder binder = new BinderPool.BinderImpl();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
