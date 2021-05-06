package com.mdian;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiInfo;

import com.hikvision.sdk.VMSNetSDK;
import com.hikvision.sdk.net.business.OnVMSNetSDKBusiness;
import com.hikvision.sdk.consts.SDKConstant.LiveSDKConstant;
import com.hikvision.sdk.consts.SDKConstant.PTZCommandConstant;

import java.util.Map;
import java.util.HashMap;
import android.util.Log;
import android.os.Looper;

import android.view.SurfaceView;
import com.mdian.ReactPlayerManager;


public class Ivms8700Module extends ReactContextBaseJavaModule {
    private int window = 1;
    private SurfaceView surfaceView = null;
    Ivms8700Module(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "Ivms8700Module";
    }

    @ReactMethod
    public void login() {
        Log.d("Ivms8700Module", "Login test");
        String url = "";
        String userName = "";
        String password = "";
        String macAddress = "";
        VMSNetSDK.getInstance().Login(url, userName, password, macAddress, new OnVMSNetSDKBusiness() {
            @Override
            public void onFailure() {
                Log.i("Ivms8700Module", "HIK Login failure");
            };

            @Override
            public void onSuccess(Object obj) {
                Log.i("Ivms8700Module", "HIK Login success");
                Log.i("Ivms8700Module", obj.toString());
            };

        });
    }

    @ReactMethod
    public void startLive(String sysCode, int id) {
        int streamType = LiveSDKConstant.MAIN_HIGH_STREAM;

        surfaceView = getCurrentActivity().findViewById(id);

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                VMSNetSDK.getInstance().startLiveOpt(
                        window, 
                        sysCode, 
                        surfaceView,
                        streamType, 
                        new OnVMSNetSDKBusiness() {
                    @Override
                    public void onFailure() {
                        Log.i("Ivms8700Module", "Live Failureeeeeeeeeeeeeeeeeeeeeeee");
                    };

                    @Override
                    public void onSuccess(Object obj) {
                        Log.i("Ivms8700Module", "successssssssssssssssssssssssssss");
                    };
                });
                Looper.loop();
            }
        }.start();
    }


    @ReactMethod
    public void stopCommand() {
        VMSNetSDK.getInstance().sendPTZCtrlCommand(
                window, 
                true,
                PTZCommandConstant.ACTION_STOP,
                24,
                0,
                new OnVMSNetSDKBusiness() {
                    @Override
                    public void onFailure() {
                        Log.i("Ivms8700Module", "Control Failureeeeeeeeeeeeeeeeeeeeeeee");
                    };

                    @Override
                    public void onSuccess(Object obj) {
                        Log.i("Ivms8700Module", "successssssssssssssssssssssssssss");
                    };
                }
        );
    }

    @ReactMethod
    public void startCommand(int command) {
        VMSNetSDK.getInstance().sendPTZCtrlCommand(
                window, 
                true,
                PTZCommandConstant.ACTION_START,
                command,
                50,
                new OnVMSNetSDKBusiness() {
                    @Override
                    public void onFailure() {
                        Log.i("Ivms8700Module", "Control Failureeeeeeeeeeeeeeeeeeeeeeee");
                    };

                    @Override
                    public void onSuccess(Object obj) {
                        Log.i("Ivms8700Module", "successssssssssssssssssssssssssss");
                    };
                }
        );
    }

    // public String getMacAddress() {
    //     WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    //     WifiInfo connectionInfo = wm.getConnectionInfo();
    //     String mac = connectionInfo.getMacAddress();
    //     return mac == null ? "02:00:00:00:00:00" : mac;
    // }
}
