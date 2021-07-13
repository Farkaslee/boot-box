package com.boot.box.Fanuc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.boot.utils.TestFileUtil;
import com.spire.doc.*;
import com.spire.doc.documents.DocumentObjectType;
import com.spire.doc.documents.Paragraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lifangliang
 * @version 1.0
 * @date 2021/7/2 2:00 下午
 * @description
 */
public class FanucTestWord {

    @Test
    public void testWord() {
        String path = "D:\\ming\\数控\\FANUC 维修官方资料\\MODEL A\\WORD 版本\\Oi-A报警码.docx";
        spireParaghDoc(path);
        spireForTableOfDoc(path);
    }

    @Test
    public void testWordSP() {
        String path = "D:\\ming\\数控\\FANUC 维修官方资料\\MODEL B\\WORD 版本\\FANUC 0i-B串行主轴.docx";
        spireForTableFiveOfDoc(path);
    }

    //读取段落
    public static void spireParaghDoc(String path) {
        Document doc = new Document(path);
        for (int i = 0; i < doc.getSections().getCount(); i++) {
            Section p = doc.getSections().get(i);
            for (int j = 0; j < p.getParagraphs().getCount(); j++) {
                Paragraph paragraph = p.getParagraphs().get(j);
                System.out.println(paragraph.getText());
            }
        }
    }

    //读取表格
    public static void spireForTableOfDoc(String path) {
        Document doc = new Document(path);
        List<WordResultItem> result = new ArrayList<>();
        for (int i = 0; i < doc.getSections().getCount(); i++) {
            Section p = doc.getSections().get(i);
            for (int j = 0; j < p.getBody().getChildObjects().getCount(); j++) {
                DocumentObject obj = p.getBody().getChildObjects().get(j);
                if (obj.getDocumentObjectType() == DocumentObjectType.Table) {
                    Table table = (Table) obj;
                    for (int k = 0; k < table.getRows().getCount(); k++) {
                        TableRow rows = table.getRows().get(k);

                        int count = 1;
                        WordResultItem item = new WordResultItem();
                        for (int x = 0; x < rows.getCells().getCount(); x++) {
                            String rowText = "";
                            for (int h = 0; h < rows.getCells().get(x).getParagraphs().getCount(); h++) {
                                Paragraph f = rows.getCells().get(x).getParagraphs().get(h);
                                rowText += f.getText();
                            }
                            //rowText += "&&";
                            if (count == 1) {
                                item.setFaultNo(rowText);
                            }
                            if (count == 2) {
                                item.setFaultInfo(rowText);
                            }

                            if (count == 3) {
                                item.setFaultContent(rowText);
                            }
                            count++;
                        }
                        if("报警号".equals(item.getFaultNo()) ){
                            continue;
                        }
                        result.add(item);
                    }
                }
            }
        }

        System.out.println("----------- size : {} " + result.size());

        String fileName = TestFileUtil.getPath() + "write" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        System.out.println("---------- fileName :" + fileName);
        EasyExcel.write(fileName, WordResultItem.class).sheet("模板").doWrite(result);
    }

    //读取表格
    public static void spireForTableFiveOfDoc(String path) {
        Document doc = new Document(path);
        List<WordResultItemSP> result = new ArrayList<>();
        for (int i = 0; i < doc.getSections().getCount(); i++) {
            Section p = doc.getSections().get(i);
            for (int j = 0; j < p.getBody().getChildObjects().getCount(); j++) {
                DocumentObject obj = p.getBody().getChildObjects().get(j);
                if (obj.getDocumentObjectType() == DocumentObjectType.Table) {
                    Table table = (Table) obj;
                    for (int k = 0; k < table.getRows().getCount(); k++) {
                        TableRow rows = table.getRows().get(k);

                        int count = 1;
                        WordResultItemSP item = new WordResultItemSP();
                        for (int x = 0; x < rows.getCells().getCount(); x++) {
                            String rowText = "";
                            for (int h = 0; h < rows.getCells().get(x).getParagraphs().getCount(); h++) {
                                Paragraph f = rows.getCells().get(x).getParagraphs().get(h);
                                rowText += f.getText();
                            }
                            //rowText += "&&";
                            if (count == 1) {
                                item.setFaultNo(rowText);
                            }
                            if (count == 2) {
                                item.setFaultInfo(rowText);
                            }
                            if (count == 3) {
                                item.setDisplayInfo(rowText);
                            }

                            if (count == 4) {
                                item.setHandleMethod(rowText);
                            }

                            if (count == 5) {
                                item.setFaultContent(rowText);
                            }
                            count++;
                        }
                        if("报警号".equals(item.getFaultNo()) ){
                            continue;
                        }
                        result.add(item);
                        //System.out.println("----------- item : {} " + JSON.toJSONString(item));
                    }
                }
            }
        }

        System.out.println("----------- size : {} " + result.size());
        String fileName = TestFileUtil.getPath() + "write" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        System.out.println("---------- fileName :" + fileName);
        EasyExcel.write(fileName, WordResultItemSP.class).sheet("模板").doWrite(result);
    }
}



