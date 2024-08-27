package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Base64;

@RestController
public class ImageController {

    @GetMapping("/image")
    public String getImageRGB() {
        ImageRGB image=ImageRGB.getInstance();

        final ByteArrayOutputStream os = new ByteArrayOutputStream();

        try
        {
            ImageIO.write(image.getImage(), "png", os);
        }
        catch (final IOException ioe)
        {
            throw new UncheckedIOException(ioe);
        }

        String output = "<div>\n" +
                "  <img src=\"data:image/jpeg;base64," + Base64.getEncoder().encodeToString(os.toByteArray()) + "\" alt=\"Cursed cat\" />\n" +
                "</div>";
        return output;
    }
}
