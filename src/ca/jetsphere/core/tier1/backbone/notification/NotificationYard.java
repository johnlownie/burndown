package ca.jetsphere.core.tier1.backbone.notification;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.notification_read.NotificationRead;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */

public class NotificationYard
{
    /**
     *
     */

    static public void add ( JDBC jdbc, HttpServletRequest request, String type, String title, String message, String container )

    { add ( jdbc, request, type, "", title, message, container, 4000 ); }

    /**
     *
     */

    static public void add ( JDBC jdbc, HttpServletRequest request, String type, String icon, String title, String message, String container, int timer )

    { User whoAmI = UserYard.whoAmI ( request ); add ( jdbc, whoAmI.getId(), type, icon, title, message, container, timer ); }

    /**
     *
     */

    static public void add ( JDBC jdbc, int user_id, String type, String icon, String title, String message, String container, int timer )
    {
    try {

        Notification notification = new Notification();

        notification.setUserId    ( user_id   );
        notification.setType      ( type      );
        notification.setIcon      ( icon      );
        notification.setTitle     ( title     );
        notification.setMessage   ( message   );
        notification.setContainer ( container );
        notification.setTimer     ( timer     );

        notification.save ( jdbc );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

    /**
     *
     */

    static public String get ( String type, String icon, String title, String message, String container, int timer )
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( "notify(\"" + type + "\", \"" + icon + "\", \"" + title + "\", \"" + message + "\", \"" + container + "\", " + timer + ");" );

    return sb.toString();
    }

    /**
     *
     */

    static public String get ( HttpServletRequest request, int user_id )
    {
    JDBC jdbc = new JDBC();

    try {

        return get ( jdbc, request, user_id );

    } catch ( Exception e ) { Common.trace ( e ); return ""; }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    static public String get ( JDBC jdbc, HttpServletRequest request, int user_id ) throws Exception
    {
    StringBuilder sb = new StringBuilder();

    NotificationSession notifications = get ( jdbc, user_id, ( String ) request.getAttribute ( "javax.servlet.forward.request_uri" ) );

    Iterator it = notifications.iterator ( true );

    while ( it.hasNext () )
    {
    Notification notification = ( Notification ) it.next();

    String icon = DockYard.isWhiteSpace ( notification.getIcon() ) ? "" : notification.getIcon();

    sb.append ( get ( notification.getType(), icon, notification.getTitle(), notification.getMessage(), notification.getContainer(), notification.getTimer() ) );

    markAsRead ( jdbc, notification, user_id );
    }

    return sb.toString();
    }

    /**
     *
     */

    static public NotificationSession get ( JDBC jdbc, int user_id, String href )
    {
    NotificationSession notifications = new NotificationSession();

    StringBuilder sb = new StringBuilder();

    sb.append ( "select jet_base_notification.* from jet_base_notification" );
    sb.append ( " left join jet_base_notification_read on notification_read_notification_id = notification_id and notification_read_user_id = " + user_id );
    sb.append ( " left join jet_base_bu on bu_id = notification_bu_id" );
    sb.append ( " left join jet_base_bu_member on bu_member_bu_id = bu_id and bu_member_company_member_id = " + user_id );
    sb.append ( " where (notification_user_id = -1 or notification_user_id = " + user_id + ")" );
    sb.append ( " and notification_active and notification_until > now()" );
    sb.append ( " and locate(notification_href, " + DockYard.quote ( href ) + " ) > 0" );
    sb.append ( " and isnull(notification_read_id)" );

    notifications.query ( jdbc, sb.toString() );

    return notifications;
    }

    /**
     *
     */

    static public String getAlert ( String icon, String title, String message )
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( "<div class=\"alert alert-info media fade in\">" );
    sb.append ( "<div class=\"media-left\">" );
    sb.append ( "<span class=\"icon-wrap icon-wrap-xs icon-circle alert-icon\"><i class=\"fa fa-lg fa-" + icon + "\"></i></span>" );
    sb.append ( "</div>" );
    sb.append ( "<div class=\"media-body\">" );
    sb.append ( "<h4 class=\"alert-title\">" + title + "</h4>" );
    sb.append ( "<p class=\"alert-message\">" + message + "</p>" );
    sb.append ( "</div>" );
    sb.append ( "</div>" );

    return sb.toString();
    }

    /**
     *
     */

    static public List getTypes()
    {
    List list = new ArrayList();

    list.add ( "primary" ); list.add ( "info" ); list.add ( "success" ); list.add ( "mint" ); list.add ( "pink" );

    list.add ( "purple" ); list.add ( "warning" ); list.add ( "danger" ); list.add ( "dark" );

    return list;
    }

    /**
     *
     */

    static public void markAsRead ( JDBC jdbc, Notification notification, int user_id ) throws Exception
    {
//    if ( notification.getUserId() == user_id ) notification.delete ( jdbc );

    NotificationRead notificationRead = new NotificationRead();

    notificationRead.setNotificationId ( notification.getId() ); notificationRead.setUserId ( user_id );

    notificationRead.save ( jdbc );
    }
}
