package com.PIdevv.PI.Service;


import com.PIdevv.PI.Entities.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;
@Service
public class PDFGeneratorService {


    private static Logger logger = LoggerFactory.getLogger(PDFGeneratorService.class);

    public static ByteArrayInputStream employeePDFReport
            (List<User> users) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Text to PDF file ->
            Font font = FontFactory.getFont(FontFactory.COURIER, 14,
                    BaseColor.BLACK);
            Paragraph para = new Paragraph("Users Table", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(8);
            // Add PDF Table Header ->
            Stream.of("Id", "FirtName","LastName","Username", "email", "Phone","Adress","BirthDate").forEach(headerTitle ->
            {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });

            for (User p : users) {
                PdfPCell idCell = new PdfPCell(new Phrase((String.valueOf(p.getIdUser()))));
                idCell.setPaddingLeft(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell FirstName = new PdfPCell(new Phrase
                        (p.getFirstName()));
                FirstName.setPaddingLeft(4);
                FirstName.setVerticalAlignment(Element.ALIGN_MIDDLE);
                FirstName.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(FirstName);

                PdfPCell LastName = new PdfPCell(new Phrase
                        (p.getLastName()));
                LastName.setPaddingLeft(4);
                LastName.setVerticalAlignment(Element.ALIGN_MIDDLE);
                LastName.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(LastName);

                PdfPCell Name = new PdfPCell(new Phrase
                        (p.getUsername()));
                Name.setPaddingLeft(4);
                Name.setVerticalAlignment(Element.ALIGN_MIDDLE);
                Name.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(Name);

                PdfPCell Email = new PdfPCell(new Phrase
                        (String.valueOf(p.getEmail())));
                Email.setVerticalAlignment(Element.ALIGN_MIDDLE);
                Email.setHorizontalAlignment(Element.ALIGN_RIGHT);
                Email.setPaddingRight(4);
                table.addCell(Email);

                PdfPCell Phone = new PdfPCell(new Phrase
                        (String.valueOf(p.getPhoneNumber())));
                Phone.setVerticalAlignment(Element.ALIGN_MIDDLE);
                Phone.setHorizontalAlignment(Element.ALIGN_RIGHT);
                Phone.setPaddingRight(4);
                table.addCell(Phone);

                PdfPCell Adress = new PdfPCell(new Phrase
                        (String.valueOf(p.getAdress())));
                Adress.setVerticalAlignment(Element.ALIGN_MIDDLE);
                Adress.setHorizontalAlignment(Element.ALIGN_RIGHT);
                Adress.setPaddingRight(4);
                table.addCell(Adress);

                PdfPCell Birth = new PdfPCell(new Phrase
                        (String.valueOf(p.getBirthDate())));
                Birth.setVerticalAlignment(Element.ALIGN_MIDDLE);
                Birth.setHorizontalAlignment(Element.ALIGN_RIGHT);
                Birth.setPaddingRight(4);
                table.addCell(Birth);

            }
            document.add(table);

            document.close();
        } catch (DocumentException e) {
            logger.error(e.toString());
        }


        return new ByteArrayInputStream(out.toByteArray());


    }
}