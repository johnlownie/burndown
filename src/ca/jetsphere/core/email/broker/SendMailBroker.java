package ca.jetsphere.core.email.broker;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.email.FileAttachmentList;
import ca.jetsphere.core.email.SendEmail;
import ca.jetsphere.core.tier1.system.knock.Knock;

/**
 *
 */

public class SendMailBroker implements Runnable
{
    SendMailQueue queue = new SendMailQueue();

    /**
     *
     */

    public SendMailQueue getQueue() { return queue; }

    /**
     *
     */
    @Override
    public void run() { try { trace(); whirl(); } catch ( Exception e ) { Common.trace ( e ); } }

    /**
     *
     */

    public void trace() { Common.debug ( "[ SEND MAIL BROKER ] ( QUEUE SIZE ) " + queue.size() ); }

    /**
     *
     */

    public void whirl() throws Exception
    {
    if ( DockYard.toBoolean ( Knock.get ( "DEVELOPER" ) ) ) return;

    int tries = 0;

    while ( ! queue.isEmpty() )

        try {

            SendMailQueuedMessage x = queue.pop();

            SendEmail sendEmail = new SendEmail ( x.getFrom(), x.getPersonal(), x.getTo(), x.getCc(), x.getBcc(), x.getSubject() );

            FileAttachmentList fal = x.getFileList();

            if ( fal == null || fal.isEmpty() ) sendEmail.send ( x.getMessage(), true );

            else sendEmail.send ( x.getMessage(), fal, true );

        } catch ( Exception e ) { Common.trace ( e ); if ( ++ tries > 3 ) return; }

    }

}
