package org.domain.seam2mysql.session;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.domain.seam2mysql.entity.Country;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.core.Manager;
import org.jboss.seam.document.ByteArrayDocumentData;
import org.jboss.seam.document.DocumentData;
import org.jboss.seam.document.DocumentStore;
import org.jboss.seam.excel.ExcelFactory;
import org.jboss.seam.excel.ExcelWorkbook;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.navigation.Pages;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

//@Scope(ScopeType.SESSION)
@Name("countryList")
public class CountryList extends EntityQuery<Country> {

	private static final String EJBQL = "select country from Country country";
	private Selection selection = new SimpleSelection();

	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	private static final String[] RESTRICTIONS = {
			"lower(country.code) like lower(concat(#{countryList.country.code},'%'))",
			"lower(country.name) like lower(concat(#{countryList.country.name},'%'))",
			"lower(country.continent) like lower(concat(#{countryList.country.continent},'%'))",
			"lower(country.region) like lower(concat(#{countryList.country.region},'%'))",
			"lower(country.localName) like lower(concat(#{countryList.country.localName},'%'))",
			"lower(country.governmentForm) like lower(concat(#{countryList.country.governmentForm},'%'))",
			"lower(country.headOfState) like lower(concat(#{countryList.country.headOfState},'%'))",
			"lower(country.code2) like lower(concat(#{countryList.country.code2},'%'))", };

	private Country country = new Country();

	public CountryList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public void save() {
		this.getEntityManager().flush();
	}

	public Country getCountry() {
		return country;
	}

	public void listResultList() {

		for (Country c : super.getResultList()) {
			System.out.println(c.getName());
		}

	}

	public void listAll() {

		int n = this.getResultCount().intValue();
		setMaxResults(n);
		for (Country c : super.getResultList()) {
			System.out.println(c.getName());
		}
		setMaxResults(25);
	}

	public void listselected() {

		for (Country c : super.getResultList()) {
			if (c.isSelected()) {
				System.out.println(c.getName());
			}

		}

	}

