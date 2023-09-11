package com.movte.slate.domain.community.application.service;

import com.movte.slate.domain.community.application.port.SaveCommentPort;
import com.movte.slate.domain.community.application.port.FindFeedByIdPort;
import com.movte.slate.domain.community.application.request.AddCommentServiceRequest;
import com.movte.slate.domain.community.application.response.AddCommentServiceResponse;
import com.movte.slate.domain.community.application.usecase.AddCommentUseCase;
import com.movte.slate.domain.community.domain.Comment;
import com.movte.slate.domain.community.domain.Feed;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.domain.user.application.port.FindUserByIdPort;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddCommentService implements AddCommentUseCase {

    private final FindUserByIdPort findUserByIdPort;
    private final FindFeedByIdPort findFeedByIdPort;
    private final SaveCommentPort saveCommentPort;

    @Override
    public AddCommentServiceResponse addComment(Long userId, long feedId, AddCommentServiceRequest serviceRequest) {
        User user = findUserByIdPort.findById(userId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NOT_USER));
        Feed feed = findFeedByIdPort.findById(feedId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NO_RESOURCE));
        String content = serviceRequest.getContent();

        Comment comment = Comment.builder()
                .writer(user)
                .feed(feed)
                .content(content).build();
        comment = saveCommentPort.save(comment);
        return new AddCommentServiceResponse(comment);
    }
}
