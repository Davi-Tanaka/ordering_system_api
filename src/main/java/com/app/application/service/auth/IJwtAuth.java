
package com.app.application.service.auth;

import com.app.application.service.jwt.JwtTokens;

/**
 *
 * @author davi
 */
public interface IJwtAuth extends IAuth {
    public JwtTokens getAuthenticationTokens();
}
