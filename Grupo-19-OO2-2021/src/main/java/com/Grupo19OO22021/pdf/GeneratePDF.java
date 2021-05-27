package com.Grupo19OO22021.pdf;

import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.Grupo19OO22021.entities.Perfil;

public class GeneratePDF {
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
}
