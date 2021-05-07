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
