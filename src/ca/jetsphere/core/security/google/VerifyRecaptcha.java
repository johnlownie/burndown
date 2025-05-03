package ca.jetsphere.core.security.google;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier1.system.knock.Knock;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

/**
 *
 */

public class VerifyRecaptcha
{
    static public  final String api_url    = "https://www.google.com/recaptcha/api/siteverify";
    static private final String USER_AGENT = "Mozilla/5.0";

    /**
     *
     */

    public static boolean verify ( String response )
    {
    if ( DockYard.isWhiteSpace ( response ) ) return false;

    try {

        URL url = new URL ( api_url ); HttpsURLConnection connection = ( HttpsURLConnection ) url.openConnection();

        connection.setRequestMethod   ( "POST" );
        connection.setRequestProperty ( "User-Agent"     , USER_AGENT       );
        connection.setRequestProperty ( "Accept-Language", "en-US,en;q=0.5" );

        String postParams = "secret=" + Knock.get ( "SECRETKEY" ) + "&response=" + response;

        connection.setDoOutput ( true );

        DataOutputStream dos = new DataOutputStream ( connection.getOutputStream() );

        dos.writeBytes ( postParams ); dos.flush(); dos.close();

        int responseCode = connection.getResponseCode();

        BufferedReader in = new BufferedReader ( new InputStreamReader ( connection.getInputStream() ) );

        String inputLine; StringBuilder sb = new StringBuilder();

        while ( ( inputLine = in.readLine() ) != null ) { sb.append ( inputLine ); }

        in.close();

        JsonReader jsonReader = Json.createReader ( new StringReader ( sb.toString() ) );

        JsonObject jsonObject = jsonReader.readObject();

        jsonReader.close();

        return jsonObject.getBoolean ( "success" );

    } catch ( Exception e ) { Common.trace ( e ); return false; }

    }

}
