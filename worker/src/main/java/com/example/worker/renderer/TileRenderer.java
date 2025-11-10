package com.example.worker.renderer;
import com.example.commondto.dto.TileSeed;
import com.example.worker.entity.SatObs;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class TileRenderer {

    public void render(List<SatObs> obsList, TileSeed msg, String outputPath) {
        int width = 256;
        int height = 256;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // White background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Coordinate bounds
        double minLon = msg.getMinLon();
        double maxLon = msg.getMaxLon();
        double minLat = msg.getMinLat();
        double maxLat = msg.getMaxLat();

        // Draw each point
        for (SatObs obs : obsList) {
            int x = (int) ((obs.getLon() - minLon) / (maxLon - minLon) * width);
            int y = height - (int) ((obs.getLat() - minLat) / (maxLat - minLat) * height);  // flip Y

            Color color = valueToColor(obs.getValue());
            g.setColor(color);
            g.fillOval(x, y, 3, 3);  // small dot
        }

        g.dispose();

        try {
            File outFile = new File(outputPath);
            outFile.getParentFile().mkdirs();
            ImageIO.write(image, "png", outFile);
            System.out.println("✅ Tile saved to: " + outFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Color valueToColor(double temp) {
        // Normalize temp: 0°C to 50°C mapped to blue → red
        float norm = (float) Math.min(1.0, Math.max(0.0, temp / 50.0));
        return new Color(norm, 0.2f, 1.0f - norm);  // RGB heatmap
    }
}