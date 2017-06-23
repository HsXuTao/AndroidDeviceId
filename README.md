# AndroidDeviceId
[![](https://jitpack.io/v/HsXuTao/AndroidDeviceId.svg)](https://jitpack.io/#HsXuTao/AndroidDeviceId)

获取安卓设备的唯一识别码，方便标示唯一设备

</br>

如何使用：

</br>


1：在你根目录里的build.gradle添加如下代码
```java
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```


2：在你的工程里，加入
```java
dependencies {
    compile 'com.github.HsXuTao:AndroidDeviceId:V1.0.1'
}
```

3：调用生成Id的方法

````java
String deviceId = DeviceIdUtils.getDeviceId(context)
````


版本更新：
</br>

V1.0.1

</br>

针对Andorid6.0以上的机型，进行额外的适配修改
</br>
注：Android6.0以上的机型，软件被卸载后，通过生成的机器识别码可能会改变
</br>

V1.0.0

</br>

发布初版，能够在不卸载软件的情况下保证生成的ID唯一并且不变，卸载后有可能会改变（仅仅在某些特殊机器里使用UUID生成的ID）

