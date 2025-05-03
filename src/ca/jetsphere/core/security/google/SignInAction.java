package ca.jetsphere.core.security.google;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson.JacksonFactory;
//import com.google.api.services.plus.Plus;
//import com.google.api.services.plus.model.PeopleFeed;
import org.apache.struts.action.ActionForward;

/**
 *
 */

public class SignInAction extends AbstractDoAction
{
    /*
     * Default HTTP transport to use to make HTTP requests.
     */

    private static final HttpTransport TRANSPORT = new NetHttpTransport();

    /**
     *
     */

    public String getKey() { return "google"; }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    return store.getForward ( "failure" );
    }

}
