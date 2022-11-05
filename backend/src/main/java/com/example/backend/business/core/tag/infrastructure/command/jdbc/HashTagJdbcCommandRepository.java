package com.example.backend.business.core.tag.infrastructure.command.jdbc;

import com.example.backend.business.web.tag.presentation.dto.response.HashTagResponse;
import com.example.backend.business.web.tag.presentation.dto.response.StudyHashTagResponse;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.backend.business.core.tag.infrastructure.command.queryutils.HashTagQuerySupport.HASH_TAG_ID;
import static com.example.backend.business.core.tag.infrastructure.command.queryutils.HashTagQuerySupport.STUDY_ID;
import static com.example.backend.business.core.tag.infrastructure.command.queryutils.HashTagQuerySupport.TAG_NAME;

@Repository
public class HashTagJdbcCommandRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public HashTagJdbcCommandRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Deprecated(since = "StudyHashTag가 필드로 HashTagId 대신 엔티티를 가지도록 리팩토링")
    public List<StudyHashTagResponse> findStudyHashTagsByIdsIn(List<Long> studyIds) {
        SqlParameterSource parameters = new MapSqlParameterSource("ids", studyIds);
        return namedParameterJdbcTemplate.query("SELECT sh.hash_tag_id, sh.study_id, t.tag_name FROM study_hash_tag AS sh JOIN hash_tag AS t ON t.hash_tag_id = sh.hash_tag_id WHERE sh.study_id IN (:ids) ORDER BY t.hash_tag_id",
                parameters, (rs, rowNum) -> new StudyHashTagResponse(
                        rs.getLong(HASH_TAG_ID),
                        rs.getLong(STUDY_ID),
                        rs.getString(TAG_NAME)));
    }

    @Deprecated(since = "StudyHashTag가 필드로 HashTagId 대신 엔티티를 가지도록 리팩토링")
    public List<HashTagResponse> findHashTagsByStudyId(Long studyId) {
        SqlParameterSource parameters = new MapSqlParameterSource("studyId", studyId);
        return namedParameterJdbcTemplate.query("SELECT ht.hash_tag_id, ht.tag_name FROM hash_tag AS ht LEFT JOIN study_hash_tag sh ON sh.hash_tag_id = ht.hash_tag_id WHERE sh.study_id=:studyId ORDER BY ht.hash_tag_id",
                parameters, (rs, rowNum) -> new HashTagResponse(
                        rs.getLong(HASH_TAG_ID),
                        rs.getString(TAG_NAME)));
    }
}
