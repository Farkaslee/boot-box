package com.boot.box.Fanuc;

import lombok.Data;

/**
 * @author lifangliang
 * @version 1.0
 * @date 2021/7/1 11:06 下午
 * @description
 */
@Data
public class ResultItem {
    private String id;

    private String fault_no;
    private String fault_reason;
    private String fault_solution;
    private String createtime;

}
