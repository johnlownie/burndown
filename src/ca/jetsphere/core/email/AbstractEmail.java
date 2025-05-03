package ca.jetsphere.core.email;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class AbstractEmail
{
    JDBC jdbc; HttpServletRequest request; Errors errors;

}
