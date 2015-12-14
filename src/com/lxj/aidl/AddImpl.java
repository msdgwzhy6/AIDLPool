package com.lxj.aidl;

import android.os.RemoteException;

public class AddImpl extends IAddManager.Stub {

	@Override
	public int add(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a+b;
	}

}
