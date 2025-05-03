package ca.jetsphere.core.tier1.backbone.user;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMember;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMemberYard;
import ca.jetsphere.core.tier1.backbone.role.RoleYard;
import ca.jetsphere.core.tier1.backbone.user.common.Status;

import java.sql.ResultSet;

/**
 *
 */

public class User extends ca.jetsphere.core.tier0.backbone.user.User
{
    private static final long serialVersionUID = 1L;

    CompanyMember companyMember;

    String   user_confirm_password ;
    String   user_confirm_username ;
    String   user_fullname         ;
    String   user_new_password     ;
    String   user_phone            ;
    String[] user_roles            ;
    boolean  user_is_administrator ;

    String openIdIdentifier;
    boolean stayLoggedIn;

    /**
     *
     */

    public User() { super(); }

    /**
     *
     */

    public User ( JDBC jdbc, int user_id ) { super ( jdbc, user_id ); }

    /**
     *
     */

    public User ( JDBC jdbc, ResultSet rs ) throws Exception  { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "username", "status", "role", "last.login", "last.update", "actions" }; }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception
    {
    CompanyMember companyMember = CompanyMemberYard.getByUserId ( jdbc, getId () );

    if ( companyMember != null && companyMember.isValid() ) companyMember.delete ( jdbc );

    super.delete ( jdbc );
    }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "user_username", "user_status", "user_roles", "user_last_login", "user_last_update", "user_uuid" }; }

    /**
     *
     */

    public void foreign ( JDBC jdbc ) throws Exception

    { setCompanyMember ( CompanyMemberYard.getByUserId ( jdbc, getId() ) ); setRoles ( getRoleIds().split ( "\\s*,\\s*" ) );}

    /**
     *
     */

    public Object get ( String s )
    {
    if ( "user_status" .equals ( s ) ) return Caption.get ( getLocale(), Status.get ( getStatus () ) );

    if ( "user_roles"  .equals ( s ) ) return RoleYard.getRoleNames ( getRoleIds() );

    return super.get ( s );
    }

    /**
     *
     */

    public int           getCompanyId      () { return companyMember == null || !companyMember.isValid() ? -1 : companyMember.getCompanyId(); }
    public CompanyMember getCompanyMember  () { return companyMember; }
    public int           getCompanyMemberId() { return companyMember == null || !companyMember.isValid() ? -1 : companyMember.getId(); }
    public String        getFirstname      () { return companyMember == null || !companyMember.isValid() ? "???" : companyMember.getFirstname(); }
    public String        getLastname       () { return companyMember == null || !companyMember.isValid() ? "???" : companyMember.getLastname (); }

    /**
     *
     */

    public String   getConfirmPassword () { return user_confirm_password ; }
    public String   getConfirmUsername () { return user_confirm_username ; }
    public String   getFullname        () { return user_fullname         ; }
    public String   getNewPassword     () { return user_new_password     ; }
    public String   getPhone           () { return user_phone            ; }
    public String[] getRoles           () { return user_roles            ; }

    public String  getOpenIdIdentifier () { return openIdIdentifier ; }
    public boolean getStayLoggedIn     () { return true; } //stayLoggedIn     ; }

    /**
     *
     */

    public String getName() { return getCompanyMember().getName() + " (" + getUsername() + ")"; }

    /**
     *
     */

    public boolean isActive       () { return getStatus() == Status.ACTIVE  ; }
    public boolean isClosed       () { return getStatus() == Status.CLOSED  ; }
    public boolean isFrozen       () { return getStatus() == Status.FROZEN  ; }
    public boolean isPending      () { return getStatus() == Status.PENDING ; }
    public boolean isAdministrator() { return user_is_administrator         ; }

    /**
     *
     */

    public void save ( JDBC jdbc ) throws Exception { if ( ! DockYard.isWhiteSpace ( getRoles() ) ) setRoleIds ( String.join ( ", ", getRoles() ) ); super.save ( jdbc ); }

    /**
     *
     */

    public void setCompanyMember ( CompanyMember companyMember ) { this.companyMember = companyMember; }

    /**
     *
     */

    public void setConfirmPassword ( String   user_confirm_password ) { this.user_confirm_password = user_confirm_password ; }
    public void setConfirmUsername ( String   user_confirm_username ) { this.user_confirm_username = user_confirm_username ; }
    public void setFullname        ( String   user_fullname         ) { this.user_fullname         = user_fullname         ; }
    public void setNewPassword     ( String   user_new_password     ) { this.user_new_password     = user_new_password     ; }
    public void setPhone           ( String   user_phone            ) { this.user_phone            = user_phone            ; }
    public void setRoles           ( String[] user_roles            ) { this.user_roles            = user_roles            ; }
    public void setIsAdministrator ( boolean  user_is_administrator ) { this.user_is_administrator = user_is_administrator ; }

    public void setOpenIdIdentifier ( String  openIdIdentifier ) { this.openIdIdentifier = openIdIdentifier ; }
    public void setStayLoggedIn     ( boolean stayLoggedIn     ) { this.stayLoggedIn     = stayLoggedIn     ; }

}
