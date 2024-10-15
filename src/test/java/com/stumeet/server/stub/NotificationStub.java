package com.stumeet.server.stub;

import com.stumeet.server.notification.adapter.out.web.dto.RenewNotificationTokenRequest;

public class NotificationStub {

    public static RenewNotificationTokenRequest getRenewNotificationTokenRequest() {
        return new RenewNotificationTokenRequest(
                "9774d56d682e549c",
                "eTwlLwiTPtM:APA91bH-0CjWQJ8p6DZHJ-LZiXkv2YKSCrGq9Z"
        );
    }

    public static RenewNotificationTokenRequest getInvalidRenewNotificationTokenRequest() {
        return new RenewNotificationTokenRequest(
                "",
                ""
        );
    }
}
