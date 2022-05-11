package com.hehe.report;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ReportCallback extends IInterface {
    void CommandReceive(String str, String str2, String str3, String str4) throws RemoteException;

    void reportComplete(String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ReportCallback {
        private static final String DESCRIPTOR = "com.higgs.droidsys.report.ReportCallback";
        static final int TRANSACTION_CommandReceive = 2;
        static final int TRANSACTION_reportComplete = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.higgs.droidsys.report.ReportCallback");
        }

        public static ReportCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.higgs.droidsys.report.ReportCallback");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ReportCallback)) {
                return new Proxy(iBinder);
            }
            return (ReportCallback) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.higgs.droidsys.report.ReportCallback");
                        reportComplete(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("com.higgs.droidsys.report.ReportCallback");
                        CommandReceive(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.higgs.droidsys.report.ReportCallback");
                return true;
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements ReportCallback {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "com.higgs.droidsys.report.ReportCallback";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.higgs.droidsys.report.ReportCallback
            public void reportComplete(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.droidsys.report.ReportCallback");
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.higgs.droidsys.report.ReportCallback
            public void CommandReceive(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.higgs.droidsys.report.ReportCallback");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
