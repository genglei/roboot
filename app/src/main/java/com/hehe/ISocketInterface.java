package com.hehe;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISocketInterface extends IInterface {
    boolean eStop(boolean z) throws RemoteException;

    boolean getCurrentMap() throws RemoteException;

    boolean getQueryList() throws RemoteException;

    boolean getRobotStatus() throws RemoteException;

    boolean moveCancel() throws RemoteException;

    boolean moveCruise(String str, float f, int i) throws RemoteException;

    boolean moveTarget(String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISocketInterface {
        private static final String DESCRIPTOR = "com.higgs.deliveryrobot.ISocketInterface";
        static final int TRANSACTION_eStop = 6;
        static final int TRANSACTION_getCurrentMap = 5;
        static final int TRANSACTION_getQueryList = 7;
        static final int TRANSACTION_getRobotStatus = 4;
        static final int TRANSACTION_moveCancel = 1;
        static final int TRANSACTION_moveCruise = 3;
        static final int TRANSACTION_moveTarget = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.higgs.deliveryrobot.ISocketInterface");
        }

        public static ISocketInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.higgs.deliveryrobot.ISocketInterface");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISocketInterface)) {
                return new Proxy(iBinder);
            }
            return (ISocketInterface) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.higgs.deliveryrobot.ISocketInterface");
                        boolean moveCancel = moveCancel();
                        parcel2.writeNoException();
                        parcel2.writeInt(moveCancel ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.higgs.deliveryrobot.ISocketInterface");
                        boolean moveTarget = moveTarget(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(moveTarget ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.higgs.deliveryrobot.ISocketInterface");
                        boolean moveCruise = moveCruise(parcel.readString(), parcel.readFloat(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(moveCruise ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.higgs.deliveryrobot.ISocketInterface");
                        boolean robotStatus = getRobotStatus();
                        parcel2.writeNoException();
                        parcel2.writeInt(robotStatus ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface("com.higgs.deliveryrobot.ISocketInterface");
                        boolean currentMap = getCurrentMap();
                        parcel2.writeNoException();
                        parcel2.writeInt(currentMap ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface("com.higgs.deliveryrobot.ISocketInterface");
                        boolean eStop = eStop(parcel.readInt() != 0);
                        parcel2.writeNoException();
                        parcel2.writeInt(eStop ? 1 : 0);
                        return true;
                    case 7:
                        parcel.enforceInterface("com.higgs.deliveryrobot.ISocketInterface");
                        boolean queryList = getQueryList();
                        parcel2.writeNoException();
                        parcel2.writeInt(queryList ? 1 : 0);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.higgs.deliveryrobot.ISocketInterface");
                return true;
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ISocketInterface {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.higgs.deliveryrobot.ISocketInterface";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.higgs.deliveryrobot.ISocketInterface
            public boolean moveCancel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.ISocketInterface");
                    boolean z = false;
                    this.mRemote.transact(1, obtain, obtain2, 0);
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

            @Override // com.higgs.deliveryrobot.ISocketInterface
            public boolean moveTarget(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.ISocketInterface");
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(2, obtain, obtain2, 0);
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

            @Override // com.higgs.deliveryrobot.ISocketInterface
            public boolean moveCruise(String str, float f, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.ISocketInterface");
                    obtain.writeString(str);
                    obtain.writeFloat(f);
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

            @Override // com.higgs.deliveryrobot.ISocketInterface
            public boolean getRobotStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.ISocketInterface");
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

            @Override // com.higgs.deliveryrobot.ISocketInterface
            public boolean getCurrentMap() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.ISocketInterface");
                    boolean z = false;
                    this.mRemote.transact(5, obtain, obtain2, 0);
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

            @Override // com.higgs.deliveryrobot.ISocketInterface
            public boolean eStop(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.ISocketInterface");
                    boolean z2 = true;
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.deliveryrobot.ISocketInterface
            public boolean getQueryList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.deliveryrobot.ISocketInterface");
                    boolean z = false;
                    this.mRemote.transact(7, obtain, obtain2, 0);
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
