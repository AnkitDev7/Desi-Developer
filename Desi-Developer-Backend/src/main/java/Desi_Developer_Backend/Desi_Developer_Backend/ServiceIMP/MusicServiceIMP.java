package Desi_Developer_Backend.Desi_Developer_Backend.ServiceIMP;
import Desi_Developer_Backend.Desi_Developer_Backend.DTO.MusicDTO;
import Desi_Developer_Backend.Desi_Developer_Backend.Entity.Music;
import Desi_Developer_Backend.Desi_Developer_Backend.Repositry.MusicRepositry;
import Desi_Developer_Backend.Desi_Developer_Backend.Service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class MusicServiceIMP implements MusicService {

    @Autowired
    private final MusicRepositry musicRepository;

    private final Path musicDirectory =
            Paths.get("uploads/music");

    private final Path coverDirectory =
            Paths.get("uploads/covers");


    public MusicServiceIMP(MusicRepositry musicRepository) {
        this.musicRepository = musicRepository;

        try {
            Files.createDirectories(musicDirectory);
            Files.createDirectories(coverDirectory);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Upload directories create nahi ho paaye",
                    e
            );
        }
    }

    @Override
    public Music addMusic(MusicDTO musicDTO) {

        MultipartFile musicFile =musicDTO.getMusic();
        MultipartFile coverFile = musicDTO.getCover();

        if (musicFile == null || musicFile.isEmpty()) {
            throw new RuntimeException("Music file empty");
        }else if (coverFile == null || coverFile.isEmpty()) {
            throw new RuntimeException("Cover file empty");
        }

        try {
            // Music File save
            String musicFileName = UUID.randomUUID() + "_" + musicFile.getOriginalFilename();

            Path musicPath =
                    musicDirectory.resolve(musicFileName);

            Files.copy(
                    musicFile.getInputStream(),
                    musicPath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            // Cover Image Save
            String coverFileName = UUID.randomUUID() + "_" + coverFile.getOriginalFilename();

            Path coverPath =
                    coverDirectory.resolve(coverFileName);

            Files.copy(
                    coverFile.getInputStream(),
                    coverPath,
                    StandardCopyOption.REPLACE_EXISTING
            );


            // Audio URL

            String audioUrl =
                    "/covers/" + coverFileName;

            // cover Url
            String coverUrl =
                    "/covers/" + coverFileName;

            // Data set

            Music music = new Music();
            music.setArtist(musicDTO.getArtist());
            music.setAlbum(musicDTO.getAlbum());
            music.setTitle(musicDTO.getTitle());
            music.setAlbum(musicDTO.getAlbum());

            music.setAudioUrl(audioUrl);
            music.setCoverUrl(coverUrl);

            return musicRepository.save(music);

        }catch (IOException e) {
            throw new RuntimeException("Music file save karne me problem aayi ");
        }

    }

    @Override
    public List<Music> getAllMusic() {
       return musicRepository.findAll();
    }
}
