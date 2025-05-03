package ca.jetsphere.burndown.tier1.backbone.category;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.burndown.tier0.backbone.category.CategoryMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */
public class CategorySession extends CategoryMap implements ISessionObject
{
    /**
     *
     */
    public CategorySession() { super(); }

    /**
     *
     */
    public CategorySession ( JDBC jdbc, int company_id ) { super ( jdbc, company_id ); }

    /**
     *
     */
    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Category ( jdbc, rs ) ); }

    /**
     *
     */
    public String [] captions() { return Category.captions(); }

    /**
     *
     */
    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Category() ); }

    /**
     *
     */
    public String [] fields() { return Category.fields(); }

    /**
     *
     */
    static public CategorySession getInstance ( HttpServletRequest request )

    { return ( CategorySession ) SessionCache.getSessionObject ( request, Category.key(), CategorySession.class ); }

    /**
     *
     */
    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */
    static public Category getSelected ( HttpServletRequest request ) { return ( Category ) getInstance ( request ).getSelected(); }

    /**
     *
     */
    static public CategorySession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { CategorySession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

    /**
     *
     */
    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */
    static public void setSelected ( HttpServletRequest request, Category selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }
}
