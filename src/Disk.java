/**
 * A class to define each piece and their position in game
 */
import java.util.ArrayList;
public class Disk {
    private char color;
    private String position;

    /**
     * A constructor to make a disk
     * @param c the color of disk
     * @param p the position of disk
     */
    public Disk(char c,String p){
        color = c;
        position = p;
    }

    /**
     * A method to set the disk position
     * @param pose the position of disk
     */
    public void setDiskPos(String pose){
            position = pose;
    }

    /**
     * A method to set the disk's color
     * @param c the first character of color of disk
     */
    public void setColor(char c){
        color = c;
    }
    public String getPosition(){
        return position;
    }
    public char getColor(){
        return color;
    }
}
