package com.hehe.iserialservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISerialEventListener extends IInterface {
    int onFinishEyesBehavior(int i) throws RemoteException;

    int onGetHardVersion(int i) throws RemoteException;

    int onGetSoftVersion(int i) throws RemoteException;

    int onSerialEvent(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISerialEventListener {
        private static final String DESCRIPTOR = "com.higgs.iserialservice.ISerialEventListener";
        static final int TRANSACTION_onFinishEyesBehavior = 2;
        static final int TRANSACTION_onGetHardVersion = 3;
        static final int TRANSACTION_onGetSoftVersion = 4;
        static final int TRANSACTION_onSerialEvent = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.higgs.iserialservice.ISerialEventListener");
        }

        public static ISerialEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.higgs.iserialservice.ISerialEventListener");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISerialEventListener)) {
                return new Proxy(iBinder);
            }
            return (ISerialEventListener) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.higgs.iserialservice.ISerialEventListener");
                        int onSerialEvent = onSerialEvent(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(onSerialEvent);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.higgs.iserialservice.ISerialEventListener");
                        int onFinishEyesBehavior = onFinishEyesBehavior(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(onFinishEyesBehavior);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.higgs.iserialservice.ISerialEventListener");
                        int onGetHardVersion = onGetHardVersion(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(onGetHardVersion);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.higgs.iserialservice.ISerialEventListener");
                        int onGetSoftVersion = onGetSoftVersion(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(onGetSoftVersion);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.higgs.iserialservice.ISerialEventListener");
                return true;
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ISerialEventListener {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.higgs.iserialservice.ISerialEventListener";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.higgs.iserialservice.ISerialEventListener
            public int onSerialEvent(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.iserialservice.ISerialEventListener");
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.iserialservice.ISerialEventListener
            public int onFinishEyesBehavior(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.iserialservice.ISerialEventListener");
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.iserialservice.ISerialEventListener
            public int onGetHardVersion(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.iserialservice.ISerialEventListener");
                    obtain.writeInt(i);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.iserialservice.ISerialEventListener
            public int onGetSoftVersion(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.iserialservice.ISerialEventListener");
                    obtain.writeInt(i);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
