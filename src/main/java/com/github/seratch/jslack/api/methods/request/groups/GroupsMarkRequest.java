package com.github.seratch.jslack.api.methods.request.groups;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupsMarkRequest {

    private String token;
    private String channel;
    private String ts;
}