package com.boot.box.business;

import com.boot.box.business.FilmMakerDraft;

public interface FilmMakerDraftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilmMakerDraft record);

    int insertSelective(FilmMakerDraft record);

    FilmMakerDraft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FilmMakerDraft record);

    int updateByPrimaryKey(FilmMakerDraft record);
}