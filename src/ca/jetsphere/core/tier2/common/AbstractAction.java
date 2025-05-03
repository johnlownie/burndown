package ca.jetsphere.core.tier2.common;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.audit.Audit;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 */

abstract public class AbstractAction extends Action
{
    /**
     *
     */

    public ActionForward execute ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException
    {
    JDBC jdbc = new JDBC ( request );

    try {

        Audit audit = new Audit ( request ); Errors errors = new Errors ( mapping, request );

        ActionForward forward = execute ( jdbc, new ActionStore ( mapping, form, request, response ), errors );

        audit.setErrors ( request, errors ); audit.save ( jdbc, request );

        return forward;

    } catch ( Exception e ) { Common.trace ( e ); return mapping.findForward ( "failure" ); }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    static public boolean add          ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "add"          ); }
    static public boolean attach       ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "attach"       ); }
    static public boolean back         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "back"         ); }
    static public boolean batch        ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "batch"        ); }
    static public boolean clear        ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "clear"        ); }
    static public boolean close        ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "close"        ); }
    static public boolean commit       ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "commit"       ); }
    static public boolean copy         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "copy"         ); }
    static public boolean delete       ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "delete"       ); }
    static public boolean edit         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "edit"         ); }
    static public boolean error        ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "error"        ); }
    static public boolean export       ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "export"       ); }
    static public boolean fetch        ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "fetch"        ); }
    static public boolean improt       ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "import"       ); }
    static public boolean insert       ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "insert"       ); }
    static public boolean items        ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "items"        ); }
    static public boolean json         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "json"         ); }
    static public boolean jupdate      ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "jupdate"      ); }
    static public boolean lock         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "lock"         ); }
    static public boolean login        ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "login"        ); }
    static public boolean move         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "move"         ); }
    static public boolean next         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "next"         ); }
    static public boolean notify       ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "notify"       ); }
    static public boolean paste        ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "paste"        ); }
    static public boolean print        ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "print"        ); }
    static public boolean query        ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "query"        ); }
    static public boolean report       ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "report"       ); }
    static public boolean role         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "role"         ); }
    static public boolean save         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "save"         ); }
    static public boolean send         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "send"         ); }
    static public boolean test         ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "test"         ); }
    static public boolean transactions ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "transactions" ); }
    static public boolean update       ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "update"       ); }
    static public boolean verify       ( HttpServletRequest request ) { return ! DockYard.isWhiteSpace ( request, "vkey"         ); }

    /**
     *
     */

    public ActionForward execute ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    if ( add          ( store.getRequest() ) ) return add          ( jdbc, store, errors );
    if ( attach       ( store.getRequest() ) ) return attach       ( jdbc, store, errors );
    if ( back         ( store.getRequest() ) ) return back         ( jdbc, store, errors );
    if ( clear        ( store.getRequest() ) ) return clear        ( jdbc, store, errors );
    if ( close        ( store.getRequest() ) ) return close        ( jdbc, store, errors );
    if ( commit       ( store.getRequest() ) ) return commit       ( jdbc, store, errors );
    if ( copy         ( store.getRequest() ) ) return copy         ( jdbc, store, errors );
    if ( delete       ( store.getRequest() ) ) return delete       ( jdbc, store, errors );
    if ( edit         ( store.getRequest() ) ) return edit         ( jdbc, store, errors );
    if ( error        ( store.getRequest() ) ) return error        ( jdbc, store, errors );
    if ( export       ( store.getRequest() ) ) return export       ( jdbc, store, errors );
    if ( fetch        ( store.getRequest() ) ) return fetch        ( jdbc, store, errors );
    if ( improt       ( store.getRequest() ) ) return improt       ( jdbc, store, errors );
    if ( insert       ( store.getRequest() ) ) return insert       ( jdbc, store, errors );
    if ( items        ( store.getRequest() ) ) return items        ( jdbc, store, errors );
    if ( json         ( store.getRequest() ) ) return json         ( jdbc, store, errors );
    if ( lock         ( store.getRequest() ) ) return lock         ( jdbc, store, errors );
    if ( login        ( store.getRequest() ) ) return login        ( jdbc, store, errors );
    if ( move         ( store.getRequest() ) ) return move         ( jdbc, store, errors );
    if ( next         ( store.getRequest() ) ) return next         ( jdbc, store, errors );
    if ( notify       ( store.getRequest() ) ) return notify       ( jdbc, store, errors );
    if ( paste        ( store.getRequest() ) ) return paste        ( jdbc, store, errors );
    if ( print        ( store.getRequest() ) ) return print        ( jdbc, store, errors );
    if ( query        ( store.getRequest() ) ) return query        ( jdbc, store, errors );
    if ( report       ( store.getRequest() ) ) return report       ( jdbc, store, errors );
    if ( role         ( store.getRequest() ) ) return role         ( jdbc, store, errors );
    if ( save         ( store.getRequest() ) ) return save         ( jdbc, store, errors );
    if ( send         ( store.getRequest() ) ) return send         ( jdbc, store, errors );
    if ( test         ( store.getRequest() ) ) return test         ( jdbc, store, errors );
    if ( transactions ( store.getRequest() ) ) return transactions ( jdbc, store, errors );
    if ( update       ( store.getRequest() ) ) return update       ( jdbc, store, errors );
    if ( verify       ( store.getRequest() ) ) return verify       ( jdbc, store, errors );

    return query ( jdbc, store, errors );
    }

    /**
     *
     */

    public ActionForward add          ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "insert"  ); }
    public ActionForward attach       ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "attach"  ); }
    public ActionForward back         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "back"    ); }
    public ActionForward batch        ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "batch"   ); }
    public ActionForward clear        ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward close        ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "close"   ); }
    public ActionForward commit       ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward copy         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward delete       ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward edit         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "edit"    ); }
    public ActionForward error        ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward export       ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward fetch        ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward improt       ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward insert       ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward items        ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward json         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "json"    ); }
    public ActionForward lock         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward login        ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "failure" ); }
    public ActionForward move         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "move"    ); }
    public ActionForward next         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "next"    ); }
    public ActionForward notify       ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "notify"  ); }
    public ActionForward paste        ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "paste"   ); }
    public ActionForward print        ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "print"   ); }
    public ActionForward query        ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "failure" ); }
    public ActionForward report       ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward role         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "role"    ); }
    public ActionForward save         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward send         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward test         ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "test"    ); }
    public ActionForward transactions ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }
    public ActionForward update       ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "update"  ); }
    public ActionForward verify       ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception { return store.getForward ( "success" ); }

    /**
     *
     */

    public ActionForward getForward ( ActionStore store ) { return store.getForward ( "failure" ); }

    /**
     *
     */

    public Errors delete ( JDBC jdbc, Bolt bolt, HttpServletRequest request, Errors errors ) throws Exception
    {
    try {

        jdbc.setAutoCommit ( false );

        if ( errors.isEmpty() )

            if ( bolt.isValid() ) bolt.delete ( jdbc );

        jdbc.commit();

    } catch ( Exception e ) { jdbc.rollback(); Common.trace ( e ); return errors.add ( "error.delete", bolt.getKey() ); }

    return errors;
    }

    /**
     *
     */

    protected void finito ( JDBC jdbc, HttpServletRequest request, Bolt bolt, boolean isUpdate, Errors errors ) throws Exception {}

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception {}

    /**
     *
     */

    public Errors update ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    try {

        jdbc.setAutoCommit ( false );

        bolt.validate ( jdbc, request, errors );

        if ( ! errors.isEmpty() ) return errors;

        validate ( jdbc, request, bolt, errors );

        if ( ! errors.isEmpty() ) return errors;

        setup ( jdbc, request, bolt, errors );

        if ( ! errors.isEmpty() ) return errors;

        boolean isUpdate = bolt.isValid();

        try { bolt.save ( jdbc ); } catch ( Exception e ) { errors.add ( "error.mysql.constraint.violation" ); return errors; }

        finito ( jdbc, request, bolt, isUpdate, errors );

        if ( ! errors.isEmpty() ) return errors;

        jdbc.commit();

    } catch ( Exception e ) { jdbc.rollback(); Common.trace ( e ); return errors.isEmpty() ? errors.add ( ActionErrors.GLOBAL_MESSAGE, "error.save", bolt.getKey() ) : errors; }

    finally { jdbc.close(); }

    return errors;
    }

    /**
     *
     */

    protected void validate ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception {}

}
