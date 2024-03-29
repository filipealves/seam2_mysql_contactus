package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("userHome")
public class UserHome extends EntityHome<User>
{


    public void setUserUser(String id)
    {
        setId(id);
    }

    public String getUserUser()
    {
        return (String) getId();
    }

    @Override
    protected User createInstance()
    {
        User user = new User();
        return user;
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

    public User getDefinedInstance()
    {
        return isIdDefined() ? getInstance() : null;
    }


}

