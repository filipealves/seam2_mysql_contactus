package org.domain.seam2mysql.session;

import java.util.List;

import org.domain.seam2mysql.entity.City;
import org.richfaces.model.selection.Selection;

public interface CityEditableTableInterface {

	public void destroy();

	public void newCity();

	public void save();

	public List getCityList();
	
	public City getCity();
	
	public Selection getSelection();

	public void setSelection(Selection selection);
	
	public void selectedRecord();
	
	public void mudalinha();
}
