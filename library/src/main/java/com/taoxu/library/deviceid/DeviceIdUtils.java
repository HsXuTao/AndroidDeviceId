package com.taoxu.library.deviceid;

/**
 * Created by tao.xu on 2017/6/19.
 */

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


public class DeviceIdUtils {
    private static final String TAG = "DeviceIdUtils";
    private static final String KEY_NAME = "android_device_id";//任意，但不能和系统设置冲突

    /**
     * 根据设备特征生成一个不变的设备id
     */
    public static String getDeviceId(Context context) {
        String uniqueId = Settings.System.getString(context.getContentResolver(), KEY_NAME);
        if (!TextUtils.isEmpty(uniqueId)) {
            Log.i(TAG, "读取到唯一设备ID：" + uniqueId);
            return uniqueId;
        }
        StringBuilder sb = new StringBuilder();

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = "";
        if (telephonyManager != null) {
            deviceId = telephonyManager.getDeviceId();
            if (!TextUtils.isEmpty(deviceId)) {
                sb.append(deviceId);
            }
        }
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (!TextUtils.isEmpty(androidId)) {
            sb.append(androidId);
        }

        if (TextUtils.isEmpty(androidId) && TextUtils.isEmpty(deviceId)) {
            String uuid = null;
            if (TextUtils.isEmpty(context.getSharedPreferences(TAG, Context.MODE_PRIVATE).getString(TAG, ""))) {
                uuid = UUID.randomUUID().toString();
                context.getSharedPreferences(TAG, Context.MODE_PRIVATE).edit().putString(TAG, uuid).commit();
            } else {
                uuid = context.getSharedPreferences(TAG, Context.MODE_PRIVATE).getString(TAG, "");
            }
            sb.append(uuid);//随机生成UUID
        }

        sb.append(android.os.Build.BOARD);//获取设备基板名称
        sb.append(android.os.Build.BOOTLOADER);//获取设备引导程序版本号
        sb.append(android.os.Build.BRAND);//获取设备品牌
        sb.append(android.os.Build.CPU_ABI);//获取设备指令集名称（CPU的类型）
        sb.append(android.os.Build.CPU_ABI2);//获取第二个指令集名称
        sb.append(android.os.Build.DEVICE);//获取设备驱动名称
        sb.append(android.os.Build.DISPLAY);//获取设备显示的版本包（在系统设置中显示为版本号）和ID一样
        sb.append(android.os.Build.FINGERPRINT);//设备的唯一标识。由设备的多个信息拼接合成。
        sb.append(android.os.Build.HARDWARE);//设备硬件名称,一般和基板名称一样（BOARD）
        sb.append(android.os.Build.ID);//设备版本号。
        sb.append(android.os.Build.MODEL);//获取手机的型号 设备名称。
        sb.append(android.os.Build.MANUFACTURER);//获取设备制造商
        sb.append(android.os.Build.PRODUCT);//整个产品的名称
        sb.append(android.os.Build.TAGS);//设备标签。如release-keys 或测试的 test-keys
        sb.append(android.os.Build.TYPE);//设备版本类型 主要为 "user" 或 "eng".
        sb.append(android.os.Build.USER);//设备用户名 基本上都为android -build
        sb.append(android.os.Build.VERSION.RELEASE);//获取系统版本字符串。如4.1.2 或2.2 或2.3等
        sb.append(android.os.Build.VERSION.CODENAME);//设备当前的系统开发代号，一般使用REL代替
        sb.append(android.os.Build.VERSION.INCREMENTAL);//系统源代码控制值，一个数字或者git hash值
        sb.append(android.os.Build.VERSION.SDK_INT);//系统的API级别 数字表示
        sb.append(android.os.Build.SERIAL);

        uniqueId = md5(sb.toString().trim().toUpperCase());
        Log.i(TAG, "生成唯一设备ID：" + uniqueId);
        Settings.System.putString(context.getContentResolver(), KEY_NAME, uniqueId);
        return uniqueId;
    }


    private static String md5(String str) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().toUpperCase();
    }
}
