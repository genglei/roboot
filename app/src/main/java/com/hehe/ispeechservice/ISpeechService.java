package com.hehe.ispeechservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISpeechService extends IInterface {
    boolean startOnlineSpeech() throws RemoteException;

    void startRecvTouchSignals() throws RemoteException;

    boolean startSpeech() throws RemoteException;

    void stopRecvTouchSignals() throws RemoteException;

    boolean stopSpeech() throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISpeechService {
        private static final String DESCRIPTOR = "com.higgs.ispeechservice.ISpeechService";
        static final int TRANSACTION_startOnlineSpeech = 3;
        static final int TRANSACTION_startRecvTouchSignals = 5;
        static final int TRANSACTION_startSpeech = 2;
        static final int TRANSACTION_stopRecvTouchSignals = 4;
        static final int TRANSACTION_stopSpeech = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.higgs.ispeechservice.ISpeechService");
        }

        public static ISpeechService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.higgs.ispeechservice.ISpeechService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISpeechService)) {
                return new Proxy(iBinder);
            }
            return (ISpeechService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.higgs.ispeechservice.ISpeechService");
                        boolean stopSpeech = stopSpeech();
                        parcel2.writeNoException();
                        parcel2.writeInt(stopSpeech ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.higgs.ispeechservice.ISpeechService");
                        boolean startSpeech = startSpeech();
                        parcel2.writeNoException();
                        parcel2.writeInt(startSpeech ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.higgs.ispeechservice.ISpeechService");
                        boolean startOnlineSpeech = startOnlineSpeech();
                        parcel2.writeNoException();
                        parcel2.writeInt(startOnlineSpeech ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.higgs.ispeechservice.ISpeechService");
                        stopRecvTouchSignals();
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface("com.higgs.ispeechservice.ISpeechService");
                        startRecvTouchSignals();
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.higgs.ispeechservice.ISpeechService");
                return true;
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ISpeechService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.higgs.ispeechservice.ISpeechService";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.higgs.ispeechservice.ISpeechService
            public boolean stopSpeech() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.ispeechservice.ISpeechService");
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

            @Override // com.higgs.ispeechservice.ISpeechService
            public boolean startSpeech() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.ispeechservice.ISpeechService");
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

            @Override // com.higgs.ispeechservice.ISpeechService
            public boolean startOnlineSpeech() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.ispeechservice.ISpeechService");
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

            @Override // com.higgs.ispeechservice.ISpeechService
            public void stopRecvTouchSignals() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.ispeechservice.ISpeechService");
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.ispeechservice.ISpeechService
            public void startRecvTouchSignals() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.ispeechservice.ISpeechService");
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
