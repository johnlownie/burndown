package ca.jetsphere.core.tier2.backbone.brand;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.brand.Brand;
import ca.jetsphere.core.tier1.backbone.brand.BrandSession;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.common.QueryActionForm;
import org.apache.struts.action.ActionForward;

/**
 *
 */

public class BrandDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return Brand.key (); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );

    BrandSession.query ( jdbc, store.getRequest (), company.getId (), false );

    return getForward ( store );
    }

}