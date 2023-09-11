package com.movte.slate.community.application.usecase;

import com.movte.slate.community.application.response.ViewOtherUserProfileServiceResponse;

public interface ViewOtherUserProfileUseCase {

    ViewOtherUserProfileServiceResponse viewOtherUserProfile(long userId, long otherUserId, int feedCount);
}
