package com.leverx.kostusev.dealerstat.service;

import com.leverx.kostusev.dealerstat.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.OptionalDouble;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql(value = {
        "classpath:drop_test_table.sql",
        "classpath:create_test_table",
        "classpath:insert_values_into_test_table.sql"
})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getTradersWithRatings() {
        Map<UserDto, Double> tradersWithRatings = userService.getTradersWithRatings();
        assertThat(tradersWithRatings.size(), is(2));
        assertThat(tradersWithRatings, hasValue(7.5));
    }

    @Test
    public void getTraderRating() {
        OptionalDouble unratedUser = userService.getTraderRating(1L);
        OptionalDouble ratedUser = userService.getTraderRating(2L);
        assertFalse(unratedUser.isPresent());
        assertTrue(ratedUser.isPresent());
    }
}
