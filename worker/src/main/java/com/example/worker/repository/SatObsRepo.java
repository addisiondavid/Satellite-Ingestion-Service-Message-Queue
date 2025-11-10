package com.example.worker.repository;

import com.example.worker.entity.SatObs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface SatObsRepo extends JpaRepository<SatObs,Long> {

    @Query("""
        SELECT s FROM SatObs s
        WHERE ST_Within(s.geom, ST_MakeEnvelope(:minLon, :minLat, :maxLon, :maxLat, 4326)) = true
          AND s.ts >= :startTs AND s.ts < :endTs
    """)
    List<SatObs> findInBBoxAtTime(@Param("minLon") double minLon,
                                  @Param("minLat") double minLat,
                                  @Param("maxLon") double maxLon,
                                  @Param("maxLat") double maxLat,
                                  @Param("startTs") Instant startTs,
                                  @Param("endTs") Instant endTs);
}

