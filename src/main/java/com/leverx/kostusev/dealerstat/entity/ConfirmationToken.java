package com.leverx.kostusev.dealerstat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.concurrent.TimeUnit;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "confirmation_token")
public class ConfirmationToken implements BaseEntity<Long> {

    @TimeToLive(unit = TimeUnit.DAYS)
    private static final long expiration = 1;

    @Id
    private Long id;

    @Indexed
    private String externalId;

    private User user;
}
