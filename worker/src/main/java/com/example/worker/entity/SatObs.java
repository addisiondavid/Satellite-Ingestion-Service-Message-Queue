package com.example.worker.entity;

import jakarta.persistence.*;

import java.time.Instant;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name="sat_obs")
public class SatObs {
    @Id
    @GeneratedValue
    private Long id;
    private double lon, lat, value;
    private Instant ts;
    @Column(columnDefinition = "geometry(Point,4326)")
    private Point geom;

    // ── Getters ──────────────────────────────────────────────
    public Long getId() {
        return id;
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

    // ── Setters ──────────────────────────────────────────────
    public void setId(Long id) {
        this.id = id;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTs(Instant ts) {
        this.ts = ts;
    }

    public Point getGeom() { return geom; }
    public void setGeom(Point geom) { this.geom = geom; }
}



