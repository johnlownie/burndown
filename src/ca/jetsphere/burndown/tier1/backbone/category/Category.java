package ca.jetsphere.burndown.tier1.backbone.category;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.tree.Tree;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class Category extends ca.jetsphere.burndown.tier0.backbone.category.Category
{
    int category_insert_type;

    /**
     *
     */
    public Category() { super(); }

    /**
     *
     */

    public Category ( Category category ) { this(); copy ( category ); }

    /**
     *
     */
    public Category ( JDBC jdbc )

    { super(); query ( jdbc, "select * from jet_burndown_category" ); }

    /**
     *
     */
    public Category ( JDBC jdbc, int category_id ) throws Exception { super ( jdbc, category_id ); }

    /**
     *
     */
    public Category ( Application application )

    { this(); setApplicationId ( application.getId() ); setParentId ( 0 ); setParentUuid ( application.getUuid() ); setName ( application.getName() ); }

    /**
     *
     */
    public Category ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */
    static public String [] captions() { return new String [] { "id", "parent", "category.name", "category.included", "category.fixed", "actions" }; }

    /**
     *
     */
    static public String [] fields() { return new String [] { "category_id", "category_parent_id", "category_name", "category_included", "category_fixed", "category_uuid" }; }

    /**
     *
     */
    public Object get ( String s )
    {
    if ( "DT_RowId"            .equals ( s ) ) return getId();

    if ( "category_name"       .equals ( s ) ) return getTableName();

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
    if ( !getStatic() ) sb.append ( "<li><a class=\"editBtn\" href=\"#\" data-id=\"" + uuid + "\">" + Caption.get ( "edit" ) + "</a></li>" );
    if ( !getStatic() ) sb.append ( "<li><a class=\"cutBtn\" href=\"#\" data-id=\"" + uuid + "\">" + Caption.get ( "cut" ) + "</a></li>" );
    if ( !getStatic() ) sb.append ( "<li class=\"divider\"></li>" );
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

    { return Caption.get ( request, getName() ); }
    
    /**
     * 
     */
    public String getTreeName()
    {
    StringBuilder sb = new StringBuilder();
    
    for ( int i = 0; i < getDepth(); i++ )
        
    { sb.append ( " --- "); }
    
    sb.append ( getName() );
    
    return sb.toString();
    }

    /**
     *
     */
    static public void setRoot ( JDBC jdbc, Application application ) throws Exception

    { Category category = new Category ( application ); category.insert ( jdbc ); }

    /**
     *
     */

    public Tree twin() { return new Category ( this ); }

    /**
     *
     */
    public int getInsertType() { return category_insert_type ; }

    /**
     *
     */
    public void setInsertType ( int category_insert_type ) { this.category_insert_type = category_insert_type ; }
}
