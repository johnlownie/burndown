package ca.jetsphere.core.tier1.backbone.action;

import ca.jetsphere.core.jdbc.JDBC;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class Action extends ca.jetsphere.core.tier0.backbone.action.Action
{
    /**
     *
     */

    public Action() { super(); }

    /**
     *
     */

    public Action ( Action action ) { this(); copy ( action ); }

    /**
     *
     */

    public Action ( JDBC jdbc, int action_id ) { super ( jdbc, action_id ); }

    /**
     *
     */

    public Action ( JDBC jdbc, HttpServletRequest request, int company_id, String name, String href ) throws Exception

    { this(); setCompanyId ( company_id ); setName ( name ); setHref ( href ); insert ( jdbc ); }

    /**
     *
     */

    public Action ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "name", "href", "public", "last.update", "actions" }; }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "action_name", "action_href", "action_public", "action_last_update", "action_uuid" }; }

}
