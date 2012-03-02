package org.domain.seam2mysql.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.domain.seam2mysql.entity.City;
import org.jboss.ejb3.annotation.LocalBinding;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.log.Log;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

@Stateful
@Scope(ScopeType.SESSION)
@Synchronized(timeout = Long.MAX_VALUE)
@Name("cityEditableTable")
public class CityEditableTable implements CityEditableTableInterface {
	@Logger
	Log log;
	@PersistenceContext
	private EntityManager em;

	private Selection selection = new SimpleSelection();

	private List<City> selectionItems = new ArrayList<City>();

	// @DataModel("cityList")
	private static List cityList = null;

	@In(create = true, required = false)
	@Out(required = false)
	private City city = new City();

	@Remove
	@Destroy
	public void destroy() {
	}

	public void newCity() {
		log.info("New City Loading");
	}

	public void save() {

		if (city.getId() == 0) {
			em.persist(city);
		} else {
			em.merge(city);
		}

	}

	// @Factory("cityList")
	public List getCityList() {
		String sql = "SELECT T FROM City T ";
		cityList = em.createQuery(sql).setMaxResults(50).getResultList();
		return cityList;
	}

	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public List<City> getSelectionItems() {

		return selectionItems;

	}

	public void setSelectionItems(List<City> selectionItems) {

		this.selectionItems = selectionItems;

	}

	public City getCity() {
		return city;
	}

	public void mudalinha() {
		System.out.println("na Linha x!");
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