package Desi_Developer_Backend.Desi_Developer_Backend.Service;

import Desi_Developer_Backend.Desi_Developer_Backend.DTO.MusicDTO;
import Desi_Developer_Backend.Desi_Developer_Backend.Entity.Music;

import java.util.List;

public interface MusicService {
    Music addMusic(MusicDTO musicDTO);

    List<Music> getAllMusic();
}
