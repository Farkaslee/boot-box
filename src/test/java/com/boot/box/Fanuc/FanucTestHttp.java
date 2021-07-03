package com.boot.box.Fanuc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.boot.utils.TestFileUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lifangliang
 * @version 1.0
 * @date 2020/10/22 12:12 下午
 * @description
 */
public class FanucTestHttp {

    @Test
    public void testHttp() {
        //产生顺序字符串
        int cycleTimes = 499;
        int numLength = 3;
        List<String> strings = Stream.iterate(1, item -> item + 1).limit(cycleTimes)
                .map(item -> String.valueOf(item))
                .map(item -> {
                    while (item.length() < numLength) item = "0" + item;
                    return item;
                }).collect(Collectors.toList());

        List<ResultItem> resultList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        for (String str : strings) {
            Request request = new Request.Builder()
                    .url("https://www.shanghai-fanuc.com.cn/api/web/gettroublecode?id=SRVO" + str)
                    .method("GET", null)
                    .addHeader("Cookie", "acw_tc=0bda72c816251502315531007e6a00aa4fad1d1f2dc4735379c9973929de3b; JSESSIONID=223721DA2037C2A55C457B6A4BA14FDF; Hm_lvt_846205b9a53bf2b75afb8a4189ec2088=1625150232; Hm_lpvt_846205b9a53bf2b75afb8a4189ec2088=1625150238")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                ShanghaiFanucResult shanghaiFanucResult = JSON.parseObject(response.body().string(), ShanghaiFanucResult.class);
                //System.out.println(JSON.toJSONString(shanghaiFanucResult));
                if (shanghaiFanucResult.getTemp().size() > 0) {
                    resultList.addAll(shanghaiFanucResult.getTemp());
                }

                System.out.println("---------- size :" + resultList.size() + " , str:" + str);

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        String fileName = TestFileUtil.getPath() + "write" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        System.out.println("---------- fileName :" + fileName);
        EasyExcel.write(fileName, ResultItem.class).sheet("模板").doWrite(resultList);
    }

    @Test
    public void testNumber() {
        int cycleTimes = 499;
        int numLength = 3;

        List<String> strings = Stream.iterate(1, item -> item + 1).limit(cycleTimes)
                .map(item -> String.valueOf(item))
                .map(item -> {
                    while (item.length() < numLength) item = "0" + item;
                    return item;
                }).collect(Collectors.toList());
    }
}

