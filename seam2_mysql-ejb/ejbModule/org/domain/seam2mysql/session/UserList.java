package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("userList")
public class UserList extends EntityQuery<User>
{

    private static final String EJBQL = "select user from User user";

    private static final String[] RESTRICTIONS = {
        "lower(user.user) like lower(concat(#{userList.user.user},'%'))",
        "lower(user.pass) like lower(concat(#{userList.user.pass},'%'))",
    };

    private User user = new User();

    public UserList()
    {
        setEjbql(EJBQL);
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
        setMaxResults(25);
    }

    public User getUser()
    {
        return user;
    }
}
