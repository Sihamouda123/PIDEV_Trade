package com.PIdevv.PI.Service;


import com.PIdevv.PI.Entities.User;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class AccountPdfExporter {


    User acc ;
    public AccountPdfExporter(User acc) {
        this.acc = acc;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Rib", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("solde", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("typeAccount", font));
        table.addCell(cell);
        //cell.setPhrase(new Phrase("openDate", font));
        //table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {

        table.addCell(acc.getUsername());
        table.addCell(acc.getEmail());
        table.addCell("/////////");
	          /*
	            String pattern = "yyyy-MM-dd";
	            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	            table.addCell (simpleDateFormat.format(acc.getOpenDate())) ;
	            */

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("bank account identification ", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3f, 1.5f, 2f});
        table.setSpacingBefore(15);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }

}
