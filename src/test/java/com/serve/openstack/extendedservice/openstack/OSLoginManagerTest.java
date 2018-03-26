package com.serve.openstack.extendedservice.openstack;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by mtillman on 3/12/15.
 */
public class OSLoginManagerTest {

    @Test()
    public void testGetAuthenticatedToken() {

    }

    @Test()
    public void testGetCatalogEndpoint() {
        try {
            JSONObject json = new JSONObject("{\"access\": {\"token\": {\"issued_at\": \"2015-03-12T13:31:48.941398\", \"expires\": \"2015-03-12T14:31:48Z\", \"id\": \"e22c45e8525b4d2da2b9862c3fcae8c1\", \"tenant\": {\"description\": \"AsgardTeam\", \"enabled\": true, \"id\": \"6cc815646a3e4d659731433ed5b0eca4\", \"name\": \"Asgard\"}, \"audit_ids\": [\"9S10opBiRz-7ONQ4ndYuog\"]}, \"serviceCatalog\": [{\"endpoints\": [{\"adminURL\": \"http://10.115.27.20:8774/v2/6cc815646a3e4d659731433ed5b0eca4\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.27.20:8774/v2/6cc815646a3e4d659731433ed5b0eca4\", \"id\": \"51b714394ed84cafba7db5efce5db1a0\", \"publicURL\": \"http://10.115.26.157:8774/v2/6cc815646a3e4d659731433ed5b0eca4\"}], \"endpoints_links\": [], \"type\": \"compute\", \"name\": \"nova\"}, {\"endpoints\": [{\"adminURL\": \"http://10.115.27.20:9696/\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.27.20:9696/\", \"id\": \"03db37e7d5344bdc9fd0679b91df4efe\", \"publicURL\": \"http://10.115.26.157:9696/\"}], \"endpoints_links\": [], \"type\": \"network\", \"name\": \"neutron\"}, {\"endpoints\": [{\"adminURL\": \"http://10.115.27.20:8776/v2/6cc815646a3e4d659731433ed5b0eca4\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.27.20:8776/v2/6cc815646a3e4d659731433ed5b0eca4\", \"id\": \"46b17fe1bf004d4da6c841a4404b3f79\", \"publicURL\": \"http://10.115.26.157:8776/v2/6cc815646a3e4d659731433ed5b0eca4\"}], \"endpoints_links\": [], \"type\": \"volumev2\", \"name\": \"cinderv2\"}, {\"endpoints\": [{\"adminURL\": \"http://10.115.27.20:9292\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.27.20:9292\", \"id\": \"13582394519d413fa1cf13fc8e54b30f\", \"publicURL\": \"http://10.115.26.157:9292\"}], \"endpoints_links\": [], \"type\": \"image\", \"name\": \"glance\"}, {\"endpoints\": [{\"adminURL\": \"http://10.115.27.20:8777\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.27.20:8777\", \"id\": \"503b4e9758c34e94881fa927b7dba14b\", \"publicURL\": \"http://10.115.26.157:8777\"}], \"endpoints_links\": [], \"type\": \"metering\", \"name\": \"ceilometer\"}, {\"endpoints\": [{\"adminURL\": \"http://10.115.27.20:8000/v1/\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.27.20:8000/v1/\", \"id\": \"05cb9c3f54cf4ab28c643a27930a4fe9\", \"publicURL\": \"http://10.115.26.157:8000/v1/\"}], \"endpoints_links\": [], \"type\": \"cloudformation\", \"name\": \"heat-cfn\"}, {\"endpoints\": [{\"adminURL\": \"http://10.115.27.20:8776/v1/6cc815646a3e4d659731433ed5b0eca4\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.27.20:8776/v1/6cc815646a3e4d659731433ed5b0eca4\", \"id\": \"9d52e175b5df493cae75b82470cc1ec5\", \"publicURL\": \"http://10.115.26.157:8776/v1/6cc815646a3e4d659731433ed5b0eca4\"}], \"endpoints_links\": [], \"type\": \"volume\", \"name\": \"cinder\"}, {\"endpoints\": [{\"adminURL\": \"http://10.115.26.157:8082\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.26.157:8082\", \"id\": \"1c2910752ee64686bfda09713511615e\", \"publicURL\": \"http://10.115.26.157:8082\"}], \"endpoints_links\": [], \"type\": \"murano\", \"name\": \"muranoapi\"}, {\"endpoints\": [{\"adminURL\": \"http://10.115.27.20:8773/services/Admin\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.27.20:8773/services/Cloud\", \"id\": \"58079b98114e4aebaba8b706c9754871\", \"publicURL\": \"http://10.115.26.157:8773/services/Cloud\"}], \"endpoints_links\": [], \"type\": \"ec2\", \"name\": \"nova_ec2\"}, {\"endpoints\": [{\"adminURL\": \"http://10.115.27.20:8004/v1/6cc815646a3e4d659731433ed5b0eca4\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.27.20:8004/v1/6cc815646a3e4d659731433ed5b0eca4\", \"id\": \"2f891914f6214ca296d2ba2a68def372\", \"publicURL\": \"http://10.115.26.157:8004/v1/6cc815646a3e4d659731433ed5b0eca4\"}], \"endpoints_links\": [], \"type\": \"orchestration\", \"name\": \"heat\"}, {\"endpoints\": [{\"adminURL\": \"http://10.115.27.20:35357/v2.0\", \"region\": \"RegionOne\", \"internalURL\": \"http://10.115.27.20:5000/v2.0\", \"id\": \"0e424fe366054ba0a04036a0bb632ef3\", \"publicURL\": \"http://10.115.26.157:5000/v2.0\"}], \"endpoints_links\": [], \"type\": \"identity\", \"name\": \"keystone\"}], \"user\": {\"username\": \"asgard\", \"roles_links\": [], \"id\": \"9193f4e5e02d4d5c9a2d564587812699\", \"roles\": [{\"name\": \"heat_stack_owner\"}, {\"name\": \"_member_\"}, {\"name\": \"Member\"}], \"name\": \"asgard\"}, \"metadata\": {\"is_admin\": 0, \"roles\": [\"2cd7e92188c7483487a3c2fddbbf9f4a\", \"9fe2ff9ee4384b1894a90878d3e92bab\", \"8d5f0ec85d4048f5b82a6b7d33be5b68\"]}}}");
            JSONArray serviceCatalog = json.getJSONObject("access").getJSONArray("serviceCatalog");

            OSLoginManager login = new OSLoginManager();
            Assert.assertEquals(login.getCatalogEndpoint(OSLoginManager.CEILOMETER_SERVICE, serviceCatalog), "http://10.115.26.157:8777");
            Assert.assertEquals(login.getCatalogEndpoint(OSLoginManager.GLANCE_SERVICE, serviceCatalog), "http://10.115.26.157:9292");

        } catch (JSONException e) {

        }
    }

}
