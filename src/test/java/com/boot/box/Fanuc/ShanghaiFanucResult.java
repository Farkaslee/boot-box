package com.boot.box.Fanuc;

import lombok.Data;

import java.util.List;

/**
 * @author lifangliang
 * @version 1.0
 * @date 2021/7/1 11:05 下午
 * @description
 */
@Data
public class ShanghaiFanucResult {
    private Integer code;

    private String msg;
    private String mark;
    private String tag;
    private String access_token;
    private List<ResultItem> temp;
}
