package ca.jetsphere.core.tier2.backbone.period;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.period.Period;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class PeriodEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return Period.key(); }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors )
    {
    Period period = ( Period ) bolt;

    Application application = ApplicationSession.getSelected ( request );

    period.setApplicationId ( application.getId() );
    }

}