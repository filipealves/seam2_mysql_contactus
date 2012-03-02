package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("roleList")
public class RoleList extends EntityQuery<Role>
{

    private static final String EJBQL = "select role from Role role";

    private static final String[] RESTRICTIONS = {
        "lower(role.roleId) like lower(concat(#{roleList.role.roleId},'%'))",
        "lower(role.roleName) like lower(concat(#{roleList.role.roleName},'%'))",
    };

    private Role role = new Role();

    public RoleList()
    {
        setEjbql(EJBQL);
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
        setMaxResults(25);
    }

    public Role getRole()
    {
        return role;
    }
}
