package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("roleHome")
public class RoleHome extends EntityHome<Role>
{


    public void setRoleRoleId(String id)
    {
        setId(id);
    }

    public String getRoleRoleId()
    {
        return (String) getId();
    }

    @Override
    protected Role createInstance()
    {
        Role role = new Role();
        return role;
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

    public Role getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }


}

