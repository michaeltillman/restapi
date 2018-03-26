######################################################
## 1. Set the active version in server to jdk 1.7
##
sudo rm /opt/java
sudo ln -s /usr/java/jdk1.7.0_71/ /opt/java
######################################################
## 2. Copy files under nginx and /etc/init.d
sudo cp /opt/asgardrestapi/asgardrestapi /etc/init.d/
sudo cp /opt/asgardrestapi/test.asgardapi.serve.com.crt /etc/nginx/conf.d/
sudo cp /opt/asgardrestapi/test.asgardapi.serve.com.key /etc/nginx/conf.d/
sudo cp /opt/asgardrestapi/asgardrestapi.conf /etc/nginx/conf.d/
######################################################
## 3. Start the nginx and asgardrestapi
## 
sudo service nginx stop
sudo service asgardrestapi start
sudo service nginx start
######################################################
## 4. setting nginx and asgardrestapi service to start on reboot or boot
sudo chkconfig --level 2345 asgardrestapi on
sudo chkconfig --level 2345 nginx on