	public void exportall() {
		
		int n = this.getResultCount().intValue();
		System.out.println("A preparar para exportar tudo " + n);
		setMaxResults(n);
		List<Country> rs = getResultList();
		first();
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			WritableWorkbook w = Workbook.createWorkbook(b);
			WritableSheet s = w.createSheet("Sheet", 0);
			int row = 0;
			s.addCell(new Label(0, row, "Country Code"));
			s.addCell(new Label(1, row, "Country Name"));
			s.addCell(new Label(2, row, "Country Continent"));
			s.addCell(new Label(3, row, "Country Region"));
			s.addCell(new Label(4, row, "Country Surface"));
			s.addCell(new Label(5, row, "Country Indepen. Year"));
			s.addCell(new Label(6, row, "Country Population"));
			s.addCell(new Label(7, row, "Country Life Expectency"));
			s.addCell(new Label(8, row, "Country Gnp"));
			s.addCell(new Label(9, row, "Country Local Name"));
			s.addCell(new Label(10, row, "Country Government"));
			s.addCell(new Label(11, row, "Country Head of State"));
			s.addCell(new Label(12, row, "Country Capital"));
			s.addCell(new Label(13, row, "Country Code 2"));
			row++;
			
			for (Country lrd : rs) {
			
				s.addCell(new Label(0, row, lrd.getCode()));
				s.addCell(new Label(1, row, lrd.getName()));
				s.addCell(new Label(2, row, lrd.getContinent()));
				s.addCell(new Label(3, row, lrd.getRegion()));
				s.addCell(new Label(4, row, String.valueOf(lrd.getSurfaceArea())));
				s.addCell(new Label(5, row, lrd.getIndepYear() != null ? lrd.getIndepYear().toString() : "N/A" ));
				s.addCell(new Label(6, row, String.valueOf(lrd.getPopulation())));
				s.addCell(new Label(7, row, lrd.getLifeExpectancy() != null ? String.valueOf(lrd.getLifeExpectancy()) : "N/A"));
				s.addCell(new Label(8, row, String.valueOf(lrd.getGnp())));
				s.addCell(new Label(9, row, lrd.getLocalName()));
				s.addCell(new Label(10, row, lrd.getGovernmentForm()));
				s.addCell(new Label(11, row, lrd.getHeadOfState()));
				s.addCell(new Label(12, row, lrd.getCapital() != null ? lrd.getCapital().toString() : "N/A"));
				s.addCell(new Label(13, row, lrd.getCode2()));
				row++;
				System.out.println("A gerar linha " + row);
			}

			w.write();
			w.close();
			byte[] d = b.toByteArray();

			redirectUrl(d);
			setMaxResults(25);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void exportselected() {
		System.out.println("A preparar para exportar os seleccionados ");
		List<Country> rs = getResultList();
		first();
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			WritableWorkbook w = Workbook.createWorkbook(b);
			WritableSheet s = w.createSheet("Sheet", 0);
			int row = 0;
			s.addCell(new Label(0, row, "Country Code: "));
			s.addCell(new Label(1, row, "Country Name: "));
			s.addCell(new Label(2, row, "Country Continent: "));
			s.addCell(new Label(3, row, "Country Region: "));
			s.addCell(new Label(4, row, "Country Surface: "));
			s.addCell(new Label(5, row, "Country Indepen. Year: "));
			s.addCell(new Label(6, row, "Country Population: "));
			s.addCell(new Label(7, row, "Country Life Expectency: "));
			s.addCell(new Label(8, row, "Country Gnp: "));
			s.addCell(new Label(9, row, "Country Local Name: "));
			s.addCell(new Label(10, row, "Country Government: "));
			s.addCell(new Label(11, row, "Country Head of State: "));
			s.addCell(new Label(12, row, "Country Capital: "));
			s.addCell(new Label(13, row, "Country Code 2: "));
			row++;

			for (Country lrd : rs) {
				if (lrd.isSelected()) {
					s.addCell(new Label(0, row, lrd.getCode()));
					s.addCell(new Label(1, row, lrd.getName()));
					s.addCell(new Label(2, row, lrd.getContinent()));
					s.addCell(new Label(3, row, lrd.getRegion()));
					s.addCell(new Label(4, row, String.valueOf(lrd.getSurfaceArea())));
					s.addCell(new Label(5, row, lrd.getIndepYear() != null ? lrd.getIndepYear().toString() : "N/A" ));
					s.addCell(new Label(6, row, String.valueOf(lrd.getPopulation())));
					s.addCell(new Label(7, row, lrd.getLifeExpectancy() != null ? String.valueOf(lrd.getLifeExpectancy()) : "N/A"));
					s.addCell(new Label(8, row, String.valueOf(lrd.getGnp())));
					s.addCell(new Label(9, row, lrd.getLocalName()));
					s.addCell(new Label(10, row, lrd.getGovernmentForm()));
					s.addCell(new Label(11, row, lrd.getHeadOfState()));
					s.addCell(new Label(12, row, lrd.getCapital() != null ? lrd.getCapital().toString() : "N/A"));
					s.addCell(new Label(13, row, lrd.getCode2()));
					row++;
					System.out.println("A gerar linha " + row);
				}
			}

			w.write();
			w.close();
			byte[] d = b.toByteArray();

			redirectUrl(d);
			setMaxResults(25);
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

	// @In(create = true)
	// EntityHome<Country> countryHome;

	public void deleteselected() {
		int n = 0;
		EntityHome<Country> countryHome = new EntityHome<Country>();
		for (Country c : super.getResultList()) {
			if (c.isSelected()) {
				System.out.println(c.getName());
				countryHome.setInstance(c);
				countryHome.getEntityManager().remove(
						countryHome.getEntityManager().merge(c));

				// countryHome.remove();
				// countryHome.persist();
				System.out.println(c.getName() + " Apagado com sucesso.");
				n++;
			}

		}
		this.refresh();
		FacesMessages facesMessages = new FacesMessages();
		facesMessages.addFromResourceBundle(n + " Registos Apagados.");
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				null, n + " Registos Apagados.");
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public void selectedRecord() {
		try {
			System.out.println("oi");
			Iterator<Object> iterator = getSelection().getKeys();

			while (iterator.hasNext()) {
				// Here you will get all the selected roes id from the Data
				// table
				Object obj = iterator.next();

				System.out.println("GET SELECTED ROWS ID ::::: "
						+ obj.toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
