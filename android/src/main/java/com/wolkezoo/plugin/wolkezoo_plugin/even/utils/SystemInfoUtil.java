package com.wolkezoo.plugin.wolkezoo_plugin.even.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;

import com.wolkezoo.plugin.wolkezoo_plugin.utils.LogUtil;

import java.io.File;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;

/**
 * Application-related information
 * <p>
 * Obtaining cell phone storage information
 */
public class SystemInfoUtil {

    private static final int ERROR = -1;

    /**
     * Does SDCARD store
     */
    public static boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }


    /**
     * Getting the remaining internal storage space on your phone
     *
     * @return Remaining storage space
     */
    public static long obtainAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return blockSize * availableBlocks;
    }

    /**
     * Get the total internal storage space of your phone
     *
     * @return Total internal storage
     */
    public static long obtainTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        return totalBlocks * blockSize;
    }


    /**
     * Getting the remaining storage space on the SDCARD
     *
     * @return SDCARD remaining storage space
     */
    public static long obtainAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long availableBlocks = stat.getAvailableBlocksLong();
            return availableBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    /**
     * Getting the total SDCARD storage space
     *
     * @return Total SDCARD storage space
     */
    public static long obtainTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long totalBlocks = stat.getBlockCountLong();
            return totalBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    /**
     * Obtain device original name
     * if the bluetooth is not open, obtain the device name from DEVICE_NAME
     * if the android version is lower than 25.0, obtain the device name from Build.DEVICE
     *
     * @param context context
     * @return device name
     */
    public static String obtainDeviceName(Context context){
        String deviceName = "";

        try {
            deviceName = Settings.Secure.getString(context.getContentResolver(), "bluetooth_name");
        } catch (Exception e) {
            LogUtil.print("obtain device name error from bluetooth_name, error message " + ExceptionUtil.getSimpleMessage(e));
        }

        if (StrUtil.isBlank(deviceName)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                deviceName = Settings.Global.getString(context.getContentResolver(), Settings.Global.DEVICE_NAME);
            } else {
                deviceName = Build.DEVICE;
            }
        }
        return deviceName;
    }
}
