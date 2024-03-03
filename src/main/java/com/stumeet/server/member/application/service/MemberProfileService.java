package com.stumeet.server.member.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;
import com.stumeet.server.member.application.port.in.MemberProfileUseCase;
import com.stumeet.server.member.application.port.in.command.MemberProfileCommand;
import com.stumeet.server.member.application.port.in.command.MemberSignupCommand;
import com.stumeet.server.member.application.port.in.command.MemberUpdateCommand;
import com.stumeet.server.member.application.port.out.MemberCommandPort;
import com.stumeet.server.member.domain.Member;
import com.stumeet.server.profession.application.port.in.ProfessionQueryUseCase;
import com.stumeet.server.profession.domain.Profession;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class MemberProfileService implements MemberProfileUseCase {

    private final ProfessionQueryUseCase professionQueryUseCase;
    private final FileUploadUseCase fileUploadUseCase;
    private final MemberCommandPort memberCommandPort;

    @Override
    public void signup(Member member, MemberSignupCommand request) {
        Profession profession = professionQueryUseCase.getById(request.profession());
        String url = fileUploadUseCase.uploadUserProfileImage(member.getId(), request.image()).url();

        MemberProfileCommand command = MemberProfileCommand.builder()
                .profession(profession)
                .url(url)
                .nickname(request.nickname())
                .region(request.region())
                .build();

        registerProfile(member, command);
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

        registerProfile(member, command);
    }

    private void registerProfile(Member member, MemberProfileCommand command) {
        member.registerWithAdditionalDetails(command);

        memberCommandPort.save(member);
    }
}
