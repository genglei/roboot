package com.hehe.iserialservice;

import android.os.RemoteException;

/* loaded from: classes.dex */
public class SerialEventListener extends ISerialEventListener.Stub {
    public int onFinishEyesBehavior(int i) throws RemoteException {
        return 0;
    }

    @Override // com.higgs.iserialservice.ISerialEventListener
    public int onGetHardVersion(int i) throws RemoteException {
        return 0;
    }

    @Override // com.higgs.iserialservice.ISerialEventListener
    public int onGetSoftVersion(int i) throws RemoteException {
        return 0;
    }

    public int onSerialEvent(int i) throws RemoteException {
        return 0;
    }
}
