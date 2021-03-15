package com.vivalnk.sdk.core;

/**
 * @author Jake
 * @date 2021.3.12 2:40
 */
public class WfdbUtils {

    static {
        System.loadLibrary("libs/x64/wfdb");
//        System.loadLibrary("MIT16_Java_Sample/libs/x86/wfdb");
//        System.load("C:\\Users\\Administrator\\Desktop\\Work\\monitor_zr wbb\\mit java\\MIT16_Java_Sample\\libs\\x64\\wfdb");
//        System.loadLibrary("libs/x86/wfdb");
//        Native.loadLibrary("MIT16_Java_Sample/libs/x64/wfdb",WfdbUtils.class);
    }

    public static native void initFile(String data, String hea);

    public static native void initSignalInfo(int frequency, int format, String desc, String units, int gain, int adcres, int adczero);

    public static native void open();

    public static native void setBaseTime(String time);

    public static native void doSample(int[] array);

    public static native void newHeader();

    public static native void close();

}
