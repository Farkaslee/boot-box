package com.boot.box.Fanuc;

import lombok.Data;

/**
 * @author lifangliang
 * @version 1.0
 * @date 2021/7/3 3:31 下午
 * @description
 */
@Data
public class WordResultItemSP {

    /**
     * 报警号
     */
    private String faultNo;

    /**
     * 信息
     */
    private String faultInfo;

    /**
     * 内容
     */
    private String faultContent;

    /**
     * 放大器显示*1
     */
    private String displayInfo;

    /**
     * 故障位置和处理办法
     */
    private String handleMethod;

}
