package com.boot.box.business;

import java.util.Date;
import lombok.Data;

@Data
public class FilmMakerDraft {
    /**
     * 主键自增id
     */
    private Integer id;

    /**
     * 1-腾讯、2-爱奇艺、3-优酷、4-西瓜视频、5-豆瓣 0未知
     */
    private Byte dataSourcePlatform;

    /**
     * 来源影人对应平台的id
     */
    private String sourceFilmMakerId;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 明星中文名字
     */
    private String cnName;

    /**
     * 明星英文名字
     */
    private String enName;

    /**
     * 其他中文名字
     */
    private String otherCnName;

    /**
     * 其他英文名字
     */
    private String otherEnName;

    /**
     * 简介
     */
    private String description;

    /**
     * 1男 2女 3未知
     */
    private Byte sex;

    /**
     * 出生日期yyyy-MM-dd
     */
    private String birthday;

    /**
     * 出生地：用,分隔
     */
    private String birthplace;

    /**
     * 出生国家
     */
    private String birthCountry;

    /**
     * 星座
     */
    private String starSign;

    /**
     * 职业以/分隔
     */
    private String profession;

    /**
     * 明星头像链接
     */
    private String avatar;

    /**
     * 家庭成员
     */
    private String family;

    /**
     * 获奖情况
     */
    private String awards;

    /**
     * 数据来源1.爬虫，2.手动维护
     */
    private String dataSource;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date mtime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dataSourcePlatform=").append(dataSourcePlatform);
        sb.append(", sourceFilmMakerId=").append(sourceFilmMakerId);
        sb.append(", name=").append(name);
        sb.append(", cnName=").append(cnName);
        sb.append(", enName=").append(enName);
        sb.append(", otherCnName=").append(otherCnName);
        sb.append(", otherEnName=").append(otherEnName);
        sb.append(", description=").append(description);
        sb.append(", sex=").append(sex);
        sb.append(", birthday=").append(birthday);
        sb.append(", birthplace=").append(birthplace);
        sb.append(", birthCountry=").append(birthCountry);
        sb.append(", starSign=").append(starSign);
        sb.append(", profession=").append(profession);
        sb.append(", avatar=").append(avatar);
        sb.append(", family=").append(family);
        sb.append(", awards=").append(awards);
        sb.append(", dataSource=").append(dataSource);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", version=").append(version);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}