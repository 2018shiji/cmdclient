package com.module.screencmd.parser.app_parser;

import com.module.screencmd.parser.IAppRecordParser;
import com.module.screencmd.pojo.app_enum.AppBootstrapOption;
import com.module.screencmd.pojo.AppCmdRecordPojo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class AppRecordParseOption implements IAppRecordParser {
    @Override
    public String[] parseAppRecord(String[] args, AppCmdRecordPojo appRecordPojo) {
        List<String> filterArgs = new ArrayList<>();
        List<AppBootstrapOption> appOptions = new ArrayList<>();

        for(int i = 0; i < args.length; i++){
            AppBootstrapOption appOption;
            if((appOption = AppBootstrapOption.getMatchOption(args[i])) != null) {
                appOptions.add(appOption);
            } else {
                filterArgs.add(args[i]);
            }
        }
        appRecordPojo.setAppOptions(appOptions);
        String[] filterArgArray = new String[filterArgs.size()];

        return filterArgs.toArray(filterArgArray);
    }
}
