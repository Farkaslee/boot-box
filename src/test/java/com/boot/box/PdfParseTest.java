package com.boot.box;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.junit.Test;

import java.io.IOException;

/**
 * @author lifangliang
 * @version 1.0
 * @date 2021/6/11 10:14 上午
 * @description
 */
public class PdfParseTest {

    public static String getPdfFileText(String fileName) throws IOException {
        PdfReader reader = new PdfReader(fileName);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        StringBuffer buff = new StringBuffer();
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i,
                    new SimpleTextExtractionStrategy());
            buff.append(strategy.getResultantText());
        }
        return buff.toString();
    }

    @Test
    public void testParse() {
        try {
            System.out.print(getPdfFileText("E:\\test\\plugindoc.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
