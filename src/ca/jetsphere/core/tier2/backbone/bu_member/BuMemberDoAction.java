package ca.jetsphere.core.tier2.backbone.bu_member;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.bu.BuSession;
import ca.jetsphere.core.tier1.backbone.bu_member.BuMember;
import ca.jetsphere.core.tier1.backbone.bu_member.BuMemberSession;
import ca.jetsphere.core.tier1.backbone.role.RoleYard;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.common.QueryActionForm;

import org.apache.struts.action.ActionForward;

/**
 *
 */

public class BuMemberDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return BuMember.key (); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    BuSession         .query ( jdbc, store.getRequest(), qaf.getPeriodId(), false );

    BuMemberSession   .query ( jdbc, store.getRequest(), qaf.getPeriodId(), qaf.getBuId(), false );

    RoleYard          .getBuRoles ( jdbc, store.getRequest() );

    return getForward ( store );
    }

}
