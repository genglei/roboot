package com.hehe.report;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ReportHander extends IInterface {
    int CommandResult(String str, String str2, int i, String str3, String str4) throws RemoteException;

    int bind(String str, ReportCallback reportCallback) throws RemoteException;

    boolean bindExist(String str) throws RemoteException;

    int report(String str, String str2) throws RemoteException;

    boolean unbind(String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ReportHander {
        private static final String DESCRIPTOR = "com.higgs.droidsys.report.ReportHander";
        static final int TRANSACTION_CommandResult = 5;
        static final int TRANSACTION_bind = 1;
        static final int TRANSACTION_bindExist = 2;
        static final int TRANSACTION_report = 4;
        static final int TRANSACTION_unbind = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.higgs.droidsys.report.ReportHander");
        }

        public static ReportHander asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.higgs.droidsys.report.ReportHander");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ReportHander)) {
                return new Proxy(iBinder);
            }
            return (ReportHander) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.higgs.droidsys.report.ReportHander");
                        int bind = bind(parcel.readString(), ReportCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(bind);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.higgs.droidsys.report.ReportHander");
                        boolean bindExist = bindExist(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(bindExist ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.higgs.droidsys.report.ReportHander");
                        boolean unbind = unbind(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(unbind ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.higgs.droidsys.report.ReportHander");
                        int report = report(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(report);
                        return true;
                    case 5:
                        parcel.enforceInterface("com.higgs.droidsys.report.ReportHander");
                        int CommandResult = CommandResult(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(CommandResult);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.higgs.droidsys.report.ReportHander");
                return true;
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ReportHander {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.higgs.droidsys.report.ReportHander";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.higgs.droidsys.report.ReportHander
            public int bind(String str, ReportCallback reportCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.droidsys.report.ReportHander");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(reportCallback != null ? reportCallback.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.droidsys.report.ReportHander
            public boolean bindExist(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.droidsys.report.ReportHander");
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

            @Override // com.higgs.droidsys.report.ReportHander
            public boolean unbind(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.droidsys.report.ReportHander");
                    obtain.writeString(str);
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

            @Override // com.higgs.droidsys.report.ReportHander
            public int report(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.droidsys.report.ReportHander");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.droidsys.report.ReportHander
            public int CommandResult(String str, String str2, int i, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.droidsys.report.ReportHander");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.mRemote.transact(5, obtain, obtain2, 0);
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
