package com.module.cmd.ping.logconfig;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApiResponse {
    private String status;
    private String description;
    private Object response;
}
