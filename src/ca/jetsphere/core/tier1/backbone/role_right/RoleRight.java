package ca.jetsphere.core.tier1.backbone.role_right;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.role.Role;
import ca.jetsphere.core.tier1.tree.Tree;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class RoleRight extends ca.jetsphere.core.tier0.backbone.role_right.RoleRight
{
    int role_right_insert_type;

    /**
     *
     */

    public RoleRight() { super(); }

    /**
     *
     */

    public RoleRight ( RoleRight roleRight ) { this(); copy ( roleRight ); }

    /**
     *
     */

    public RoleRight ( JDBC jdbc, int role_right_id ) throws Exception { super ( jdbc, role_right_id ); }

    /**
     *
     */

    public RoleRight ( Role role )

    { this(); setRoleId ( role.getId() ); setParentId ( 0 ); setParentUuid ( role.getUuid() ); setName ( role.getName() ); }

    /**
     *
     */

    public RoleRight ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "id", "parent", "name", "action", "href", "ordinal", "hidden", "actions" }; }

    /**
     *
     */

    static public String [] fields() { return new String [] { "role_right_id", "role_right_parent_id", "role_right_name", "role_right_action", "role_right_action_href", "role_right_ordinal", "role_right_hidden", "role_right_uuid" }; }

    /**
     *
     */

    public Object get ( String s )
    {
    if ( "DT_RowId"              .equals ( s ) ) return getId();

    if ( "role_right_name"       .equals ( s ) ) return getTableName();

    if ( "role_right_action"     .equals ( s ) ) return getAction() != null ? getAction().getName(): "";

    if ( "role_right_action_href".equals ( s ) ) return getAction() != null ? getAction().getHref(): "";

    if ( s.endsWith ( "_uuid" ) ) return getActionBox ( getUuid() );

    return super.get ( s );
    }

    /**
     *
     */

    protected String getActionBox ( String uuid )
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( "<div class=\"btn-group btn-group-sm node-buttons\">" );
    sb.append ( "<button class=\"btn btn-default btn-active-primary dropdown-toggle\" data-toggle=\"dropdown\" type=\"button\">" + Caption.get ( "action" ) + "<i class=\"dropdown-caret fa fa-caret-down\"></i></button>" );
    sb.append ( "<ul class=\"dropdown-menu dropdown-menu-right\">" );
    sb.append ( "<li><a class=\"editBtn\" href=\"#\" data-id=\"" + uuid + "\">" + Caption.get ( "edit" ) + "</a></li>" );
    sb.append ( "<li><a class=\"cutBtn\" href=\"#\" data-id=\"" + uuid + "\">" + Caption.get ( "cut" ) + "</a></li>" );
    sb.append ( "<li class=\"divider\"></li>" );
    sb.append ( "<li><a class=\"copyBtn\" href=\"#\" data-id=\"" + uuid + "\">" + Caption.get ( "copy" ) + "</a></li>" );
    sb.append ( "<li><a class=\"pasteBtn\" href=\"#\" data-id=\"" + uuid + "\">" + Caption.get ( "paste" ) + "</a></li>" );

    if ( getDepth() <= 0 ) { sb.append ( "</div>" ); return sb.toString(); }

    sb.append ( "<li class=\"divider\"></li>" );
    sb.append ( "<li><a class=\"insertB4Btn\" href=\"#\" data-id=\"" + uuid + "\">" + Caption.get ( "insert.before" ) + "</a></li>" );
    sb.append ( "<li><a class=\"insertA4Btn\" href=\"#\" data-id=\"" + uuid + "\">" + Caption.get ( "insert.after" ) + "</a></li>" );
    sb.append ( "<li><a class=\"insertChild\" href=\"#\" data-id=\"" + uuid + "\">" + Caption.get ( "insert.child" ) + "</a></li>" );
    sb.append ( "</ul>" );
    sb.append ( "</div>" );

    return sb.toString();
    }

    /**
     *
     */

    public String getText ( HttpServletRequest request )
    {
    String s = Caption.get ( request, getName() );

    return ( ! getAction().isValid() ) ? s : s + ( ! DockYard.isWhiteSpace ( getAction().getName() ) ? " [ " + getAction().getName() + " ]" : "" );
    }

    /**
     *
     */

    static public void setRoot ( JDBC jdbc, Role role ) throws Exception

    { RoleRight roleRight = new RoleRight ( role ); roleRight.insert ( jdbc ); }

    /**
     *
     */

    public Tree twin() { return new RoleRight ( this ); }

    /**
     *
     */

    public int getInsertType() { return role_right_insert_type ; }

    /**
     *
     */

    public void setInsertType ( int role_right_insert_type ) { this.role_right_insert_type = role_right_insert_type ; }

}
