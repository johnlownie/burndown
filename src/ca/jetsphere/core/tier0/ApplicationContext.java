package ca.jetsphere.core.tier0;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class ApplicationContext
{
    static Map context = new HashMap();

    /**
     *
     */

    public ApplicationContext()
    {
    context.put ( "action"            , new ca.jetsphere.core.tier0.backbone.action            .ActionDao           () );
    context.put ( "application"      , new ca.jetsphere.core.tier0.backbone.application        .ApplicationDao      () );
    context.put ( "audit"             , new ca.jetsphere.core.tier0.backbone.audit             .AuditDao            () );
    context.put ( "brand"             , new ca.jetsphere.core.tier0.backbone.brand             .BrandDao            () );
    context.put ( "bu"                , new ca.jetsphere.core.tier0.backbone.bu                .BuDao               () );
    context.put ( "bu_member"         , new ca.jetsphere.core.tier0.backbone.bu_member         .BuMemberDao         () );
    context.put ( "company"           , new ca.jetsphere.core.tier0.backbone.company           .CompanyDao          () );
    context.put ( "company_member"    , new ca.jetsphere.core.tier0.backbone.company_member    .CompanyMemberDao    () );
    context.put ( "email"             , new ca.jetsphere.core.tier0.backbone.email             .EmailDao            () );
    context.put ( "mail_server"       , new ca.jetsphere.core.tier0.backbone.mail_server       .MailServerDao       () );
    context.put ( "notification"      , new ca.jetsphere.core.tier0.backbone.notification      .NotificationDao     () );
    context.put ( "notification_read" , new ca.jetsphere.core.tier0.backbone.notification_read .NotificationReadDao () );
    context.put ( "period"            , new ca.jetsphere.core.tier0.backbone.period            .PeriodDao           () );
    context.put ( "policy"            , new ca.jetsphere.core.tier0.backbone.policy            .PolicyDao           () );
    context.put ( "role"              , new ca.jetsphere.core.tier0.backbone.role              .RoleDao             () );
    context.put ( "role_right"        , new ca.jetsphere.core.tier0.backbone.role_right        .RoleRightDao        () );
    context.put ( "token"             , new ca.jetsphere.core.tier0.backbone.token             .TokenDao            () );
    context.put ( "user"              , new ca.jetsphere.core.tier0.backbone.user              .UserDao             () );
    }

    /**
     *
     */

    public AbstractDao getBean ( String key, Class clazz ) { return ( AbstractDao ) context.get ( key ); }
}
