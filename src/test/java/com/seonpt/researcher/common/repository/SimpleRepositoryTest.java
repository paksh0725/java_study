package com.seonpt.researcher.common.repository;

import com.seonpt.researcher.common.entity.SimpleUser;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Import(SimpleRepository.class)
@Transactional
class SimpleRepositoryTest {

    @Autowired
    private SimpleRepository simpleRepository;

    @AfterEach
    void tearDown() {
        simpleRepository.deleteAll();
    }

    @Test
    @DisplayName("Simple 객체를 저장하고 조회할 수 있다.")
    public void saveFindAllTest(){

        String givenName = "givenName";
        SimpleUser user = new SimpleUser(givenName);

        simpleRepository.insert(user);

        List<SimpleUser> actual = simpleRepository.findAll();

        assertThat(actual).hasSize(1);
        assertThat(actual).extracting( "name")
                .containsExactly(givenName);

    }

    @Test
    @DisplayName("Simple 객체를 저장하고 ID로 조회할 수 있다.")
    public void saveFindIdTest() throws Exception{

        //given
        String givenName = "givenName";
        SimpleUser user = new SimpleUser(givenName);
        simpleRepository.insert(user);

        //when
        SimpleUser actual = simpleRepository.findById(1L);

        //then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getName()).isEqualTo(givenName);

    }



}