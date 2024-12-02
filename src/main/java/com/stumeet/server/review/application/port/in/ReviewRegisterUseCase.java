package com.stumeet.server.review.application.port.in;

import com.stumeet.server.review.application.port.in.command.ReviewRegisterCommand;

public interface ReviewRegisterUseCase {

    void register(ReviewRegisterCommand command);
}
