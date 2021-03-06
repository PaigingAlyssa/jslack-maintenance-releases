package com.github.seratch.jslack.lightning.request.builtin;

import com.github.seratch.jslack.common.json.GsonFactory;
import com.github.seratch.jslack.lightning.context.builtin.DefaultContext;
import com.github.seratch.jslack.lightning.request.Request;
import com.github.seratch.jslack.lightning.request.RequestHeaders;
import com.github.seratch.jslack.lightning.request.RequestType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.ToString;

@ToString(callSuper = true)
public class UrlVerificationRequest extends Request<DefaultContext> {

    private final String requestBody;
    private final RequestHeaders headers;
    private final String challenge;

    public UrlVerificationRequest(
            String requestBody,
            RequestHeaders headers) {
        this.requestBody = requestBody;
        this.headers = headers;
        JsonObject payload = GsonFactory.createSnakeCase().fromJson(requestBody, JsonElement.class).getAsJsonObject();
        this.challenge = payload.get("challenge").getAsString();
    }

    private DefaultContext context = new DefaultContext();

    @Override
    public DefaultContext getContext() {
        return context;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.UrlVerification;
    }

    @Override
    public String getRequestBodyAsString() {
        return requestBody;
    }

    @Override
    public RequestHeaders getHeaders() {
        return this.headers;
    }

    public String getChallenge() {
        return this.challenge;
    }
}
