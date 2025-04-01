package com.stumeet.server.member.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.member.adapter.in.web.response.MemberProfileResponse;
import com.stumeet.server.member.application.port.in.MemberProfileUseCase;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.member.application.port.in.command.MemberProfileCommand;
import com.stumeet.server.member.application.port.in.command.MemberSignupCommand;
import com.stumeet.server.member.application.port.in.command.MemberUpdateCommand;
import com.stumeet.server.member.application.port.in.mapper.MemberUseCaseMapper;
import com.stumeet.server.member.application.port.out.MemberCommandPort;
import com.stumeet.server.member.application.port.out.MemberQueryPort;
import com.stumeet.server.member.domain.Member;
import com.stumeet.server.profession.application.port.in.ProfessionQueryUseCase;
import com.stumeet.server.profession.domain.Profession;
import com.stumeet.server.studymember.application.port.in.MemberGrapeQueryUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class MemberProfileService implements MemberProfileUseCase {

    private final ProfessionQueryUseCase professionQueryUseCase;
    private final FileUploadUseCase fileUploadUseCase;
    private final MemberValidationUseCase memberValidationUseCase;
    private final MemberGrapeQueryUseCase memberGrapeQueryUseCase;

    private final MemberCommandPort memberCommandPort;
    private final MemberQueryPort memberQueryPort;

    private final MemberUseCaseMapper memberUseCaseMapper;


    @Override
    public void signup(Member member, MemberSignupCommand request) {
        memberValidationUseCase.validateNickname(request.nickname());

        Profession profession = professionQueryUseCase.getById(request.profession());
        String url = fileUploadUseCase.uploadUserProfileImage(member.getId(), request.image()).url();

        MemberProfileCommand command = MemberProfileCommand.builder()
                .profession(profession)
                .url(url)
                .nickname(request.nickname())
                .region(request.region())
                .build();

        member.signup(command);

        memberCommandPort.save(member);
    }

    @Override
    public void updateProfile(Member member, MemberUpdateCommand request) {
        Profession profession = request.profession() != null ?
                professionQueryUseCase.getById(request.profession()) :
                null;

        String url = request.image() != null ?
                fileUploadUseCase.uploadUserProfileImage(member.getId(), request.image()).url() :
                null;

        MemberProfileCommand command = MemberProfileCommand.builder()
                .profession(profession)
                .url(url)
                .nickname(request.nickname())
                .region(request.region())
                .build();

        member.updateProfile(command);

        memberCommandPort.update(member);
    }

    @Override
    public MemberProfileResponse getProfileById(Long id) {
        Member member = memberQueryPort.getById(id);
        int grapeCount = memberGrapeQueryUseCase.countMemberGrape(id);

        return memberUseCaseMapper.toProfileResponse(member, grapeCount);
    }
}
