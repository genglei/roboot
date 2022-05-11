package com.hehe;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IAIDLClientCallback extends IInterface {
    void updateSay(String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAIDLClientCallback {
        private static final String DESCRIPTOR = "com.higgs.deliveryrobot.IAIDLClientCallback";
        static final int TRANSACTION_updateSay = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.higgs.deliveryrobot.IAIDLClientCallback");
        }

        public static IAIDLClientCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.higgs.deliveryrobot.IAIDLClientCallback");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAIDLClientCallback)) {
                return new Proxy(iBinder);
            }
            return (IAIDLClientCallback) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.higgs.deliveryrobot.IAIDLClientCallback");
                updateSay(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.higgs.deliveryrobot.IAIDLClientCallback");
                return true;
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IAIDLClientCallback {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.higgs.deliveryrobot.IAIDLClientCallback";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.higgs.deliveryrobot.IAIDLClientCallback
            public void updateSay(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.IAIDLClientCallback");
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
