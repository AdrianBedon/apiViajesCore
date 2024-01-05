package com.arbc.development.mvc.services;

import java.util.List;

import com.arbc.development.mvc.models.dto.PeakSeasonDto;

public interface PeakSeasonService {
    List<PeakSeasonDto> findAll();
}
