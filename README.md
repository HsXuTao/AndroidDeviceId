# AndroidDeviceId

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
    compile 'com.github.HsXuTao:AndroidDeviceId:V1.0.0'
}
```

3：调用生成Id的方法

````java
String deviceId = DeviceIdUtils.getDeviceId(context)
````


版本更新：

</br>

V1.0.0

</br>

发布初版，能够在不卸载软件的情况下保证生成的ID唯一并且不变

