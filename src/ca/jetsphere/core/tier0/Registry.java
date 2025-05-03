package ca.jetsphere.core.tier0;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

public class Registry
{
    private static ApplicationContext context;

    /**
     *
     */

    protected Registry() {}

    /**
     *
     */

    static
    {
    try {

        Registry.context = new ApplicationContext();

    } catch ( Exception e ) { Common.trace ( e ); }

    }

    /**
     *
     */

    static public void get ( JDBC jdbc, Bolt bolt, int id ) { bolt.query ( jdbc, "select * from jet_base_" + bolt.getKey() + " where " + bolt.getKey() + "_id = " + id ); }

    /**
     *
     */

    static public AbstractDao getAbstractDao ( String key ) { return context.getBean ( key, null ); }

    /**
     *
     */

    public static ca.jetsphere.core.tier0.backbone.action            .ActionDao           getActionDao           () { return ( ca.jetsphere.core.tier0.backbone.action            .ActionDao           ) context.getBean ( "action"            , ca.jetsphere.core.tier0.backbone.action            .ActionDao           .class ); }
    public static ca.jetsphere.core.tier0.backbone.application       .ApplicationDao      getApplicationDao      () { return ( ca.jetsphere.core.tier0.backbone.application       .ApplicationDao      ) context.getBean ( "application"       , ca.jetsphere.core.tier0.backbone.application       .ApplicationDao      .class ); }
    public static ca.jetsphere.core.tier0.backbone.audit             .AuditDao            getAuditDao            () { return ( ca.jetsphere.core.tier0.backbone.audit             .AuditDao            ) context.getBean ( "audit"             , ca.jetsphere.core.tier0.backbone.audit             .AuditDao            .class ); }
    public static ca.jetsphere.core.tier0.backbone.brand             .BrandDao            getBrandDao            () { return ( ca.jetsphere.core.tier0.backbone.brand             .BrandDao            ) context.getBean ( "brand"             , ca.jetsphere.core.tier0.backbone.brand             .BrandDao            .class ); }
    public static ca.jetsphere.core.tier0.backbone.bu                .BuDao               getBuDao               () { return ( ca.jetsphere.core.tier0.backbone.bu                .BuDao               ) context.getBean ( "bu"                , ca.jetsphere.core.tier0.backbone.bu                .BuDao               .class ); }
    public static ca.jetsphere.core.tier0.backbone.bu_member         .BuMemberDao         getBuMemberDao         () { return ( ca.jetsphere.core.tier0.backbone.bu_member         .BuMemberDao         ) context.getBean ( "bu_member"         , ca.jetsphere.core.tier0.backbone.bu_member         .BuMemberDao         .class ); }
    public static ca.jetsphere.core.tier0.backbone.company           .CompanyDao          getCompanyDao          () { return ( ca.jetsphere.core.tier0.backbone.company           .CompanyDao          ) context.getBean ( "company"           , ca.jetsphere.core.tier0.backbone.company           .CompanyDao          .class ); }
    public static ca.jetsphere.core.tier0.backbone.company_member    .CompanyMemberDao    getCompanyMemberDao    () { return ( ca.jetsphere.core.tier0.backbone.company_member    .CompanyMemberDao    ) context.getBean ( "company_member"    , ca.jetsphere.core.tier0.backbone.company_member    .CompanyMemberDao    .class ); }
    public static ca.jetsphere.core.tier0.backbone.email             .EmailDao            getEmailDao            () { return ( ca.jetsphere.core.tier0.backbone.email             .EmailDao            ) context.getBean ( "email"             , ca.jetsphere.core.tier0.backbone.email             .EmailDao            .class ); }
    public static ca.jetsphere.core.tier0.backbone.mail_server       .MailServerDao       getMailServerDao       () { return ( ca.jetsphere.core.tier0.backbone.mail_server       .MailServerDao       ) context.getBean ( "mail_server"       , ca.jetsphere.core.tier0.backbone.mail_server       .MailServerDao       .class ); }
    public static ca.jetsphere.core.tier0.backbone.notification      .NotificationDao     getNotificationDao     () { return ( ca.jetsphere.core.tier0.backbone.notification      .NotificationDao     ) context.getBean ( "notification"      , ca.jetsphere.core.tier0.backbone.notification      .NotificationDao     .class ); }
    public static ca.jetsphere.core.tier0.backbone.notification_read .NotificationReadDao getNotificationReadDao () { return ( ca.jetsphere.core.tier0.backbone.notification_read .NotificationReadDao ) context.getBean ( "notification_read" , ca.jetsphere.core.tier0.backbone.notification_read .NotificationReadDao .class ); }
    public static ca.jetsphere.core.tier0.backbone.period            .PeriodDao           getPeriodDao           () { return ( ca.jetsphere.core.tier0.backbone.period            .PeriodDao           ) context.getBean ( "period"            , ca.jetsphere.core.tier0.backbone.period            .PeriodDao           .class ); }
    public static ca.jetsphere.core.tier0.backbone.policy            .PolicyDao           getPolicyDao           () { return ( ca.jetsphere.core.tier0.backbone.policy            .PolicyDao           ) context.getBean ( "policy"            , ca.jetsphere.core.tier0.backbone.policy            .PolicyDao           .class ); }
    public static ca.jetsphere.core.tier0.backbone.role              .RoleDao             getRoleDao             () { return ( ca.jetsphere.core.tier0.backbone.role              .RoleDao             ) context.getBean ( "role"              , ca.jetsphere.core.tier0.backbone.role              .RoleDao             .class ); }
    public static ca.jetsphere.core.tier0.backbone.role_right        .RoleRightDao        getRoleRightDao        () { return ( ca.jetsphere.core.tier0.backbone.role_right        .RoleRightDao        ) context.getBean ( "role_right"        , ca.jetsphere.core.tier0.backbone.role_right        .RoleRightDao        .class ); }
    public static ca.jetsphere.core.tier0.backbone.token             .TokenDao            getTokenDao            () { return ( ca.jetsphere.core.tier0.backbone.token             .TokenDao            ) context.getBean ( "token"             , ca.jetsphere.core.tier0.backbone.token             .TokenDao            .class ); }
    public static ca.jetsphere.core.tier0.backbone.user              .UserDao             getUserDao             () { return ( ca.jetsphere.core.tier0.backbone.user              .UserDao             ) context.getBean ( "user"              , ca.jetsphere.core.tier0.backbone.user              .UserDao             .class ); }

}
