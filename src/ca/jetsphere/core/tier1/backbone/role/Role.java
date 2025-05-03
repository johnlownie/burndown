package ca.jetsphere.core.tier1.backbone.role;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.role.common.Type;
import ca.jetsphere.core.tier1.backbone.role_right.RoleRight;

import ca.jetsphere.core.tier1.backbone.role_right.RoleRightYard;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class Role extends ca.jetsphere.core.tier0.backbone.role.Role
{
    /**
     *
     */

    public Role() { super(); }

    /**
     *
     */

    public Role ( JDBC jdbc, int role_id ) { super(jdbc, role_id); }

    /**
     *
     */

    public Role ( JDBC jdbc, int application_id, String alias ) throws Exception

    { this(); query(jdbc, "select * from jet_base_role where role_application_id = " + application_id + " and role_alias = " + DockYard.quote(alias)); }

    /**
     *
     */

    public Role ( JDBC jdbc, HttpServletRequest request, String alias ) throws Exception

    { this(jdbc, ApplicationSession.getSelected(request).getId(), alias); }

    /**
     *
     */

    public Role ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "name", "type", "read.only", "last.update", "actions" }; }

    /**
     *
     */

    public int compareTo ( Object o )
    {
    Role role = ( Role ) o;

    return DockYard.compareTo ( getOrdinal(), role.getOrdinal() ) == 0 ? DockYard.compareTo ( getName(), role.getName() ) : DockYard.compareTo ( getOrdinal(), role.getOrdinal() );
    }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception
    {
    RoleRightYard.delete         ( jdbc, getId() );

    super.delete ( jdbc );
    }

    /**
     *
     */

    static public String [] fields() { return new String [] { "role_name", "role_type", "role_read_only", "role_last_update", "role_uuid" }; }

    /**
     *
     */

    static public Role get ( int role_id )
    {
    JDBC jdbc = new JDBC();

    try {

        return new Role ( jdbc, role_id );

    } catch ( Exception e ) { Common.trace(e); }

    finally { jdbc.close(); }

    return new Role();
    }

    /**
     *
     */

    public Object get ( String s )
    {
    if ( "role_name" .equals ( s ) ) return Caption.get(getLocale(), getName()); if ( "role_type" .equals ( s ) ) return Caption.get ( getLocale(), Type.getType ( getType() ) );

    return super.get(s);
    }

    /**
     *
     */

    static public int getId ( JDBC jdbc, HttpServletRequest request, String role_alias ) throws Exception
    {
    Role role = new Role ( jdbc, request, role_alias );

    if ( ! role.isValid() ) role = new Role ( jdbc, -1, role_alias );

    return role.getId();
    }

    /**
     *
     */

    public String getName ( HttpServletRequest request ) { return Caption.get ( request, getName() ); }

    /**
     *
     */

    static public Role getRole ( HttpServletRequest request, String alias )
    {
    JDBC jdbc = new JDBC ( request );

    try {

        return new Role ( jdbc, request, alias );

    } catch ( Exception e ) { Common.trace ( e ); return new Role(); }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { super.insert ( jdbc ); RoleRight.setRoot ( jdbc, this ); }

    /**
     *
     */

    static public boolean isType ( JDBC jdbc, int role_type, int role_id ) throws Exception

    { Role role = new Role ( jdbc, role_id ); return ( role.getType() & role_type ) != 0; }

}
