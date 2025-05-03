package ca.jetsphere.core.email;

import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */

public class FileAttachmentList
{
    ArrayList list = new ArrayList();

    /**
     *
     */

    public void add ( FileAttachment fa ) { list.add ( fa ); }

    /**
     *
     */

    public MimeMultipart addTo ( MimeMultipart mp ) throws Exception
    {
    Iterator it = list.iterator();

    while ( it.hasNext() )
    {
        FileAttachment fa = ( FileAttachment ) it.next();

        mp.addBodyPart ( fa.get() );
    }

    return mp;
    }

    /**
     *
     */

    public void delete() throws Exception
    {
    Iterator it = list.iterator();

    while ( it.hasNext() )

    { FileAttachment fa = ( FileAttachment ) it.next(); fa.delete(); }

    }

    /**
     *
     */

    public boolean isEmpty() { return list.size() == 0; }

    /**
     *
     */

    public int size() { return list.size(); }

}
