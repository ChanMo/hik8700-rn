# HIK-RN

使用ReactNative, 基于海康IVMS8700

## Setup

### 复制文件

把`ivms_8700_sdk_library.jar` 复制到`android/app/libs/`目录

把`libAudioEngine.so`, `libMCRSDK.so`, `libPlayCtrl.so`复制到`android/app/src/main/jniLibs/armeabi-v7a/`目录


### 修改`android/app/build.gradle`

```
android {
    buildTypes {
        ...
        ndk {
            abiFilters "armeabi-v7a"
        }
    }
    release {
        ...
        ndk {
            abiFilters "armeabi-v7a"
        }
    }
}
```

### 修改`android/app/src/main/AndroidManifest.xml`

```
    ...
    <uses-library android:name="org.apache.http.legacy" android:required="false"/>
    <activity
      android:name=".MainActivity"
      ...

```

### 配置使用`Ivms8700Package`

复制`com.mdian`目录到`android/app/src/main/java/`

修改`MainApplication.java`

```
...
import com.hik.mcrsdk.MCRSDK;
import com.hik.mcrsdk.rtsp.RtspClient;
import com.hik.mcrsdk.talk.TalkClientSDK;
import com.hikvision.sdk.VMSNetSDK;
import com.hikvision.sdk.utils.CNetSDKLog;
import com.hikvision.sdk.utils.FileUtils;

import com.mdian.Ivms8700Package;
import com.mdian.ReactPlayerPackage;
...


        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          packages.add(new Ivms8700Package());
          packages.add(new ReactPlayerPackage());
          return packages;
        }


  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);

    MCRSDK.init();
    RtspClient.initLib();
    MCRSDK.setPrint(1, null);
    TalkClientSDK.initLib();
    VMSNetSDK.init(this);

    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
  }

```

### 在Js中使用

```
import {NativeModules} from 'react-native';
const { Ivms8700Module } = NativeModules;

...

Ivms8700Module.login()
```
