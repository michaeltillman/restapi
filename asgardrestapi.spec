Name: asgardrestapi
Summary: Asgard Rest API application package
Version: 0.0.4
Release: 2%{?dist}
Requires: jdk nginx
Group: Applications
License: Copyright (c) American Express 2015
URL: http://serve.com


%description
Asgard Rest API application package


%prep


%build


%install
rm -rf $RPM_BUILD_ROOT
install -d -m 755 $RPM_BUILD_ROOT/opt/asgardrestapi/
cp ../SOURCES/dist-openstack-api-1.0.5.1.jar $RPM_BUILD_ROOT/opt/asgardrestapi/
cp ../SOURCES/RestAPIConfiguration.yml $RPM_BUILD_ROOT/opt/asgardrestapi/
cp ../SOURCES/test.asgardapi.serve.com.crt $RPM_BUILD_ROOT/opt/asgardrestapi/
cp ../SOURCES/test.asgardapi.serve.com.key $RPM_BUILD_ROOT/opt/asgardrestapi/
cp ../SOURCES/os-api.jks $RPM_BUILD_ROOT/opt/asgardrestapi/
cp ../SOURCES/asgardrestapi.conf $RPM_BUILD_ROOT/opt/asgardrestapi/
cp ../SOURCES/asgardrestapi $RPM_BUILD_ROOT/opt/asgardrestapi/
install -d m 755 $RPM_BUILD_ROOT/usr/local/bin
mkdir -p $RPM_BUILD_ROOT/usr/local/bin
cp ../SOURCES/asgardrestapi_post_install.sh $RPM_BUILD_ROOT/usr/local/bin/asgardrestapi_post_install.sh
%clean
rm -rf $RPM_BUILD_ROOT

%files
%attr(755, root, -) "/opt/asgardrestapi/dist-openstack-api-1.0.5.1.jar"
%attr(755, root, -) "/opt/asgardrestapi/RestAPIConfiguration.yml"
%attr(755, root, -) "/opt/asgardrestapi/test.asgardapi.serve.com.crt"
%attr(755, root, -) "/opt/asgardrestapi/test.asgardapi.serve.com.key"
%attr(755, root, -) "/opt/asgardrestapi/os-api.jks"
%attr(755, root, -) "/opt/asgardrestapi/asgardrestapi.conf"
%attr(755, root, -) "/opt/asgardrestapi/asgardrestapi"
%attr(755, root, -) "/usr/local/bin/asgardrestapi_post_install.sh"
#"/usr/local/bin/asgardrestapi_post_install.sh"

%post
/usr/local/bin/asgardrestapi_post_install.sh


%changelog
* Tue Aug 11 2015 Michael Tillman <michael.tillman7@gmail.com> 0.0.1-initial package build for the Asgard Rest API
* Tue Aug 18 2015 Michael Tillman <michael.tillman7@gmail.com> 0.0.3-add certs in the package
for the script
