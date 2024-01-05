package com.arbc.development.mvc.services;

import java.util.List;

import com.arbc.development.mvc.models.dto.OffSeasonDto;

public interface OffSeasonService {
    List<OffSeasonDto> findAll();
}
