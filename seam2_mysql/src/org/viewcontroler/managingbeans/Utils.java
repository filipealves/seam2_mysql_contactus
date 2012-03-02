package org.viewcontroler.managingbeans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.faces.context.FacesContext;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.core.Manager;
import org.jboss.seam.document.ByteArrayDocumentData;
import org.jboss.seam.document.DocumentData;
import org.jboss.seam.document.DocumentStore;
import org.jboss.seam.excel.ExcelFactory;
import org.jboss.seam.excel.ExcelWorkbook;
import org.jboss.seam.navigation.Pages;
import org.jboss.seam.security.Role;
import org.jboss.seam.security.management.IdentityManager;

@Name("Utils")
public class Utils {

	public void exportall() {

		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			WritableWorkbook w = Workbook.createWorkbook(b);
			WritableSheet s = w.createSheet("Sheet", 0);
			int row = 0;

			s.addCell(new Label(0, row++, "Country Code"));
			s.addCell(new Label(0, row++, "Country Name: "));

			w.write();
			w.close();
			byte[] d = b.toByteArray();

			redirectUrl(d);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void redirectUrl(byte[] d) {
		String viewId = Pages.getViewId(FacesContext.getCurrentInstance());
		String baseName = Pages.getCurrentBaseName();
		ExcelWorkbook e = ExcelFactory.instance().getExcelWorkbook("");

		DocumentData documentData = new ByteArrayDocumentData(baseName,
				e.getDocumentType(), d);

		String id = DocumentStore.instance().newId();
		String url = DocumentStore.instance().preferredUrlForContent(baseName,
				e.getDocumentType().getExtension(), id);
		url = Manager.instance().encodeConversationId(url, viewId);
		DocumentStore.instance().saveData(id, documentData);

		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void printRoles() {
		System.out.println("Listagem de Roles :");
		for ( String r : IdentityManager.instance().listRoles()){
			System.out.println("Role : "+r);
		}
	}

}
