package com.module.screencmd.parser;

import com.module.screencmd.pojo.AppCmdRecordPojo;
import org.springframework.stereotype.Component;

@Component
public interface IAppRecordParser {
    String[] parseAppRecord(String[] args, AppCmdRecordPojo appRecordPojo);
}
