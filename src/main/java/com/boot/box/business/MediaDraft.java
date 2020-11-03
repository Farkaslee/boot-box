package com.boot.box.business;

import java.util.Date;
import lombok.Data;

@Data
public class MediaDraft {
    /**
     * 主键自增id
     */
    private Integer id;

    /**
     * 1-腾讯、2-爱奇艺、3-优酷、4-西瓜视频、5-豆瓣 0未知
     */
    private Byte dataSourcePlatform;

    /**
     * 来源media对应平台的id
     */
    private String sourceMediaId;

    /**
     * 内部seasonId
     */
    private Integer seasonId;

    /**
     * 片名
     */
    private String title;

    /**
     * 别名
     */
    private String alias;

    /**
     * 1：番剧、2：电影、3：纪录片、4：国创、5：电视剧、7：综艺
     */
    private Byte seasonType;

    /**
     * 简介
     */
    private String description;

    /**
     * 风格以/分隔
     */
    private String styles;

    /**
     * 地区
     */
    private String areas;

    /**
     * 语言
     */
    private String mediaLanguage;

    /**
     * 豆瓣成员常用的标签 豆瓣成员常用的标签 
     */
    private String tags;

    /**
     * 上映年份
     */
    private String releaseYear;

    /**
     * 上映日期可能含有中文其他信息
     */
    private String releaseDate;

    /**
     * 出品方
     */
    private String producers;

    /**
     * 导演以/分隔
     */
    private String directors;

    /**
     * 编剧以/分隔
     */
    private String screenwriters;

    /**
     * 演员以/分隔
     */
    private String actors;

    /**
     * 导演对应平台ids以/分隔
     */
    private String directorsIds;

    /**
     * 编剧对应平台ids以/分隔
     */
    private String screenwritersIds;

    /**
     * 演员对应平台ids以/分隔
     */
    private String actorsIds;

    /**
     * 总集数
     */
    private Short total;

    /**
     * 时长
     */
    private String videoLength;

    /**
     * 获奖情况
     */
    private String award;

    /**
     * 评分
     */
    private Double rating;

    /**
     * 豆瓣评分人数
     */
    private Long ratingPeopleNum;

    /**
     * 短评数
     */
    private Long shortRatingNum;

    /**
     * 影评数
     */
    private Long reviewRatingNum;

    /**
     * 在看数
     */
    private Long watchingNum;

    /**
     * 看过
     */
    private Long watchedNum;

    /**
     * 想看
     */
    private Long wantNum;

    /**
     * 热度
     */
    private Long hotNum;

    /**
     * IMDB
     */
    private String imdb;

    /**
     * 角标
     */
    private String mark;

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
     * 是否删除 0 未删除 1 已删除
     */
    private Byte isDelete;

    /**
     * 演员对应角色以/分隔
     */
    private String roles;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dataSourcePlatform=").append(dataSourcePlatform);
        sb.append(", sourceMediaId=").append(sourceMediaId);
        sb.append(", seasonId=").append(seasonId);
        sb.append(", title=").append(title);
        sb.append(", alias=").append(alias);
        sb.append(", seasonType=").append(seasonType);
        sb.append(", description=").append(description);
        sb.append(", styles=").append(styles);
        sb.append(", areas=").append(areas);
        sb.append(", mediaLanguage=").append(mediaLanguage);
        sb.append(", tags=").append(tags);
        sb.append(", releaseYear=").append(releaseYear);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", producers=").append(producers);
        sb.append(", directors=").append(directors);
        sb.append(", screenwriters=").append(screenwriters);
        sb.append(", actors=").append(actors);
        sb.append(", directorsIds=").append(directorsIds);
        sb.append(", screenwritersIds=").append(screenwritersIds);
        sb.append(", actorsIds=").append(actorsIds);
        sb.append(", total=").append(total);
        sb.append(", videoLength=").append(videoLength);
        sb.append(", award=").append(award);
        sb.append(", rating=").append(rating);
        sb.append(", ratingPeopleNum=").append(ratingPeopleNum);
        sb.append(", shortRatingNum=").append(shortRatingNum);
        sb.append(", reviewRatingNum=").append(reviewRatingNum);
        sb.append(", watchingNum=").append(watchingNum);
        sb.append(", watchedNum=").append(watchedNum);
        sb.append(", wantNum=").append(wantNum);
        sb.append(", hotNum=").append(hotNum);
        sb.append(", imdb=").append(imdb);
        sb.append(", mark=").append(mark);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", version=").append(version);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", roles=").append(roles);
        sb.append("]");
        return sb.toString();
    }
}