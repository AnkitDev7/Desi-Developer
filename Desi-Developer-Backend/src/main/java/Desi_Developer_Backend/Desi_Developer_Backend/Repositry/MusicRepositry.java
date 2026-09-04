package Desi_Developer_Backend.Desi_Developer_Backend.Repositry;
import Desi_Developer_Backend.Desi_Developer_Backend.Entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepositry extends JpaRepository<Music, Long> {

}
