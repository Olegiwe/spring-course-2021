package persist;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String username;

    public User(String username) {
        this.username = username;
    }
}
