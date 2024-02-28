package com.stumeet.server.profession.adapter.in.web;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.profession.adapter.in.web.response.ProfessionResponses;
import com.stumeet.server.profession.application.port.in.ProfessionQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping("/api/v1/professions")
@RequiredArgsConstructor
public class ProfessionQueryApi {

    private final ProfessionQueryUseCase professionQueryUseCase;

    @GetMapping
    public ResponseEntity<ApiResponse<ProfessionResponses>> getProfessions() {
        ProfessionResponses response = professionQueryUseCase.findAll();

        return new ResponseEntity<>(
                ApiResponse.success(HttpStatus.OK.value(), "분야 정보 조회에 성공했습니다.", response),
                HttpStatus.OK
        );
    }
}
