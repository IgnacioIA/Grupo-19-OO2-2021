
package com.Grupo19OO22021.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import com.Grupo19OO22021.entities.Perfil;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class GeneratePDF {
	
	private List<Perfil> listPerfil;
	
	
	public GeneratePDF(List<Perfil> listPerfil) {
		this.listPerfil=listPerfil;
	}
	
	private void writeTableHeader(PdfPTable tabla) {
		PdfPCell cell = new PdfPCell();
		
		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.BLUE);
		
		
		cell.setPhrase(new Phrase("ID", font));
		tabla.addCell(cell);
		cell.setPhrase(new Phrase("Nombre", font));
		tabla.addCell(cell);
		
		//cell.setPhrase(new Phrase);
		//tabla.addCell(cell);
		//Datos del perfil
		
		
	}
	
	
	private void writeTableData(PdfPTable tabla) {
		for (Perfil perfil : listPerfil) {
			tabla.addCell(String.valueOf(perfil.getIdPerfil()));
			tabla.addCell(perfil.getNombrePerfil());
			//tabla.addCell(String.valueOf(perfil.getIdPerfil()));
		
		}
		
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document,response.getOutputStream());
		
		document.open();
		
		document.add(new Paragraph("Lista de Perfiles"));
		
		PdfPTable tabla = new PdfPTable(2);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		
		
		writeTableHeader(tabla);
		writeTableData(tabla);
		
		document.add(tabla);
		document.close();
	}
	
	
	/*
	private PDDocument documento;
	
	public GeneratePDF(PDDocument documento) {
		super();
		this.documento = documento;
	}
	
	
	public PDDocument getDocumento() {
		return documento;
	}
	
	public void setDocumento(PDDocument documento) {
		this.documento = documento;
	}
	
	
	public static PDDocument generatePDCListPerfil(List<Perfil> listPerfil) throws Exception{
	
	try (PDDocument document = new PDDocument()) {
        PDPage page = new PDPage(PDRectangle.A6);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ITALIC, 8);
        contentStream.newLineAtOffset( 10, page.getMediaBox().getHeight() - 52);
    	contentStream.showText("Perfiles");
    	contentStream.newLineAtOffset(0, -15);
    	contentStream.showText("ID                Nombre del Perfil");
    	contentStream.newLineAtOffset(0, -15);
    
    
        for (int i = 0; i < listPerfil.size(); i++) {
        
        	contentStream.showText(imprimirPerfil(listPerfil.get(i)));
        	contentStream.newLineAtOffset(0, -15);
        }
        contentStream.endText();
        // Image
//        PDImageXObject image = PDImageXObject.createFromByteArray(document, Main.class.getResourceAsStream("/java.png").readAllBytes(), "Java Logo");
  //      contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);

        contentStream.close();

        document.save("perfiles.pdf");
        return document;
    }
	}
	
	
	public static String imprimirPerfil(Perfil perfil) {
		return perfil.getIdPerfil() +"                      " + perfil.getNombrePerfil()+ "                                                                                                                 ";
	}
	
	
	*/
}
