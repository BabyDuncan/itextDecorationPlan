package com.babyduncan;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.ImageIOUtil;

import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String... args) throws Exception {
        Document document = new Document();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/Users/zhaoguohao/github/itextDecorationPlan/ITextTest.pdf"));
        document.open();
        insertPhrase(document, "装修报价单");
        insertPhrase(document, "基本信息：北京，新房，125平，精装");
        PdfPTable table = new PdfPTable(5);
        table.setTotalWidth(580);
        table.setLockedWidth(true);
        table.setTotalWidth(new float[]{135, 80, 80, 80, 200});
        table.setLockedWidth(true);
        insertHeaderRow(table, "施工项目", "单价(元)", "工程量", "总价(元)", "工艺说明");
        insertTitleRow(table, "卧室");
        insertDataRow(table, "墙面地砖", "100", "9999", "33660098", "我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述");
        insertDataRow(table, "墙面地砖", "100", "9999", "33660098", "我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述");
        insertDataRow(table, "墙面地砖", "100", "9999", "33660098", "我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述");
        insertDataRow(table, "墙面地砖", "100", "9999", "33660098", "我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述");
        insertSmallTotalRow(table, "100");
        insertTitleRow(table, "厨房");
        insertDataRow(table, "墙面地砖", "100", "9999", "33660098", "我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述");
        insertDataRow(table, "墙面地砖", "100", "9999", "33660098", "我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述");
        insertDataRow(table, "墙面地砖", "100", "9999", "33660098", "我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述");
        insertDataRow(table, "墙面地砖", "100", "9999", "33660098", "我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述我是很多描述");
        insertSmallTotalRow(table, "100");
        insertTotalRow(table, "999");
        byte[] bytes = image2byte("/Users/zhaoguohao/Downloads/bp_ee323ca1598144078f23f986b87a21df.jpeg");
        Image img = Image.getInstance(bytes);
        img.setAlignment(Image.ALIGN_CENTER | Image.TEXTWRAP);
        img.scalePercent(30);
        document.add(table);
        document.add(img);
        document.close();
        pdfToJpg("/Users/zhaoguohao/github/itextDecorationPlan/ITextTest.pdf");
    }

    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    public static void insertPhrase(Document document, String content) throws IOException, DocumentException {
        Phrase phrase = new Phrase();
        BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(baseFontChinese, 12, Font.NORMAL);
        phrase.setFont(fontChinese);
        Element element = new Chunk(content, fontChinese);
        phrase.add(element);
        document.add(phrase);
        document.add(Chunk.NEWLINE);
    }

    public static void insertHeaderRow(PdfPTable pdfPTable, String... contents) throws IOException, DocumentException {
        for (String content : contents) {
            PdfPCell pdfPCell = new PdfPCell();
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPCell.setPadding(10);
            Phrase phrase = new Phrase();
            BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font fontChinese = new Font(baseFontChinese, 12, Font.NORMAL);
            phrase.setFont(fontChinese);
            Element element = new Chunk(content, fontChinese);
            phrase.add(0, element);
            pdfPCell.setPhrase(phrase);
            pdfPTable.addCell(pdfPCell);
        }
    }

    public static void insertTitleRow(PdfPTable pdfPTable, String title) throws IOException, DocumentException {
        BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(baseFontChinese, 14, Font.NORMAL);
        PdfPCell pdfPCell = new PdfPCell(new Phrase("卧室", fontChinese));
        pdfPCell.setColspan(5);
        pdfPCell.setBackgroundColor(new BaseColor(237, 237, 237));
        pdfPCell.setPadding(10);
        pdfPCell.setPaddingLeft(50);
        pdfPTable.addCell(pdfPCell);
    }

    public static void insertDataRow(PdfPTable pdfPTable, String... data) throws IOException, DocumentException {
        for (int i = 0; i < data.length; i++) {
            PdfPCell pdfPCell = new PdfPCell();
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfPCell.setPadding(10);
            Phrase phrase = new Phrase();
            BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font fontChinese = new Font(baseFontChinese, 10, Font.NORMAL);
            if (i == 0 || i == 4) {
                Element element = new Chunk(data[i], fontChinese);
                phrase.add(0, element);
            } else {
                Element element = new Chunk(data[i]);
                phrase.add(0, element);
            }
            pdfPCell.setPhrase(phrase);
            pdfPTable.addCell(pdfPCell);
        }
    }

    public static void insertSmallTotalRow(PdfPTable pdfPTable, String smallTotal) throws IOException, DocumentException {
        BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(baseFontChinese, 12, Font.NORMAL);
        Phrase phrase = new Phrase();
        Element element = new Chunk("小计:", fontChinese);
        phrase.add(element);
        Element element2 = new Chunk(smallTotal);
        phrase.add(element2);
        PdfPCell pdfPCell = new PdfPCell(phrase);
        pdfPCell.setColspan(5);
        pdfPCell.setPadding(10);
        pdfPCell.setPaddingLeft(300);
        pdfPTable.addCell(pdfPCell);
    }

    public static void insertTotalRow(PdfPTable pdfPTable, String total) throws IOException, DocumentException {
        BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(baseFontChinese, 14, Font.NORMAL);
        Phrase phrase = new Phrase();
        Element element = new Chunk("总计:", fontChinese);
        phrase.add(element);
        Element element2 = new Chunk(total);
        phrase.add(element2);
        PdfPCell pdfPCell = new PdfPCell(phrase);
        pdfPCell.setColspan(5);
        pdfPCell.setBackgroundColor(new BaseColor(237, 237, 237));
        pdfPCell.setPadding(10);
        pdfPCell.setPaddingLeft(300);
        pdfPTable.addCell(pdfPCell);
    }

    private static void pdfToJpg(String pdfFilename) throws Exception {
        PDDocument document = PDDocument.loadNonSeq(new File(pdfFilename), null);
        java.util.List<PDPage> pdPages = document.getDocumentCatalog().getAllPages();
        int page = 0;
        for (PDPage pdPage : pdPages) {
            ++page;
            BufferedImage bim = pdPage.convertToImage(BufferedImage.TYPE_INT_RGB, 300);
            ImageIOUtil.writeImage(bim, pdfFilename + "-" + page + ".png", 300);
        }
        document.close();
    }


}
