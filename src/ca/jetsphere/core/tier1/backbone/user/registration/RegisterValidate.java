package ca.jetsphere.core.tier1.backbone.user.registration;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier1.system.knock.Knock;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.security.google.VerifyRecaptcha;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
import ca.jetsphere.core.tier2.common.Errors;

/**
 *
 */

public class RegisterValidate
{
    /**
     *
     */

    protected void collision ( JDBC jdbc, User applicant, Errors errors )
    {
    if ( applicant.isValid() ) return;

    User x = UserYard.getByUsername ( jdbc, applicant.getUsername() );

    if ( x.isValid() ) errors.add ( "username", "error.registration.username.invalid" );
    }

    /**
     *
     */

    protected void confirm ( User applicant, Errors errors )
    {
    if ( DockYard.compareTo ( applicant.getNewPassword(), applicant.getConfirmPassword() ) != 0 )

    errors.add ( "confirmPassword", "error.registration.confirm.match" );
    }

    /**
     *
     */

    protected void mandatory ( User applicant, Errors errors )
    {
    if ( DockYard.isWhiteSpace ( applicant.getFullname   () ) ) errors.add ( "fullname", "error.registration.fullname.required" );

    if ( DockYard.isWhiteSpace ( applicant.getUsername   () ) ) errors.add ( "username", "error.registration.username.required" );

    if ( DockYard.isWhiteSpace ( applicant.getNewPassword() ) ) errors.add ( "password", "error.registration.password.required" );

    if ( DockYard.isWhiteSpace ( applicant.getPhone      () ) ) errors.add ( "phone", "error.registration.phone.required" );
    }

    /**
     *
     */

    protected void recaptcha ( String response, Errors errors )
    {
    boolean valid = VerifyRecaptcha.verify ( response );

    if ( !valid ) { Common.trace ( "Invalid Captcha" ); errors.add ( "captchaContainer", "error.registration.recaptcha.invalid" ); }
    }

    /**
     *
     */

    public Errors validate ( JDBC jdbc, User applicant, String response, Errors errors )
    {
    mandatory ( applicant, errors ); if ( ! errors.isEmpty() ) return errors;

    if ( ! DockYard.toBoolean ( Knock.get ( "DEVELOPER" ) ) ) { recaptcha ( response, errors ); if ( ! errors.isEmpty() ) return errors; }

    collision ( jdbc, applicant, errors ); if ( ! errors.isEmpty() ) return errors;

    confirm ( applicant, errors ); if ( ! errors.isEmpty() ) return errors;

    return errors;
    }
}
