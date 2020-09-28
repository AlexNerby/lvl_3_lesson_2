package server;

public class SqlAuthService implements AuthService {
    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return SqlHandler.getNicknameByLoginAndPassword(login, password);
    }
//
    @Override
    public boolean connectDb() {
        return SqlHandler.connectDb();
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return SqlHandler.registration(login, password, nickname);
    }

    @Override
    public boolean rename(String oldName, String newName) {
        return SqlHandler.rename(oldName, newName);
    }
}
