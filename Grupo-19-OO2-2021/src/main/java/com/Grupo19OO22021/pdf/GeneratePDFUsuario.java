package com.Grupo19OO22021.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.Grupo19OO22021.entities.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class GeneratePDFUsuario {
private List<Usuario> listUsuario;
	
	
	public GeneratePDFUsuario(List<Usuario> listUsuario) {
		this.listUsuario=listUsuario;
	}
	
	private void writeTableHeader(PdfPTable tabla) {
		PdfPCell cell = new PdfPCell();
		
		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
		
		cell.setBackgroundColor(new Color(157,214,215));
	
		cell.setPhrase(new Phrase("Nombre de usuario", font));
		tabla.addCell(cell);
		cell.setPhrase(new Phrase("Nombre", font));
		tabla.addCell(cell);
		cell.setPhrase(new Phrase("Apellido", font));
		tabla.addCell(cell);
		cell.setPhrase(new Phrase("Número de documento", font));
		tabla.addCell(cell);
		cell.setPhrase(new Phrase("Email", font));
		tabla.addCell(cell);
		cell.setPhrase(new Phrase("Perfil", font));
		tabla.addCell(cell);
		
	
		
	}
	
	
	private void writeTableData(PdfPTable tabla) {
		for (Usuario usuario : listUsuario) {
			
		
			tabla.addCell(String.valueOf(usuario.getIdUsuario()));
			tabla.addCell(usuario.getNombre());
			tabla.addCell(usuario.getApellido());
			tabla.addCell(String.valueOf(usuario.getNroDocumento()));
			tabla.addCell(usuario.getEmail());
			tabla.addCell(usuario.getPerfil().getNombrePerfil());
			
		}
		
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4.rotate());
	
		
		PdfWriter.getInstance(document,response.getOutputStream());
		
		document.open();
		
		document.add(new Paragraph("Lista de Usuarios"));
		
		PdfPTable tabla = new PdfPTable(6);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		
		
		writeTableHeader(tabla);
		writeTableData(tabla);
		
		document.add(tabla);
		document.close();
	}
	
}
	