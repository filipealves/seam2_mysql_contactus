package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("countrylanguageList")
public class CountrylanguageList extends EntityQuery<Countrylanguage>
{

    private static final String EJBQL = "select countrylanguage from Countrylanguage countrylanguage";

    private static final String[] RESTRICTIONS = {
        "lower(countrylanguage.id.countryCode) like lower(concat(#{countrylanguageList.countrylanguage.id.countryCode},'%'))",
        "lower(countrylanguage.id.language) like lower(concat(#{countrylanguageList.countrylanguage.id.language},'%'))",
        "lower(countrylanguage.isOfficial) like lower(concat(#{countrylanguageList.countrylanguage.isOfficial},'%'))",
    };

    private Countrylanguage countrylanguage;

    public CountrylanguageList()
    {
        countrylanguage = new Countrylanguage();
        countrylanguage.setId( new CountrylanguageId() );
        setEjbql(EJBQL);
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
        setMaxResults(25);
    }

    public Countrylanguage getCountrylanguage()
    {
        return countrylanguage;
    }
}
