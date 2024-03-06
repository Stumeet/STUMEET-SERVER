package com.stumeet.server.member.application.service;

import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.member.adapter.in.web.response.MemberProfileResponse;
import com.stumeet.server.member.application.port.in.command.MemberUpdateCommand;
import com.stumeet.server.member.application.port.in.mapper.MemberUseCaseMapper;
import com.stumeet.server.member.application.port.out.MemberCommandPort;
import com.stumeet.server.member.application.port.out.MemberQueryPort;
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

import static org.assertj.core.api.Assertions.assertThat;
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

    @Mock
    private MemberQueryPort memberQueryPort;

    @Mock
    private MemberUseCaseMapper memberUseCaseMapper;

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

    @Nested
    @DisplayName("프로필 조회")
    class GetProfileById {
        @Test
        @DisplayName("[성공] 멤버 프로필 조회에 성공한다.")
        void successTest() {
            Member member = MemberStub.getMember();
            MemberProfileResponse want = MemberStub.getMemberProfileResponse(member);

            given(memberQueryPort.getById(member.getId()))
                    .willReturn(member);
            given(memberUseCaseMapper.toProfileResponse(member))
                    .willReturn(want);

            MemberProfileResponse got = memberProfileService.getProfileById(member.getId());

            assertThat(got).usingRecursiveComparison().isEqualTo(want);
        }
    }

}