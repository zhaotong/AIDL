// IMyAidlInterface.aidl
package com.tone.aidl;

import com.tone.aidl.ICallBack;
import com.tone.aidl.Entity;
interface IMyAidlInterface {
    void registerCallback(ICallBack cb);
    void unregisterCallback(ICallBack cb);
    void send2Service(inout Entity entity);

}
