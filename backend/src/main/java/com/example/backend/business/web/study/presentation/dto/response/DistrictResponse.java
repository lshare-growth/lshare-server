package com.example.backend.business.web.study.presentation.dto.response;

import com.example.backend.common.mapper.api.EnumMapperValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class DistrictResponse implements Serializable {

    private final List<EnumMapperValue> districts;

    private DistrictResponse(List<EnumMapperValue> districts) {
        this.districts = districts;
    }

    public static DistrictResponse of(List<EnumMapperValue> districts) {
        return new DistrictResponse(districts);
    }

    public List<EnumMapperValue> getDistricts() {
        return districts;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }
}
