package ca.jetsphere.core.tier1.backbone.audit;

/**
 *
 */

public class AuditType
{
    static final public int INSERT = 0;
    static final public int DELETE = 1;
    static final public int UPDATE = 2;

    /**
     *
     */

    static public String get ( int type )
    {
    switch ( type )
    {
    case INSERT : return "audit.insert" ;
    case DELETE : return "audit.delete" ;
    case UPDATE : return "audit.update" ;
    }

    return "audit.unknown" ;
    }
}
