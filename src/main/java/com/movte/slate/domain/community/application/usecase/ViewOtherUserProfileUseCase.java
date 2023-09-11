package com.movte.slate.domain.community.application.usecase;

import com.movte.slate.domain.community.application.response.ViewOtherUserProfileServiceResponse;

public interface ViewOtherUserProfileUseCase {

    ViewOtherUserProfileServiceResponse viewOtherUserProfile(long userId, long otherUserId, int feedCount);
}
