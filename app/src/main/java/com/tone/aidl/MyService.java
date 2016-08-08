package com.tone.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

public class MyService extends Service {
    private RemoteCallbackList<ICallBack> mCallbacks = new RemoteCallbackList<>();

    private IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public void registerCallback(ICallBack cb) throws RemoteException {
            mCallbacks.register(cb);
        }

        @Override
        public void unregisterCallback(ICallBack cb) throws RemoteException {
            mCallbacks.unregister(cb);
        }

        @Override
        public void send2Service(Entity entity) throws RemoteException {
            String msg = "收到来自客户端是消息："+entity.getName();
            sendMsgToMain(msg);
        }
    };

    public MyService() {
    }

    private void sendMsgToMain(String msg) {
        int N = mCallbacks.beginBroadcast();
        if (N < 1)
            return;
        for (int i = 0; i < N; i++) {
            try {
                mCallbacks.getBroadcastItem(i).callBack(new Entity(msg,1));
            } catch (RemoteException e) {
            }
        }
        mCallbacks.finishBroadcast();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallbacks.kill();
    }
}
