package com.lxj.aidlpool;

import java.util.concurrent.CountDownLatch;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.lxj.aidl.AddImpl;
import com.lxj.aidl.IBinderPool;
import com.lxj.aidl.PulsImpl;

public class BinderPool {
	private static volatile BinderPool mPool;
	private Context mContext;
	private IBinderPool iBinderPool;
	private CountDownLatch mCountDownLatch;
	private BinderPool(Context context){
		mContext = context.getApplicationContext();
		connectBinderPoolService();
	}
	//µ¥Àý
	public static BinderPool getInstance(Context context){
		if(mPool == null){
			synchronized(BinderPool.class){
				if(mPool == null){
					mPool = new BinderPool(context);
				}
			}
		}
		return mPool;
	}
	 public synchronized void connectBinderPoolService(){
		 Intent i = new Intent(mContext,AidlManagerService.class);
			mContext.bindService(i, conn, Context.BIND_AUTO_CREATE);
			mCountDownLatch = new CountDownLatch(1);
			try {
				mCountDownLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 };
	 private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			iBinderPool = IBinderPool.Stub.asInterface(service);
			  try {
				  iBinderPool.asBinder().linkToDeath(deathRecipient, 0);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  mCountDownLatch.countDown();
		}
	};
	private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
		
		@Override
		public void binderDied() {
			// TODO Auto-generated method stub
			if(iBinderPool != null){
				iBinderPool.asBinder().unlinkToDeath(deathRecipient, 0);
				iBinderPool = null;
			}
			connectBinderPoolService();
		}
	};
	
	public IBinder queryBinder(int binderCode){
		 IBinder binder = null;
		 try {
			if(iBinderPool != null){
				 binder = iBinderPool.queryBinder(binderCode);
			 }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return binder;
	}
	
	public static class BinderImpl extends IBinderPool.Stub{

		@Override
		public IBinder queryBinder(int binderCode) throws RemoteException {
			// TODO Auto-generated method stub
			Binder binder = null;
			switch(binderCode){
			case 1:
				binder = new AddImpl();
				break;
			case 2:
				binder = new PulsImpl();
				break;
			}
			return binder;
		}
		
	}
}
