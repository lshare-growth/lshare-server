package com.example.backend.business.core.common;

import com.example.backend.common.mapper.api.EnumMapperType;

import java.util.Arrays;
import java.util.Objects;

public enum District implements EnumMapperType {

    SEOUL(11, "SEOUL", "서울"),
    BUSAN(21, "BUSAN", "부산"),
    DAEGU(22, "DAEGU", "대구"),
    INCHEON(23, "INCHEON", "인천"),
    DAEJEON(24, "DAEJEON", "대전"),
    ULSAN(25, "ULSAN", "울산"),
    NONE(100, "", "");

    private final int districtId;
    private final String type;
    private final String value;

    District(int districtId,
             String type,
             String value) {

        this.districtId = districtId;
        this.type = type;
        this.value = value;
    }

    public static District findDistrictByName(String district) {
        if (district == null) {
            return District.NONE;
        }
        return Arrays.stream(values())
                .filter(city -> city.type.equals(district))
                .findAny()
                .orElseGet(() -> NONE);
    }

    public District updateDistrict(District oldDistrict, District newDistrict) {
        if (Objects.isNull(newDistrict)) {
            return oldDistrict;
        }
        return newDistrict;
    }

    @Override
    public int getId() {
        return districtId;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }
}
