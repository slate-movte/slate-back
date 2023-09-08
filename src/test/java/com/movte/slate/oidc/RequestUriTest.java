package com.movte.slate.oidc;

import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.jwt.domain.RequestUri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RequestUriTest {


    @DisplayName("CanAccessWithoutAccessToken HAPPY CASE")
    @ParameterizedTest
    @CsvSource(value = {"/oidc/kakao,true", "/user/reissue,true", "/user,false"})
    void CanAccessWithoutAccessTokenHappyCase(String uriValue, boolean result) {
        // given
        RequestUri requestUri = new RequestUri(uriValue);
        // when

        // then
        assertThat(requestUri.canAccessWithoutAccessToken()).isEqualTo(result);
    }


    @Test
    @DisplayName("CanAccessWithoutAccessToken BAD CASE")
    void CanAccessWithoutAccessTokenBadCase() {
        // given
        RequestUri requestUri = new RequestUri(null);
        // when

        // then
        assertThatThrownBy(requestUri::canAccessWithoutAccessToken).isInstanceOf(ServerErrorException.class);
    }
}