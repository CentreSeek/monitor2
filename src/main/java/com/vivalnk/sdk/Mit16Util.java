package com.vivalnk.sdk;

import com.vivalnk.sdk.utils.DateFormat;

import static com.vivalnk.sdk.core.WfdbUtils.close;
import static com.vivalnk.sdk.core.WfdbUtils.doSample;
import static com.vivalnk.sdk.core.WfdbUtils.initFile;
import static com.vivalnk.sdk.core.WfdbUtils.initSignalInfo;
import static com.vivalnk.sdk.core.WfdbUtils.newHeader;
import static com.vivalnk.sdk.core.WfdbUtils.open;
import static com.vivalnk.sdk.core.WfdbUtils.setBaseTime;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2021-03-12 17:48:47
 **/
public class Mit16Util {
    public static String writeMit16File(String caseNum, Long timestamp, int[] data) {
        String fileTimestamp = DateFormat.format(timestamp, "yyMMdd");
        String fileName = caseNum + "-" + fileTimestamp;
//        String fileName = "t";
        /**
         * data  wfdb 数据文件名，调用者自己定义
         * ps: 用户ID（住院号）-设备ID-日期-时间；比如：
         * 010001-ECGRec_202041_C740431-20210304_124944_-0700.txt
         *
         * hea   wfdb 摘要头文件名, 库会自动在hea入参后添加.hea后缀
         */
        initFile(fileName + ".dat", fileName);
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
        /**
         * 打开文件
         */
        open();
        String time = DateFormat.format(timestamp, "HH:mm:ss yyyy/MM/dd");
        /**
         * 设置采样起点数据时间
         */
        setBaseTime(time);
        /**
         * 写入采样数据点
         */
        doSample(data);
        /**
         * 更新头文件信息
         */
        newHeader();
        /**
         * 关闭文件
         */
        close();
//        try {
//            return ZipUtils.doCompressEcg(fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return fileName;
    }
}
