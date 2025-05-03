package ca.jetsphere.core.email;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier2.common.Errors;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */

public class EmailYard
{
    /**
     *
     */

    static public boolean isValid ( String address )
    {
    try {

        InternetAddress emailAddress = new InternetAddress ( address );

        emailAddress.validate();

        Pattern pattern = Pattern.compile ( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" );

        Matcher matcher = pattern.matcher ( address );

        return matcher.matches();

    } catch ( AddressException ex ) { return false; }

    }

    /**
     *
     */

    static public void sendEmail (ca.jetsphere.core.email.Message message, Errors errors )
    {
    try {

        if ( DockYard.isWhiteSpace ( message.getSignature() ) )
        {
        SendEmail email = new SendEmail ( message.getSender(), message.getRecipients(), message.getSubject() );

        email.send ( message );

        } else {

        SendEmail email = new SendEmail ( message.getSender(), message.getSignature(), message.getRecipients(), message.getSubject() );

        email.send ( message );
        }

    } catch ( Exception e ) { Common.trace ( e ); errors.add ( "error.email.not.sent" ); }

    }

}
