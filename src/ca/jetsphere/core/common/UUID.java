package ca.jetsphere.core.common;

import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 */

public class UUID
{
    private static Random random; private static String inet;

    /**
     *
     */

    static {

        random = new Random ( new SecureRandom().nextLong() );

        try {

            inet = InetAddress.getLocalHost().toString();

        } catch ( Exception e ) { Common.trace ( "UUID", e ); }

    }

    /**
     *
     */

    static public String get()
    {
    try {

        StringBuilder sb = new StringBuilder();

        MessageDigest md5 = MessageDigest.getInstance ( "MD5" );

        long time = System.currentTimeMillis(); long x = random.nextLong();

        sb.append ( inet ).append ( ":" ).append ( Long.toString ( time ) ).append ( ":" ).append ( Long.toString ( x ) );

        md5.update ( sb.toString().getBytes() );

        byte[] array = md5.digest();

        sb.delete ( 0, sb.length() );

        for ( int cc = 0; cc < array.length; ++cc ) { int b = array [ cc ] & 0xFF; if ( b < 0x10 ) sb.append ( '0' ); sb.append ( Integer.toHexString ( b ) ); }

        return sb.toString();

    } catch ( Exception e ) { Common.trace ( e ); }

    return "";
    }

}
