package com.serve.openstack.extendedservice.auth;

import com.google.common.base.Optional;
import com.serve.openstack.extendedservice.core.AccessToken;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.UUID;

/**
 * Created by mtillman on 4/9/15.
 */
public class SimpleAuthenticator implements Authenticator<String, Long> {

    public static final int ACCESS_TOKEN_EXPIRE_TIME_MIN = 20;

    @Override
    public Optional<Long> authenticate(String accessTokenId) throws AuthenticationException {

        UUID accessTokenUUID;
        try {
            accessTokenUUID = UUID.fromString(accessTokenId);
        } catch (IllegalArgumentException e) {
            return Optional.absent();
        }

        return Optional.of(new Long(1));

    }

}
