package io.github.opensabe.common.alive.client.message;


import io.github.opensabe.common.alive.client.message.enumeration.PushType;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MessageVo extends PushVo {
    public final String body;
    public final String id;
    public final long expiry;

    public MessageVo(String topic, String body, String id) {
        this(topic, body, id, (String)null, PushType.GROUP);
    }

    public MessageVo(String topic, String body, String id, String deviceId, PushType pushType) {
        this(topic, body, id, deviceId, pushType, 0L, (String)null);
    }

    public MessageVo(String topic, String body, String id, String deviceId, PushType pushType, long expiry, String accountId) {
        super(topic, deviceId, pushType, accountId);
        if (body == null) {
            throw new NullPointerException();
        } else {
            this.body = body;
            this.expiry = expiry;
            this.id = id;
        }
    }

    public MessageVo(String topic, String body, String id, String deviceId, PushType pushType, long expiry, String accountId, int requsetId) {
        super(topic, deviceId, pushType, accountId, requsetId);
        this.body = body;
        this.expiry = expiry;
        this.id = id;
    }

    public Publish buildPublish(int requestId, int productCode) {
        Publish builder = new Publish();
        builder.setRequestId(requestId);
        builder.setProductCode(productCode);
        builder.setTopic(this.topic);
        builder.setPushType(this.pushType);
        builder.setId(this.id);
        builder.setBody(Base64.getEncoder().encode(this.body.getBytes(StandardCharsets.UTF_8)));
        if (this.deviceId != null && !this.deviceId.isEmpty()) {
            builder.setDeviceId(this.deviceId);
        }
        builder.setExpiry(this.expiry);
        if (this.accountId != null && !this.accountId.isEmpty()) {
            builder.setAccountId(this.accountId);
        }

        return builder;
    }

    public static void main(String[] args) {
        (new MessageVo("aa", "", "")).buildPublish(1, 1);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("topic: ").append(this.topic);
        sb.append(" ,deviceId: ").append(this.deviceId);
        sb.append(" ,pushType: ").append(this.pushType);
        sb.append(" ,account: ").append(this.accountId);
        sb.append(" ,requestId: ").append(this.requestId);
        return sb.toString();
    }
}