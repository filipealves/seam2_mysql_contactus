package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("countryHome")
public class CountryHome extends EntityHome<Country>
{


    public void setCountryCode(String id)
    {
        setId(id);
    }

    public String getCountryCode()
    {
        return (String) getId();
    }

    @Override
    protected Country createInstance()
    {
        Country country = new Country();
        return country;
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

    public Country getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }


}

