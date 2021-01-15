package com.module.screencmd.parser;

import com.module.screencmd.pojo.CtrlCmdRecordPojo;

public interface ICtrlRecordParser {
    String[] parseAppRecord(String[] args, CtrlCmdRecordPojo ctrlRecordPojo);
}
