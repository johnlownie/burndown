package ca.jetsphere.core.tier0.backbone.role_right.foreign;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.action.Action;
import ca.jetsphere.core.tier0.backbone.role.Role;

/**
 *
 */

abstract public class RoleRight extends ca.jetsphere.core.tier0.backbone.role_right.bean.RoleRight
{
    Action action; Role role;

    /**
     *
     */

    static public Class container() { return Role.class; }

    /**
     *
     */

    public void foreign ( JDBC jdbc ) throws Exception

    { setAction ( new Action ( jdbc, getActionId() ) ); setRole ( new Role ( jdbc, getRoleId() ) ); }

    /**
     *
     */

    public Action getAction() { return action; }

    /**
     *
     */

    public void setAction ( Action action ) { this.action = action; }

    /**
     *
     */

    public Role getRole() { return role; }

    /**
     *
     */

    public void setRole ( Role role ) { this.role = role; }

}
