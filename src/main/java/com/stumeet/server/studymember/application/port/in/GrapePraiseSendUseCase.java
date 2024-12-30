package com.stumeet.server.studymember.application.port.in;

import com.stumeet.server.studymember.application.port.in.command.GrapeSendCommand;

public interface GrapePraiseSendUseCase {

    void sendGrape(GrapeSendCommand command);
}
