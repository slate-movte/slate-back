package com.movte.slate.domain.user.application.port;

import java.util.Date;

public interface PutAccessTokenToBlackListTokenPort {

    void blackList(String accessTokenValue, Date expiredDateTime, Date now);
}
