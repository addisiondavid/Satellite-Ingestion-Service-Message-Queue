package com.example.worker.listener;

import com.example.commondto.dto.SatRecord;
import com.example.commondto.dto.TileSeed;
import com.example.worker.entity.SatObs;
import com.example.worker.repository.SatObsRepo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

@Component
public class SatListener {
    private final SatObsRepo repo;
    private final AmqpTemplate amqp;

    public SatListener(SatObsRepo repo, AmqpTemplate amqp) {

        this.repo = repo;
        this.amqp = amqp;
    }

    @RabbitListener(queues = "sat.records")
    public void handle(SatRecord msg) {

        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);
        Point point = gf.createPoint(new Coordinate(msg.getLon(), msg.getLat()));

        // save to postGIS
        SatObs o = new SatObs();
        o.setLon(msg.getLon());
        o.setLat(msg.getLat());
        o.setValue(msg.getValue());
        o.setTs(msg.getTs());
        o.setGeom(point);
        repo.save(o);

        // send to downstream tile-seed
        double buffer = 0.1;  // ~10km tile
        TileSeed tile = new TileSeed(
                o.getLon() - buffer,
                o.getLat() - buffer,
                o.getLon() + buffer,
                o.getLat() + buffer,
                10, // zoom level
                o.getTs()
        );

        amqp.convertAndSend("tile.seed", tile);
        System.out.println(" Published tile.seed for bbox: " + tile);



    }
}
