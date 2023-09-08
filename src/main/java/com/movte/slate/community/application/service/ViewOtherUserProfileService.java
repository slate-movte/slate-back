package com.movte.slate.community.application.service;

import com.movte.slate.community.application.response.ViewotherUserProfileServiceResponse;
import com.movte.slate.community.application.usecase.ViewOtherUserProfileUseCase;
import org.springframework.stereotype.Service;

@Service
public class ViewOtherUserProfileService implements ViewOtherUserProfileUseCase {
    @Override
    public ViewotherUserProfileServiceResponse viewOtherUserProfile(long otherUserId) {
        return null;
    }
}
