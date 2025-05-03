package ca.jetsphere.core.tier2.backbone.mail_server;

import ca.jetsphere.core.tier1.backbone.mail_server.MailServer;
import ca.jetsphere.core.tier2.common.AbstractEditAction;

/**
 *
 */

public class MailServerEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return MailServer.key(); }

}
