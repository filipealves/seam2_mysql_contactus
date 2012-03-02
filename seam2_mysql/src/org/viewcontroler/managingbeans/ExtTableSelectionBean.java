package org.viewcontroler.managingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.domain.seam2mysql.entity.City;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.log.Log;
import org.richfaces.component.UIExtendedDataTable;

//@Stateful
@Scope(ScopeType.SESSION)
@Synchronized(timeout=Long.MAX_VALUE)
@Name("ExtTableSelectionBean")

public class ExtTableSelectionBean implements Serializable,ExtTableInterface {
    @Logger Log log;
    @PersistenceContext
    private EntityManager em;
    
	private Collection<Object> selection;
	
	private List<City> cityItems;

	private List<City> selectionItems = new ArrayList<City>();

    @Remove
    @Destroy
    public void destroy() {
    }
	public void selectionListener(AjaxBehaviorEvent event) {

		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();

		Object originalKey = dataTable.getRowKey();

		selectionItems.clear();

		for (Object selectionKey : selection) {

			dataTable.setRowKey(selectionKey);

			if (dataTable.isRowAvailable()) {

				selectionItems.add((City) dataTable.getRowData());

			}

		}

		dataTable.setRowKey(originalKey);

	}

	public Collection<Object> getSelection() {

		return selection;

	}

	public void setSelection(Collection<Object> selection) {

		this.selection = selection;

	}

	public List<City> getCityItems() {
        String sql="SELECT T FROM City T ";
        cityItems=em.createQuery(sql).setMaxResults(50).getResultList();
		return cityItems;

	}

	public void setcityItems(List<City> cityItems) {

		this.cityItems = cityItems;

	}

	public List<City> getSelectionItems() {

		return selectionItems;

	}

	public void setSelectionItems(List<City> selectionItems) {

		this.selectionItems = selectionItems;

	}
	

}
