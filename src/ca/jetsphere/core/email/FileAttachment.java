package ca.jetsphere.core.email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import java.io.File;

/**
 *
 */

public class FileAttachment
{
    String filename, filepath;

    /**
     *
     */

    public FileAttachment ( String filename, String filepath ) { this.filename = filename; this.filepath = filepath; }

    /**
     *
     */

    public void delete() throws Exception { new File ( filepath ).delete(); }

    /**
     *
     */

    static public MimeBodyPart get ( String filename, String filepath ) throws Exception
    {
            MimeBodyPart mbp = new MimeBodyPart();

            mbp.setFileName ( filename ); mbp.setDataHandler ( new DataHandler( new FileDataSource( filepath ) ) );

            return mbp;
    }

    /**
     *
     */

    public MimeBodyPart get() throws Exception { return get ( filename, filepath ); }

}
