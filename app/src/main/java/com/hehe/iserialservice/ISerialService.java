package com.hehe.iserialservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISerialService extends IInterface {
    boolean SendMsg(int i) throws RemoteException;

    void closeSerial(String str) throws RemoteException;

    void openSerial() throws RemoteException;

    boolean setHyExtEventListener(String str, ISerialEventListener iSerialEventListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISerialService {
        private static final String DESCRIPTOR = "com.higgs.iserialservice.ISerialService";
        static final int TRANSACTION_SendMsg = 3;
        static final int TRANSACTION_closeSerial = 2;
        static final int TRANSACTION_openSerial = 1;
        static final int TRANSACTION_setHyExtEventListener = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.higgs.iserialservice.ISerialService");
        }

        public static ISerialService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.higgs.iserialservice.ISerialService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISerialService)) {
                return new Proxy(iBinder);
            }
            return (ISerialService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.higgs.iserialservice.ISerialService");
                        openSerial();
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("com.higgs.iserialservice.ISerialService");
                        closeSerial(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface("com.higgs.iserialservice.ISerialService");
                        boolean SendMsg = SendMsg(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(SendMsg ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.higgs.iserialservice.ISerialService");
                        boolean hyExtEventListener = setHyExtEventListener(parcel.readString(), ISerialEventListener.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(hyExtEventListener ? 1 : 0);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.higgs.iserialservice.ISerialService");
                return true;
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ISerialService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.higgs.iserialservice.ISerialService";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.higgs.iserialservice.ISerialService
            public void openSerial() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.iserialservice.ISerialService");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.iserialservice.ISerialService
            public void closeSerial(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.iserialservice.ISerialService");
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.iserialservice.ISerialService
            public boolean SendMsg(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.iserialservice.ISerialService");
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.iserialservice.ISerialService
            public boolean setHyExtEventListener(String str, ISerialEventListener iSerialEventListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.iserialservice.ISerialService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSerialEventListener != null ? iSerialEventListener.asBinder() : null);
                    boolean z = false;
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
