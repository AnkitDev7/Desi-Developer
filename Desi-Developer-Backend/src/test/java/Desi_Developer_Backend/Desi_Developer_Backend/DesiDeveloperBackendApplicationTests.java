package Desi_Developer_Backend.Desi_Developer_Backend;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootTest
class DesiDeveloperBackendApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Test
//	void keyTest() {
//		SecretKey key = Jwts.SIG.HS512.key().build();
//
//		String finalKey = Base64.getEncoder()
//				.encodeToString(key.getEncoded());
//
//		System.out.println("******************" + finalKey + "***********************");
//	}

}
