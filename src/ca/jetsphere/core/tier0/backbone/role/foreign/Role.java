package ca.jetsphere.core.tier0.backbone.role.foreign;

import ca.jetsphere.core.tier0.backbone.application.Application;

/**
 *
 */

abstract public class Role extends ca.jetsphere.core.tier0.backbone.role.bean.Role
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
