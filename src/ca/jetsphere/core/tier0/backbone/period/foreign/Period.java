package ca.jetsphere.core.tier0.backbone.period.foreign;

import ca.jetsphere.core.tier0.backbone.application.Application;

/**
 *
 */

abstract public class Period extends ca.jetsphere.core.tier0.backbone.period.bean.Period
{
    Application application;

    /**
     *
     */

    static public Class container() { return Application.class; }

    /**
     *
     */

    public Application getApplication() { return application; }

    /**
     *
     */

    public void setApplication ( Application application ) { this.application = application; }

}
