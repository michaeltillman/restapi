package com.serve.openstack.extendedservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Wither;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.util.UUID;
/**
 * Created by mtillman on 4/9/15.
 */

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Wither
public class AccessToken {

    @JsonProperty("access_token_id")
    @NotNull
    private UUID accessTokenId;

    @JsonProperty("user_id")
    @NotNull
    private Long userId;

    @JsonProperty("last_access_utc")
    @NotNull
    private DateTime lastAccessUTC;

}
