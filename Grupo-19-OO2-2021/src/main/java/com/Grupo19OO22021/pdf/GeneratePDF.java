
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
		
		cell.setPhrase(new Phrase("Nombre", font));
		tabla.addCell(cell);
	
	}
	
	
	private void writeTableData(PdfPTable tabla) {
		for (Perfil perfil : listPerfil) {
			tabla.addCell(perfil.getNombrePerfil());
		
		}
		
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document,response.getOutputStream());
		
		document.open();
		
		document.add(new Paragraph("Lista de Perfiles"));
		
		PdfPTable tabla = new PdfPTable(1);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		
		
		writeTableHeader(tabla);
		writeTableData(tabla);
		
		document.add(tabla);
		document.close();
	}
	
}
