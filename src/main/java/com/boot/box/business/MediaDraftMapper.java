package com.boot.box.business;

import com.boot.box.business.MediaDraft;

public interface MediaDraftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MediaDraft record);

    int insertSelective(MediaDraft record);

    MediaDraft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MediaDraft record);

    int updateByPrimaryKey(MediaDraft record);
}