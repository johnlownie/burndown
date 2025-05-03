package ca.jetsphere.core.tier2.backbone.mail_server;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier1.system.knock.Knock;
import ca.jetsphere.core.email.EmailYard;
import ca.jetsphere.core.email.Message;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.notification.NotificationYard;
import ca.jetsphere.core.tier2.common.AbstractAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;

import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class MailServerTestAction extends AbstractAction
{

    /**
     *
     */

    public ActionForward send ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    HttpServletRequest request = store.getRequest();

    Message message = ( Message ) store.getForm();

    Company company = CompanySession.getSelected ( request );

    String from = company == null || ! company.isValid() || DockYard.isWhiteSpace ( company.getEmail() ) ? Knock.get ( "SMTP-MAIL-SERVER-SENDER" ) : company.getEmail();

    message.setSender ( from ); message.setSignature ( company.getName() ); message.setRecipients();

    EmailYard.sendEmail ( message, errors );

    if ( errors.isEmpty() ) { NotificationYard.add ( jdbc, store.getRequest(), "success", "envelope", Caption.get ( store.getRequest(), "mail.server.test" ), Caption.get ( store.getRequest(), "error.email.sent" ), "floating", 4000 ); message.clear (); }

    return errors.forward ( store );
    }

}
