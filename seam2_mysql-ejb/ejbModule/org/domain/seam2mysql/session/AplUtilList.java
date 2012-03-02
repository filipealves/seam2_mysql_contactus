package org.domain.seam2mysql.session;

import org.domain.seam2mysql.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("aplUtilList")
public class AplUtilList extends EntityQuery<AplUtil>
{

    private static final String EJBQL = "select aplUtil from AplUtil aplUtil";

    private static final String[] RESTRICTIONS = {
        "lower(aplUtil.utilizador) like lower(concat(#{aplUtilList.aplUtil.utilizador},'%'))",
        "lower(aplUtil.passwd) like lower(concat(#{aplUtilList.aplUtil.passwd},'%'))",
        "lower(aplUtil.unidOrg) like lower(concat(#{aplUtilList.aplUtil.unidOrg},'%'))",
    };

    private AplUtil aplUtil = new AplUtil();

    public AplUtilList()
    {
        setEjbql(EJBQL);
        setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
        setMaxResults(25);
    }

    public AplUtil getAplUtil()
    {
        return aplUtil;
    }
}
