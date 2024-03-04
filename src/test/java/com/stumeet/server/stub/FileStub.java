package com.stumeet.server.stub;

import com.stumeet.server.file.application.port.out.FileUrl;

public class FileStub {
    private FileStub() {

    }


    public static FileUrl getFileUrl() {
        return new FileUrl("http://localhost:4572/user/1/profile/2024030416531039839905-b7e8-4ad3-9552-7d9cbc01cb14-test.jpg");
    }
}
