package com.wolkezoo.plugin.wolkezoo_plugin.even.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

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

}
