package ca.jetsphere.core.tier2.backbone.user;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.security.hash.PasswordHash;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMember;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMemberYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
import ca.jetsphere.core.tier1.backbone.user.common.Status;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class UserEditAction extends AbstractEditAction
{
    /**
     *
     */

    protected void finito ( JDBC jdbc, HttpServletRequest request, Bolt bolt, boolean isUpdate, Errors errors ) throws Exception
    {
    User user = ( User ) bolt; Company company = CompanySession.getSelected ( request );

    CompanyMember companyMember = isUpdate ? CompanyMemberYard.getByUserId ( jdbc, user.getId() ) : new CompanyMember();

    companyMember.setUserId ( user.getId() ); companyMember.setCompanyId ( company.getId() ); companyMember.setAvatarId ( 1 );

    companyMember.save ( jdbc );
    }

    /**
     *
     */

    public String getKey() { return User.key(); }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors )throws Exception
    {
    User user = ( User ) bolt;

    validate ( jdbc, request, user, errors );
    }

    /**
     *
     */

    final protected void validate ( JDBC jdbc, HttpServletRequest request, User user, Errors errors ) throws Exception
    {
    User existing = UserYard.getByUsername ( jdbc, user.getUsername() );

    if ( DockYard.isWhiteSpace ( user.getUsername() ) ) { errors.add ( "error.username.required" ); return; }

    if ( !user.isValid() && existing.isValid() ) { errors.add ( "error.username.invalid" ); return; }

    if ( user.isValid() && existing.isValid() && user.getId() != existing.getId() ) { errors.add ( "error.username.invalid" ); return; }

    if ( DockYard.isWhiteSpace ( user.getNewPassword() ) && !user.isValid() ) { errors.add ( "error.password.required" ); return; }

    if ( DockYard.compareTo ( user.getNewPassword(), user.getConfirmPassword() ) != 0 ) { errors.add ( "error.password.change.nomatch" ); return; }

    if ( !user.isValid() || !DockYard.isWhiteSpace ( user.getNewPassword() ) ) user.setPassword ( PasswordHash.createHash ( user.getNewPassword() ) );

    if ( !user.isValid() ) user.setStatus ( Status.ACTIVE );
    }

}