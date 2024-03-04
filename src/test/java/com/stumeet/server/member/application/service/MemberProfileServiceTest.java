package com.stumeet.server.member.application.service;

import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.member.application.port.in.command.MemberUpdateCommand;
import com.stumeet.server.member.application.port.out.MemberCommandPort;
import com.stumeet.server.member.domain.Member;
import com.stumeet.server.profession.application.port.in.ProfessionQueryUseCase;
import com.stumeet.server.stub.FileStub;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.ProfessionStub;
import com.stumeet.server.template.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


class MemberProfileServiceTest extends UnitTest {

    @InjectMocks
    private MemberProfileService memberProfileService;

    @Mock
    private MemberCommandPort memberCommandPort;

    @Mock
    private FileUploadUseCase fileUploadUseCase;

    @Mock
    private ProfessionQueryUseCase professionQueryUseCase;

    @Nested
    @DisplayName("멤버 프로필 업데이트")
    class UpdateProfile {

        @Test
        @DisplayName("[성공] 멤버 프로필 업데이트에 성공한다.")
        void successTest() {
            MemberUpdateCommand command = MemberStub.getMemberUpdateCommand();
            Member member = MemberStub.getMember();

            given(professionQueryUseCase.getById(command.profession()))
                    .willReturn(ProfessionStub.getProfession());
            given(fileUploadUseCase.uploadUserProfileImage(member.getId(), command.image()))
                    .willReturn(FileStub.getFileUrl());

            memberProfileService.updateProfile(member, command);

            then(memberCommandPort).should().update(member);
        }
    }

}