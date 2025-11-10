package com.example.worker.listener;

import com.example.commondto.dto.TileSeed;
import com.example.worker.entity.SatObs;
import com.example.worker.renderer.TileRenderer;
import com.example.worker.repository.SatObsRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TileSeedListener {
    private final SatObsRepo repo;
    private final TileRenderer renderer;

    public TileSeedListener(SatObsRepo repo, TileRenderer renderer) {
        this.repo = repo;
        this.renderer = renderer;
    }

    @RabbitListener(queues = "tile.seed")
    public void handle(TileSeed msg) {
        List<SatObs> obsList = repo.findInBBoxAtTime(
                msg.getMinLon(), msg.getMinLat(),
                msg.getMaxLon(), msg.getMaxLat(),
                msg.getTimestamp(), msg.getTimestamp().plusSeconds(60)
        );

        String filename = "tiles/z" + msg.getZoom() + "/tile_" + msg.getTimestamp().toEpochMilli() + ".png";
        renderer.render(obsList, msg, filename);
    }
}
