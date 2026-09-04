package Desi_Developer_Backend.Desi_Developer_Backend.Controller;
import Desi_Developer_Backend.Desi_Developer_Backend.DTO.MusicDTO;
import Desi_Developer_Backend.Desi_Developer_Backend.Entity.Music;
import Desi_Developer_Backend.Desi_Developer_Backend.Service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    @Autowired
    private MusicService musicService;


    @PostMapping(
            value = "/add",
            consumes = "multipart/form-data"
    )
    public ResponseEntity<?> addMusic(
            @ModelAttribute MusicDTO musicDTO
    ) {

        Music savedMusic =
                musicService.addMusic(musicDTO);

        return ResponseEntity.ok(savedMusic);
    }

    @GetMapping
    public ResponseEntity<List<Music>> getAllMusic() {

        List<Music> musicList = musicService.getAllMusic();

        return ResponseEntity.ok(musicList);
    }
}
