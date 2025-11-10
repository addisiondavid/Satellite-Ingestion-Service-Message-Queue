package com.example.commondto.dto;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

//@Data
//@Getter
//@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
//@AllArgsConstructor
public class SatRecord implements Serializable {
    private double lon;
    private double lat;
    private double value;
    private Instant ts;

    public SatRecord(double lon, double lat, double value, Instant ts) {
        this.lon = lon;
        this.lat = lat;
        this.value = value;
        this.ts = ts;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public double getValue() {
        return value;
    }

    public Instant getTs() {
        return ts;
    }


}

