package com.vivalnk.sdk;

import com.vivalnk.sdk.utils.DateFormat;

import java.util.Random;

import static com.vivalnk.sdk.core.WfdbUtils.*;

/**
 * @author Jake
 * @date 2021.3.10 18:25
 */
public class MIT16Test {

    public static void main(String[] args) {

        String testName = "mit16_test";

        System.out.println("test start!!!");

        /**
         * data  wfdb 数据文件名，调用者自己定义
         * hea   wfdb 摘要头文件名, 库会自动在hea入参后添加.hea后缀
         */
        initFile(testName + ".dat", testName);

        System.out.println("initDataFile!!!");

        /**
         * frequency 采样频率，针对VivaLN当前的心电参数，传入128即可，若有其他的，需要写入其他值
         * format 固定为16， 以为MIT16 格式
         * desc 数据文件描述
         * units 数据单位，固定为 "mV"
         * gain 数据增益： 固定为 1000
         * adcres ADC芯片采样分辨率，传0，默认
         * adczero ADC采样数据基线位置，
         * 参照 http://ecg.mit.edu/dbpg/dbu_73.htm#SEC93
         */
        initSignalInfo(
                128,
                16,
                "sample data",
                "mV",
                1000,
                0,
                0);

        System.out.println("initSignalInfo!!!");

        /**
         * 打开文件
         */
        open();

        System.out.println("openFile!!!");

        String time = DateFormat.format(System.currentTimeMillis(), "HH:mm:ss yyyy/MM/dd");
        /**
         * 设置采样起点数据时间
         */
        setBaseTime(time);

        System.out.println("setBaseTime!!!");

        Random random = new Random();
        int sample_Records = 2 * 60 * 128;
        int[] sample = new int[sample_Records];
        for (int i = 0; i < sample_Records; i++) {
            sample[i] = random.nextInt(1500);
        }

        /**
         * 写入采样数据点
         */
        doSample(sample);

        /**
         * 更新头文件信息
         */
        newHeader();

        System.out.println("doSample, newHeader!!!");

        /**
         * 关闭文件
         */
        close();

        System.out.println("close!!!");
    }

}
