package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("aplUtilHome")
public class AplUtilHome extends EntityHome<AplUtil>
{

    @In(create=true)
    AplPerfilHome aplPerfilHome;

    public void setAplUtilUtilizador(String id)
    {
        setId(id);
    }

    public String getAplUtilUtilizador()
    {
        return (String) getId();
    }

    @Override
    protected AplUtil createInstance()
    {
        AplUtil aplUtil = new AplUtil();
        return aplUtil;
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
        AplPerfil aplPerfil=aplPerfilHome.getDefinedInstance();
        if ( aplPerfil!=null )
        {
           getInstance().setAplPerfil(aplPerfil);
        }
    }

    public boolean isWired()
    {
        if ( getInstance().getAplPerfil()==null ) return false;
        return true;
    }

    public AplUtil getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }


}

