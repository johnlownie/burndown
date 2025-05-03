package ca.jetsphere.core.tier1.backbone.bu;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import ca.jetsphere.core.tier1.backbone.bu_member.BuMemberYard;
import ca.jetsphere.core.tier1.tree.Tree;

/**
 *
 */

public class Bu extends ca.jetsphere.core.tier0.backbone.bu.Bu implements Comparable
{
    /**
     *
     */

    public Bu() { super(); }

    /**
     *
     */

    public Bu ( Bu bu ) { this(); copy ( bu ); }

    /**
     *
     */

    public Bu ( JDBC jdbc, int bu_id ) { super ( jdbc, bu_id ); }

    /**
     *
     */

    public Bu ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "#" }; }

    /**
     *
     */

    public int compareTo ( Object o )
    {
    Bu bu = ( Bu ) o;

    return DockYard.compareTo ( getName(), bu.getName() );
    }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception
    {
    BuMemberYard.delete ( jdbc, getId() );

    super.delete ( jdbc );
    }

    /**
     *
     */

    static public String [] fields() { return new String [] { "bu_id"}; }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public String getText ( HttpServletRequest request ) { return getName(); }

    /**
     *
     */

    public String getTreeName()
    {
    StringBuilder sb = new StringBuilder();

    for ( int cc = 0; cc < getDepth(); cc ++ ) sb.append ( "... " );

    sb.append ( DockYard.clipAt ( getName(), 75 ) );

    return sb.toString();
    }

    /**
     *
     */

    public boolean hasAncestor ( int bu_id ) { return getLineage().indexOf ( "/" + bu_id ) > -1; }

    /**
     *
     */

    static public boolean hasChild ( JDBC jdbc, int bu_id )

    { return QueryYard.query ( jdbc, "select count(1) from os_bu where bu_lineage like '%/" + bu_id + "/%'" ) > 0; }

    /**
     *
     */

    public void setAttributes ( Tree tree )

    { Bu parent = ( Bu ) tree; setPeriodId ( parent.getPeriodId() ); setParentId ( parent.getId() ); }

    /**
     *
     */

    public Tree twin() { return new Bu ( this ); }

}
