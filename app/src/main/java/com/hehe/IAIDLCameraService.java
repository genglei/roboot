package com.hehe;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;


/* loaded from: classes.dex */
public interface IAIDLCameraService extends IInterface {
    boolean isOpenCamera() throws RemoteException;

    void openCamera() throws RemoteException;

    void registerClientCallback(IAIDLClientCallback iAIDLClientCallback) throws RemoteException;

    void stopCamera() throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAIDLCameraService {
        private static final String DESCRIPTOR = "com.higgs.deliveryrobot.IAIDLCameraService";
        static final int TRANSACTION_isOpenCamera = 4;
        static final int TRANSACTION_openCamera = 1;
        static final int TRANSACTION_registerClientCallback = 3;
        static final int TRANSACTION_stopCamera = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.higgs.deliveryrobot.IAIDLCameraService");
        }

        public static IAIDLCameraService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.higgs.deliveryrobot.IAIDLCameraService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAIDLCameraService)) {
                return new Proxy(iBinder);
            }
            return (IAIDLCameraService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.higgs.deliveryrobot.IAIDLCameraService");
                        openCamera();
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("com.higgs.deliveryrobot.IAIDLCameraService");
                        stopCamera();
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface("com.higgs.deliveryrobot.IAIDLCameraService");
                        registerClientCallback(IAIDLClientCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface("com.higgs.deliveryrobot.IAIDLCameraService");
                        boolean isOpenCamera = isOpenCamera();
                        parcel2.writeNoException();
                        parcel2.writeInt(isOpenCamera ? 1 : 0);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.higgs.deliveryrobot.IAIDLCameraService");
                return true;
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IAIDLCameraService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.higgs.deliveryrobot.IAIDLCameraService";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.higgs.deliveryrobot.IAIDLCameraService
            public void openCamera() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.IAIDLCameraService");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.deliveryrobot.IAIDLCameraService
            public void stopCamera() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.IAIDLCameraService");
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.deliveryrobot.IAIDLCameraService
            public void registerClientCallback(IAIDLClientCallback iAIDLClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.IAIDLCameraService");
                    obtain.writeStrongBinder(iAIDLClientCallback != null ? iAIDLClientCallback.asBinder() : null);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.deliveryrobot.IAIDLCameraService
            public boolean isOpenCamera() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.IAIDLCameraService");
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
