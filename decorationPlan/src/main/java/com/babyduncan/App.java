package com.babyduncan;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        Document document = new Document();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/Users/zhaoguohao/github/itextDecorationPlan/ITextTest.pdf"));
        document.open();
        PdfPTable table = new PdfPTable(5);
        table.setTotalWidth(580);
        table.setLockedWidth(true);
        table.setTotalWidth(new float[]{135, 80, 80, 80, 200});
        table.setLockedWidth(true);
        insertCommonPhrase(table, "施工项目", "单价(元)", "工程量", "总价(元)", "工艺说明");
        document.add(table);
        document.close();


    }

    public static void insertCommonPhrase(PdfPTable pdfPTable, String... contents) throws IOException, DocumentException {
        for (String content : contents) {
            Phrase phrase = new Phrase();
            BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font fontChinese = new Font(baseFontChinese, 12, Font.NORMAL);
            phrase.setFont(fontChinese);
            Element element = new Chunk(content, fontChinese);
            phrase.add(0, element);
            pdfPTable.addCell(phrase);
        }

    }
}
