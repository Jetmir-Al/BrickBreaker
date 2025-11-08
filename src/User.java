public class User {
    private  String username;
    private int highestScore;

    public User(String username){
        this.username = username;
        this.highestScore = 0;
    }

    public String getUsername(){
        return username;
    }
    public int getScore(){
        return highestScore;
    }

    public void setHighestScore(int score){
        this.highestScore = score;
    }



}
