package ca.jetsphere.core.email.broker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SendMailQueue
{
    List list = new ArrayList ();

    /**
     *
     */

    public boolean isEmpty() { return list.size() == 0; }

    /**
     *
     */

    public SendMailQueuedMessage pop() { if ( isEmpty() ) return null; SendMailQueuedMessage message = ( SendMailQueuedMessage ) list.get ( 0 ); list.remove ( 0 ); return message; }

    /**
     *
     */

    public void push ( SendMailQueuedMessage message ) { list.add ( message ); }

    /**
     *
     */

    public int size() { return list.size(); }

    /**
     *
     */

    public SendMailQueuedMessage top() { return !isEmpty() ? ( SendMailQueuedMessage ) list.get ( 0 ) : null; }

}
