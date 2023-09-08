package com.movte.slate.community.application.usecase;

import com.movte.slate.community.application.response.ViewotherUserProfileServiceResponse;

public interface ViewOtherUserProfileUseCase {
    ViewotherUserProfileServiceResponse viewOtherUserProfile(long otherUserId);
}
