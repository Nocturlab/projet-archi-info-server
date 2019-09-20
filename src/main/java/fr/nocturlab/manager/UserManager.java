package fr.nocturlab.manager;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.nocturlab.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserManager {
	private static String ENCODE = "HmacSHA256";

	@Autowired
	private UserRepository userRepository;

	public byte[] encryptPassword(String value) {
		byte[] ret;
		try {
			// génération de la clé secrète pour HMAC
			SecretKeySpec sk = new SecretKeySpec(new byte[]{3, 54 ,86, 51 ,65, 8, 65, 9, 1, 65, 87, 6, 13 ,9, 98, 24}, "RawBytes");

			// initailisation avec la clé secrète
			Mac mac = Mac.getInstance(ENCODE);
			mac.init(sk);
			ret = mac.doFinal(value.getBytes());
		}
		catch (Exception e) {
			log.error("Erreur d'encodage de mot de passe", e);
			return null;
		}
		return ret;
	}

	/**
	 * Generate a new password
	 * @return Pass of 8 characters 
	 */
	public String generatePassword() {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz";
		String[] biblio = new String[] {
			allowedChars,
			allowedChars.toUpperCase(),
			"1234567890"
		};
		String pwd = "";
		for (int i = 0;i<8;++i) {
			int id = (int)(Math.random()*(double)biblio.length);
			if (id == biblio.length)
				id--;
			pwd += biblio[id].charAt((int)(Math.random()*(double)biblio[id].length()));
		}
		return pwd;
	}
}