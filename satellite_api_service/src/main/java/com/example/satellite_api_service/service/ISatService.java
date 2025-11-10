package com.example.satellite_api_service.service;

//import satellite_api_service.domain.dto.SatRecord;

import com.example.commondto.dto.SatRecord;

import java.util.List;

public interface ISatService {
    List<SatRecord> latest();
}
