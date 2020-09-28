package server;

public interface AuthService {
    /**
     * @return nickname если пользователь есть
     * @return null если пользоватаеля нет
     * */
    String getNicknameByLoginAndPassword(String login, String password);

    boolean connectDb();

    boolean registration(String login, String password, String nickname);

    boolean rename(String oldNickname, String newNickname);
}