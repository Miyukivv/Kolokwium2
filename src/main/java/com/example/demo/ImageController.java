package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/pixel")
    public ResponseEntity setColorOfPixel(@RequestParam int x, @RequestParam int y, @RequestParam String hexColor, @RequestParam int id)
    {
        for (Token tokens : Token.getTokens()){
            if (tokens.getId()==id) {
                if (!tokens.isTokenActive()){
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nieaktywny token");
                }
                else{
                    break;
                }
            }
        }

        if (x<0 || y<0 || x>512 || y>512){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Podane złe dane");
        }

        ImageRGB image = ImageRGB.getInstance();
        image.setPixelOfImage(x,y,hexColor);
        return ResponseEntity.status(HttpStatus.OK).body("git, dziala");
    }

}
