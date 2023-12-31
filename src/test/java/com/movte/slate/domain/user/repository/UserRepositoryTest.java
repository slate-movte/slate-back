package com.movte.slate.domain.user.repository;

import com.movte.slate.support.IntegrationTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class UserRepositoryTest extends IntegrationTestSupport {


    @Autowired
    UserRepository userRepository;

    /// 통합 테스트 샘플용 코드
    @Test
    @DisplayName("유저를 생성하면 식별자를 자동으로 생성해서 리턴한다.")
    public void createUserTest() throws Exception {
//        //given
//        User user = new User("닉네임");
//
//        //when
//        User savedUser = userRepository.save(user);
//
//        //then
//        assertThat(savedUser.getId()).isNotNull();
//        assertThat(savedUser.getNickname()).isEqualTo("닉네임");


    }
}