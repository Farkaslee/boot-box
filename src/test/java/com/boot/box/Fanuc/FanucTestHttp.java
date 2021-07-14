package com.boot.box.Fanuc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.boot.utils.TestFileUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.*;
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
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://skg.hzyskg.com/skg/index.php/Myapi/Alarm/Msg?id=143241&type=1&userid=8446")
                .method("GET", null)

                .build();
        try {
            Response response = client.newCall(request).execute();
            try {
                String resultContent = new String(response.body().bytes(), "utf-8");
                System.out.println("---------- content:" + new String(resultContent.getBytes(), "utf-8"));
                System.out.println("---------- size uncompress :" + new String(uncompresss(response.body().bytes()), "UTF-8"));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            //System.out.println("---------- size :" + getResponseString(response.body().bytes()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getResponseString(byte[] buf) {
        try {
            GZIPInputStream gzip = new GZIPInputStream(
                    new ByteArrayInputStream(buf));
            InputStreamReader isr = new InputStreamReader(gzip);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
                sb.append("\r\n");
            }
            isr.close();
            gzip.close();

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] uncompresss(byte[] bytes) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        GZIPInputStream gzip = new GZIPInputStream(in);

        byte[] buffer = new byte[1024];

        int n;

        while ((n = gzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);

        }

        return out.toByteArray();

    }

    @Test
    public void testUtil() throws UnsupportedEncodingException {
        String s = new String("\u53d1\u90a3\u79d1-FANUC".getBytes(), "utf-8");
        System.out.println(s);
    }

}

