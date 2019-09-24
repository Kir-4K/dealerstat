package com.leverx.kostusev.dealerstat.repository;

import com.leverx.kostusev.dealerstat.entity.Role;
import com.leverx.kostusev.dealerstat.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql(value = {
        "classpath:drop_test_table.sql",
        "classpath:create_test_table",
        "classpath:insert_values_into_test_table.sql"
})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindById() {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(value -> assertThat(value.getEmail(), equalTo("admin@mail.com")));
    }

    @Test
    public void findByEmail() {
        Optional<User> user = userRepository.findByEmail("admin@mail.com");
        user.ifPresent(value -> assertThat(value.getEmail(), equalTo("admin@mail.com")));
    }

    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll();
        List<String> list = users.stream().map(User::getFirstName).collect(toList());
        assertThat(users, hasSize(3));
        assertThat(list, containsInAnyOrder("Админус", "Анна", "Алексей"));
    }

    @Test
    public void testSave() {
        User save = User.builder()
                .firstName("Иван")
                .lastName("Иванович")
                .password("ivan")
                .email("ivan@mail.com")
                .createdAt(LocalDateTime.now())
                .role(Role.TRADER)
                .build();
        userRepository.save(save);

        Optional<User> user = userRepository.findById(save.getId());
        user.ifPresent(value -> assertThat(value.getEmail(), equalTo("ivan@mail.com")));
    }

    @Test
    public void testUpdate() {
        Optional<User> update = userRepository.findById(1L);
        if (update.isPresent()) {
            update.get().setEmail("somemail@mail.com");
            userRepository.save(update.get());
        }

        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(value -> assertThat(value.getEmail(), equalTo("somemail@mail.com")));
    }
}
