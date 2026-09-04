package Desi_Developer_Backend.Desi_Developer_Backend.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicDTO {

    private String title;

    private String artist;

    private String album;

    private MultipartFile music;

    private MultipartFile cover;
}
