package com.stumeet.server.stub;

import com.stumeet.server.studymember.adapter.in.web.dto.GrapeSendRequest;

public class GrapeStub {

    public static GrapeSendRequest getGrapeSendRequest() {
        return GrapeSendRequest.builder()
            .studyMemberId(4L)
            .complimentType("DILIGENT")
            .build();
    }

    public static GrapeSendRequest getInvalidComplimentTypeGrapeSendRequest() {
        return GrapeSendRequest.builder()
            .studyMemberId(4L)
            .complimentType("HELLO")
            .build();
    }

    public static GrapeSendRequest getNotExistStudyMemberGrapeSendRequest() {
        return GrapeSendRequest.builder()
            .studyMemberId(0L)
            .complimentType("OUTSTANDING")
            .build();
    }
}
