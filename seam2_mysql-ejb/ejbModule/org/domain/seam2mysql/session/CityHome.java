package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("cityHome")
public class CityHome extends EntityHome<City>
{


    public void setCityId(Integer id)
    {
        setId(id);
    }

    public Integer getCityId()
    {
        return (Integer) getId();
    }

    @Override
    protected City createInstance()
    {
        City city = new City();
        return city;
    }

    public void load()
    {
        if (isIdDefined())
        {
            wire();
        }
    }

    public void wire()
    {
        getInstance();
    }

    public boolean isWired()
    {
        return true;
    }

    public City getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }


}

