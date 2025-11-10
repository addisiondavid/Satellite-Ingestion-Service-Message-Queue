package com.example.satellite_api_service.service.Impl;

//import satellite_api_service.domain.dto.SatRecord;
import com.example.commondto.dto.SatRecord;
import com.example.satellite_api_service.service.ISatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SatServiceImpl implements ISatService {
    public List<SatRecord> latest(){
        Instant ts = Instant.now().truncatedTo(ChronoUnit.MINUTES);
        return List.of(
                new SatRecord(100.0, 20.0, Math.random()*50, ts),
                new SatRecord(100.5, 20.2, Math.random()*50, ts),
                new SatRecord(101.0, 20.1, Math.random()*50, ts)
        );
    };
}
