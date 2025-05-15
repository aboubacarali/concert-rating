package fr.rating.concert.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@NoArgsConstructor
@AllArgsConstructor
public class PasswordEncoder {
    private int strength = 10;

    /**
     * Hash le mot de passe brut avec BCrypt
     */
    public String encode(String toEncode) {
        return BCrypt.hashpw(toEncode, BCrypt.gensalt(strength));
    }

    /**
     * Compare un mot de passe brut avec un hash BCrypt existant
     */
    public boolean matches(String toMatch, String hashedPassword) {
        return BCrypt.checkpw(toMatch, hashedPassword);
    }
}
