package tictactoe.datasource.repository.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="players")
public class Players {

    @Id
    private UUID id;

    private String login;
    private String password;

    @OneToMany(mappedBy = "player")
    private List<Games> dataGames = new ArrayList<>();

    public Players(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Players() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Games> getGames() { return dataGames; }
    public void setGames(List<Games> dataGames) { this.dataGames = dataGames; }

}
