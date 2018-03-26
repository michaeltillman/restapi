package com.serve.openstack.extendedservice.health;

import com.codahale.metrics.health.HealthCheck;
import com.serve.openstack.extendedservice.RestAPIConfiguration;

/**
 * Created by mtillman on 3/2/15.
 */
public class RestAPIHealthCheck extends HealthCheck {

    final private RestAPIConfiguration config;

    public RestAPIHealthCheck(RestAPIConfiguration config) {
        this.config = config;

    }

    @Override
    protected Result check() throws Exception {
        final String sourcePassword = config.getSourcePassword();
        final String targetPassword = config.getTargetPassword();

        if (sourcePassword.contains("{password}")) {
            return Result.unhealthy("The password of the Tenant for the source OpenStack must be set.");
        }
        if (targetPassword.contains("{password}")) {
            return Result.unhealthy("The password of the Tenant for the target OpenStack must be set.");
        }
        return Result.healthy();
    }
}
