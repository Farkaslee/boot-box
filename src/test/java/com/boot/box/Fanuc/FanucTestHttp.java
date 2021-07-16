package com.boot.box.Fanuc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.boot.utils.TestFileUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

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

    @Test
    public void testshukonggu() {
        //143241
        List<ShukongGuData> resultDataList = new ArrayList<>();
        String fileName = TestFileUtil.getPath() + "repeatedWrite" + LocalDate.now() + "01" + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, ShukongGuData.class).build();

        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//200000
        for(int i = 1;i<=200000;i++){
            if(i%2000 == 0){
                excelWriter.finish();
                fileName = TestFileUtil.getPath() + "repeatedWrite" + LocalDate.now() + i + ".xlsx";
                // 这里 需要指定写用哪个class去写
                excelWriter = EasyExcel.write(fileName, ShukongGuData.class).build();
            }
            SocketAddress sa = new InetSocketAddress("14.18.109.42", 8081);
            OkHttpClient client = new OkHttpClient().newBuilder().proxy(new Proxy(Proxy.Type.HTTP,sa))
                    .build();
            Request request = new Request.Builder()
                    .url("http://skg.hzyskg.com/skg/index.php/Myapi/Alarm/Msg?id="+i+"&type=1&userid=8446")
                    .method("GET", null)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                try {
                    String resultContent = new String(response.body().bytes(), "utf-8");
                    System.out.println("---------- content:" + resultContent);
                    ShukongResult shukongResult = JSON.parseObject(resultContent,ShukongResult.class);

                    //System.out.println("---------- content data:" + new String(shukongResult.getData().getBytes(), "utf-8"));
                    String resultData =new String(shukongResult.getData().getBytes(), "utf-8");
                    ShukongGuData shukongGuData= JSON.parseObject(resultData,ShukongGuData.class);
                    resultDataList.add(shukongGuData);
                    if(resultDataList.size() >= 5){
                        excelWriter.write(resultDataList, writeSheet);
                        resultDataList.clear();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(Long.valueOf((long) (Math.random()*5000)));
                System.out.println(Long.valueOf((long) (Math.random()*5000)) + "----------- : "+ resultDataList.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        excelWriter.finish();

    }

    @Test
    public void testUtil() throws UnsupportedEncodingException {
        String s = new String("\u53d1\u90a3\u79d1-FANUC".getBytes(), "utf-8");
        System.out.println(s);

        for(int i = 1;i< 10;i++){
            System.out.println(Long.valueOf((long) (Math.random()*3000)));

        }
    }

}

