package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("aplPerfilHome")
public class AplPerfilHome extends EntityHome<AplPerfil>
{


    public void setAplPerfilCodPerfil(String id)
    {
        setId(id);
    }

    public String getAplPerfilCodPerfil()
    {
        return (String) getId();
    }

    @Override
    protected AplPerfil createInstance()
    {
        AplPerfil aplPerfil = new AplPerfil();
        return aplPerfil;
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

    public AplPerfil getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }

    public List<AplUtil> getAplUtils() {
        return getInstance() == null ?
            null : new ArrayList<AplUtil>( getInstance().getAplUtils() );
    }

}

