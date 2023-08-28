package com.movte.slate.domain.user.application.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final KakaoService kakaoService;

}
