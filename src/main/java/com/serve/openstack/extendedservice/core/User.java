package com.serve.openstack.extendedservice.core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by mtillman on 4/9/15.
 */

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private Long id;
    private String username;
    private String password;
}
