package usuarios.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.Grupo19OO22021.entities.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("usuario/listUsuario") 
public class ListarUsuariosPdf extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Usuario> listadoUsuarios = (List<Usuario>) model.get("usuarios");
		
		
		PdfPTable tablaUsuarios = new PdfPTable(5);
		
		
		listadoUsuarios.forEach(usuario ->{
			tablaUsuarios.addCell(usuario.getNombreUsuario());
			tablaUsuarios.addCell(usuario.getNombre());
			tablaUsuarios.addCell(usuario.getApellido());
			tablaUsuarios.addCell(usuario.getEmail());
			tablaUsuarios.addCell(usuario .getPerfil().getNombrePerfil());
			
		});
		
		document.add(tablaUsuarios);
		
	}
	
	
	
	

}