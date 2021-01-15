package com.module.screencmd.parser.app_parser;

import com.module.screencmd.parser.IAppRecordParser;
import com.module.screencmd.pojo.app_enum.AppOnOffArg;
import com.module.screencmd.pojo.AppCmdRecordPojo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class AppRecordParseOnOffArg implements IAppRecordParser {

    @Override
    public String[] parseAppRecord(String[] args, AppCmdRecordPojo appRecordPojo) {
        List<String> filterArgs = new ArrayList();

        for(int i = 0; i < args.length; i++){
            AppOnOffArg appOnOffArg;
            if((appOnOffArg = AppOnOffArg.getMatchOnOffArgs(args[i])) != null){
                appRecordPojo.setAppOnOffArg(appOnOffArg);
            } else {
                filterArgs.add(args[i]);
            }
        }
        String[] filterArgArray = new String[filterArgs.size()];
        return filterArgs.toArray(filterArgArray);
    }

}
