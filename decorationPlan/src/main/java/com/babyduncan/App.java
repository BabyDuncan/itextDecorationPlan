package com.babyduncan;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        PdfPTable table = new PdfPTable(5);
        table.setTotalWidth(580);
        table.setLockedWidth(true);
        table.setTotalWidth(new float[]{135, 80, 80, 80, 200});
        table.setLockedWidth(true);
//        table = insertCommonPhrase(table, "施工项目", "单价(元)", "工程量", "总价(元)", "工艺说明");
        insertCommonPhrase(table, "1", "2", "3", "4", "5");
        document.add(table);
        document.close();
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        PDFFile pdffile = new PDFFile(byteBuffer);
        PDFPage page = pdffile.getPage(0);
        java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, (int) page.getBBox()
                .getWidth(), (int) page.getBBox().getHeight());
        java.awt.Image img = page.getImage(rect.width, rect.height, rect, null, true, true
        );
        BufferedImage tag = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height, null);
        FileOutputStream out = new FileOutputStream("/Users/zhaoguohao/Desktop/1.jpg"); // 输出到文件流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(tag);
        out.close();
    }

    public static void insertCommonPhrase(PdfPTable pdfPTable, String... contents) throws IOException, DocumentException {
        for (String content : contents) {
            Phrase phrase = new Phrase();
            BaseFont baseFontChinese = BaseFont.createFont("/Library/Fonts/Courier New.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Font fontChinese = new Font(baseFontChinese, 12, Font.NORMAL);
            phrase.setFont(fontChinese);
            Element element = new Chunk(content);
            phrase.add(0, element);
            pdfPTable.addCell(phrase);
        }

    }
}
