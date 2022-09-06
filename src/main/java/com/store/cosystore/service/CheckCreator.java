package com.store.cosystore.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.store.cosystore.domain.Order;
import com.store.cosystore.domain.OrderPosition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class CheckCreator {

    @Value("${files.path}")
    private String filesPath;

    public File getFile(Order order) throws IOException, DocumentException {
        File check = new File("check.pdf");
        check.createNewFile();
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(check));

        document.open();
        BaseFont bf = BaseFont.createFont("templates/files/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf);
        document.add(new Paragraph("Заказ №" + order.getId(), font));
        document.add(new Paragraph(" "));

        PdfPTable t = new PdfPTable(4);
        for(OrderPosition op : order.getOrderPositions()) {
            t.addCell(op.getProductVersion().getArticle());
            //t.addCell(Image.getInstance("uploads/" + op.getProductVersion().getMainImg()));
            t.addCell(new Phrase(new Chunk(op.getProductVersion().getProduct().getName() + ", " + op.getProductVersion().getProduct().getGeneralInf(), font)));
            t.addCell(Integer.toString(op.getCount()));
            t.addCell(new Phrase(new Chunk(op.getProductVersion().getProduct().getPrice() + " руб", font)));
        }
        document.add(t);
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Итого: " + order.getCost() + " руб", font));

        document.close();
        return check;
    }
}
