package ca.jetsphere.core.tier1.backbone.period;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;

import javax.servlet.http.HttpServletRequest;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 */

public class PeriodYard
{
    static public final int TYPE1 = 0x01;
    static public final int TYPE2 = 0x02;
    static public final int TYPE3 = 0x03;
    static public final int TYPE4 = 0x04;
	
    /**
     *
     */
	
    static public Comparator BY_ORDINAL = new Comparator()
    {
    public int compare ( Object o1, Object o2 )
    {
    Period p1 = ( Period ) o1; Period p2 = ( Period ) o2;
	
    int cc = DockYard.compareTo ( p1.getOrdinal(), p2.getOrdinal() );
	
    if ( cc != 0 ) return cc;
	
    return DockYard.compareTo ( p1.getName(), p2.getName() );
    }
    };
	   
    /**
     *
     */

    static public void delete ( JDBC jdbc, int application_id ) throws Exception
    {
    PeriodSession periods = new PeriodSession ( jdbc, application_id );

    Iterator it = periods.iterator ( false );

    while ( it.hasNext() )

    { Period period = ( Period ) it.next(); period.delete ( jdbc ); }

    }

    /**
     *
     */

    static public Period get ( HttpServletRequest request ) { return ( Period ) PeriodSession.getSelected ( request ); }

    /**
     * 
     */
    
    static public PeriodSession getOpen ( JDBC jdbc, HttpServletRequest request, int application_id )
    {
    PeriodSession periods = PeriodSession.getInstance ( request );

    StringBuilder sb = new StringBuilder();
	
    sb.append ( "select * from jet_base_period where period_application_id = " + application_id + " and period_closed = 0" );
	
    periods.query ( jdbc, sb.toString() );
    	
    return periods;
    }
    
    /**
     *
     */

    static public String getName ( HttpServletRequest request )
    {
    Period period = get ( request );

    return period != null ? period.getName() : "???";
    }

    /**
     *
     */

    static public boolean isClosed ( HttpServletRequest request )
    {
    Period period = get ( request );

    return period != null ? period.getClosed() : false;
    }

    /**
     * 
     */
    
    static public int openCount ( PeriodSession periods ) 
    {
    try {
		
	Iterator it = periods.iterator(); int count = 0;
		
	while ( it.hasNext() )
			
	{ Period period = ( Period ) it.next(); if ( !period.getClosed() )  count++; }
		
        return count;
		
    } catch ( Exception e ) { Common.trace ( e ); return 0; }
    }
}
