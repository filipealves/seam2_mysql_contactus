package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("countrylanguageHome")
public class CountrylanguageHome extends EntityHome<Countrylanguage>
{


    public void setCountrylanguageId(CountrylanguageId id)
    {
        setId(id);
    }

    public CountrylanguageId getCountrylanguageId()
    {
        return (CountrylanguageId) getId();
    }

    public CountrylanguageHome()
    {
        setCountrylanguageId( new CountrylanguageId() );
    }

    @Override
    public boolean isIdDefined()
    {
        if ( getCountrylanguageId().getCountryCode()==null || "".equals( getCountrylanguageId().getCountryCode() ) ) return false;
        if ( getCountrylanguageId().getLanguage()==null || "".equals( getCountrylanguageId().getLanguage() ) ) return false;
        return true;
    }

    @Override
    protected Countrylanguage createInstance()
    {
        Countrylanguage countrylanguage = new Countrylanguage();
        countrylanguage.setId( new CountrylanguageId() );
        return countrylanguage;
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

    public Countrylanguage getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }


}

