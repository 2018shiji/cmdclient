package com.module.screencmd.parser.app_parser;

import com.module.screencmd.parser.IAppRecordParser;
import com.module.screencmd.pojo.app_enum.AppCategory;
import com.module.screencmd.pojo.AppCmdRecordPojo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class AppRecordParseCategory implements IAppRecordParser {

    @Override
    public String[] parseAppRecord(String[] args, AppCmdRecordPojo appRecordPojo) {
        List<String> filerArgs = new ArrayList();
        for(int i = 0; i < args.length; i++){
            AppCategory appCategory;
            if((appCategory = AppCategory.getMatchCategory(args[i])) != null){
                appRecordPojo.setAppCategory(appCategory);
            } else {
                filerArgs.add(args[i]);
            }
        }
        String[] filterArgArray = new String[filerArgs.size()];
        return filerArgs.toArray(filterArgArray);
    }
}
