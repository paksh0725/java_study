package com.seonpt.researcher.common.repository;

import com.seonpt.researcher.common.entity.SimpleUser;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@Import(SimpleRepository.class)
class SimpleRepositoryTest {

    @Autowired
    private SimpleRepository simpleRepository;

    @Test
    @DisplayName("Simple 객체를 저장하고 조회할 수 있다.")
    public void saveTest(){
        SimpleUser user = new SimpleUser(1L, "givenName");

        simpleRepository.insert(user);

        List<SimpleUser> actual = simpleRepository.findAll();

        assertThat(actual).hasSize(1);
        assertThat(actual).extracting("id", "name")
                .containsExactly(Tuple.tuple(1L, "givenName"));
    }


}