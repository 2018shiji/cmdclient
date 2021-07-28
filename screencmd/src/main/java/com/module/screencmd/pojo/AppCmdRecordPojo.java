package com.module.screencmd.pojo;

import com.module.screencmd.pojo.app_enum.AppBootstrapOption;
import com.module.screencmd.pojo.app_enum.AppCategory;
import com.module.screencmd.pojo.app_enum.AppOnOffArg;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ToString
@Component
public class AppCmdRecordPojo {
    private AppOnOffArg appOnOffArg;
    private AppCategory appCategory;
    private List<AppBootstrapOption> appOptions;
}
