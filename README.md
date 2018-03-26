# Openstack Utility Rest API

This is a REST API application that makes certain openstack tasks easy to perform.

## Features

### Copy

This is to copy an image file from one glance storage to another. Use case is to promote an image from test openstack to production openstack.

| URL | /image/v1/copy |
|:----|:---------------|
| request body | { "devImageID":"UUID", "oSTargetTenant":"bakery" } |


## Questions?

Contact Michael Tillman (michael.tillman7@gmail.com)
