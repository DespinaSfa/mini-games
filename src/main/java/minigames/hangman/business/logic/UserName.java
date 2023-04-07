package minigames.hangman.business.logic;

public class UserName {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public UserName(String name) {
        this.userName = name;
    }

    public static int getUserNameMaxLength() {
        return 10;
    }
}