package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("aplPerfilList")
public class AplPerfilList extends EntityQuery<AplPerfil>
{

    private static final String EJBQL = "select aplPerfil from AplPerfil aplPerfil";

    private static final String[] RESTRICTIONS = {
        "lower(aplPerfil.codPerfil) like lower(concat(#{aplPerfilList.aplPerfil.codPerfil},'%'))",
        "lower(aplPerfil.descPerfil) like lower(concat(#{aplPerfilList.aplPerfil.descPerfil},'%'))",
    };

    private AplPerfil aplPerfil = new AplPerfil();

    public AplPerfilList()
    {
        setEjbql(EJBQL);
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
        setMaxResults(25);
    }

    public AplPerfil getAplPerfil()
    {
        return aplPerfil;
    }
}
