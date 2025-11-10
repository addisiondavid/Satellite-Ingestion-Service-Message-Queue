package com.example.commondto.dto;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
public class TileSeed {
    double minLon;
    double minLat;
    double maxLon;
    double maxLat;
    int zoom;
    Instant timestamp;

    public TileSeed(double minLon, double minLat, double maxLon,double maxLat,int zoom, Instant ts) {
        this.minLon = minLon;
        this.minLat = minLat;
        this.maxLon = maxLon;
        this.maxLat = maxLat;
        this.zoom = zoom;
        this.timestamp = ts;
    }

    public double getMinLon() {
        return minLon;
    }

    public double getMinLat() {
        return minLat;
    }

    public double getMaxLon() {
        return maxLon;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public int getZoom() {
        return zoom;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
