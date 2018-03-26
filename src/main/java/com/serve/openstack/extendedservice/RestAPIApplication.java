package com.serve.openstack.extendedservice;

import com.serve.openstack.extendedservice.health.RestAPIHealthCheck;
import com.serve.openstack.extendedservice.resources.ImageResourceV1;
import com.serve.openstack.extendedservice.resources.PromotionResourceV1;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


/**
 * Created by mtillman on 3/2/15.
 */
public class RestAPIApplication extends Application<RestAPIConfiguration> {

    public static void main(String[] args) throws Exception {
        new RestAPIApplication().run(args);
    }

    /*
    * String srcUser, String srcPassword, String srcKeyUrl, String srcGlanceUrl,
                               String sourceTenant, String targetUser, String targetPassword,
                               String targetKeyUrl, String targetGlanceUrl
    * */

    @Override
    public void run(RestAPIConfiguration configuration, Environment env) {

        final String clientUser = configuration.getApiClientUser();
        final String clientPassword = configuration.getApiClientPassword();

        final PromotionResourceV1 resource = new PromotionResourceV1(
                configuration.getSourceUser(),
                configuration.getSourcePassword(),
                configuration.getSourceKeyUrl(),
                configuration.getSourceGlanceUrl(),
                configuration.getSourceTenant(),
                configuration.getTargetUser(),
                configuration.getTargetPassword(),
                configuration.getTargetKeyUrl(),
                configuration.getTargetGlanceUrl(),
                configuration.getTargetTenantsUrl()
        );

        final ImageResourceV1 imageResource = new ImageResourceV1(
                configuration.getSourceUser(),
                configuration.getSourcePassword(),
                configuration.getSourceKeyUrl(),
                configuration.getSourceTenant()
        );

        final RestAPIHealthCheck healthCheck =
                new RestAPIHealthCheck(configuration);
        env.jersey().register(resource);
        env.healthChecks().register("Promotion Rest API", healthCheck);

       final RestAPIHealthCheck imageHealthCheck = new RestAPIHealthCheck(configuration);
        env.jersey().register(imageResource);
        env.healthChecks().register("Image Meta-data", imageHealthCheck);
    }

    @Override
    public String getName() {
        return "Image Promotion Rest API";
    }

    @Override
    public void initialize(Bootstrap<RestAPIConfiguration> bootstrap) {

    }

}
