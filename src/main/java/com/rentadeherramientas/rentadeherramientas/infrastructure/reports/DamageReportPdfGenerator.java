package com.rentadeherramientas.rentadeherramientas.infrastructure.reports;

import java.io.FileOutputStream;
import java.util.List;

import javax.lang.model.element.Element;

import org.springframework.stereotype.Component;


import com.zaxxer.hikari.util.ClockSource.Factory;

@Component
public class DamageReportPdfGenerator<Paragraph, Document> {

    public void generateReport(List<String> reportLines, String filePath, Object PdfWriter) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font titleFont = Factory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Reporte de Daños de Herramientas", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            for (String line : reportLines) {
                document.add(new Paragraph(line));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}

