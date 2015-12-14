package com.lxj.aidl;

import android.os.RemoteException;

public class PulsImpl extends IPulsManager.Stub{

	@Override
	public int puls(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a-b;
	}

}
