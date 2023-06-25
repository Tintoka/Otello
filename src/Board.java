import java.util.HashMap;
import java.util.ArrayList;
/**
 * A class to define board of the game and valid moves
 */
public class Board {
    private HashMap<String,Disk> disks;
    private ArrayList<String> validBlackMoves;
    private ArrayList<String> validWhiteMoves;
    char[] charList = {'A','B','C','D','E','F','G','H'};

    /**
     * A constructor to make board for the first time
     */
    public Board() {
        disks = new HashMap<>();
        validBlackMoves = new ArrayList<>();
        validWhiteMoves = new ArrayList<>();
        for (int i = 1; i < 9; i++)
            for (int j = 0; j < 8; j++) {
                String position = i + "" + charList[j];
                disks.put(position, new Disk('n', position));
            }
            disks.get("4D").setColor('b');
            disks.get("4E").setColor('w');
            disks.get("5D").setColor('w');
            disks.get("5E").setColor('b');
            validMoves();
    }

    /**
     * A method to add new disk to disk list in the board each turn
     * @param d the disk to be added
     */
    public void putDisk(Disk d){
        if(validPose(d.getColor(),d.getPosition())) {
            disks.replace(d.getPosition(), d);
            char xChar =  d.getPosition().charAt(1);
            int  x= 0;
            int y = Character.getNumericValue(d.getPosition().charAt(0));
            ArrayList<String> toChange = new ArrayList<>();
            for (int i = 0 ; i < 8; i++){
                if(charList[i] == xChar) {
                    x = i;
                    break;
                }
            }
            int fixX = x, fixY = y;
            boolean flag = false;
            x ++;
            y++;
            while(x < 8 && y < 9 && !(disks.get(y + "" + charList[x]).getColor() =='n')){
                toChange.add(y  + "" + charList[x]);
                if( disks.get( y  + "" + charList[x]).getColor() == d.getColor()) {
                    flag = true;
                    break;
                }
                x++;
                y++;
            }
            if(flag)
                changDisk(toChange,d.getColor());
            toChange.clear();
            flag = false;
            x = fixX + 1;
            y = fixY - 1;
            while(x < 8 && y > 0 &&  !(disks.get(y + "" + charList[x]).getColor() =='n')){
                toChange.add( y + "" + charList[x]);
                if(disks.get(y  + "" + charList[x]).getColor() == d.getColor()) {
                    flag = true;
                    break;
                }
                x++;
                y--;
            }
            if(flag)
                changDisk(toChange,d.getColor());
            toChange.clear();
            flag = false;
            x = fixX - 1;
            y = fixY - 1;
            while(x > -1 && y > 0 && !(disks.get(y + "" + charList[x]).getColor() =='n')){
                if(disks.get(y  + "" + charList[x]).getColor() == d.getColor()) {
                    flag = true;
                    break;
                }
                toChange.add(y  + "" + charList[x]);
                x--;
                y--;
            }
            if(flag)
                changDisk(toChange,d.getColor());
            toChange.clear();
            flag = false;
            x = fixX - 1;
            y = fixY + 1;
            while(x > -1 && y < 9 && !(disks.get(y + "" + charList[x]).getColor() =='n')){
                if( disks.get(y + "" + charList[x]).getColor() == d.getColor()) {
                    flag = true;
                    break;
                }
                toChange.add(y  + "" + charList[x]);
                x--;
                y++;
            }
            if(flag)
                changDisk(toChange,d.getColor());
            toChange.clear();
            x = fixX;
            y = fixY - 1;
            flag = false;
            while(y >  0 && !(disks.get(y + "" + charList[x]).getColor() == 'n')){
                toChange.add(y  + "" + charList[x]);
                if(disks.get(y + "" + charList[x]).getColor() == d.getColor()) {
                    flag = true;
                    break;
                }
                y--;
            }
            if(flag)
                changDisk(toChange,d.getColor());
            x = fixX;
            y = fixY + 1;
            flag = false;
            while(y < 9 && !(disks.get(y + "" + charList[x]).getColor() == 'n')){
                toChange.add( y + "" + charList[x]);
                if(disks.get(y  + "" + charList[x]).getColor() == d.getColor()) {
                    flag = true;
                    break;
                }
                y++;
            }
            if(flag)
                changDisk(toChange,d.getColor());
            toChange.clear();
            y = fixY;
            x = fixX - 1;
            flag = false;
            while(x > -1 && !(disks.get(y + "" + charList[x]).getColor() == 'n')){
                toChange.add(y + "" + charList[x] );
                if(disks.get(y + "" + charList[x]).getColor() == d.getColor()) {
                    flag = true;
                    break;
                }
                x--;
            }
            if(flag)
                changDisk(toChange,d.getColor());
            toChange.clear();
            x = fixX + 1;
            y = fixY;
            flag = false;
            while(x < 8 && !(disks.get(y + "" + charList[x]).getColor() =='n')){
                toChange.add(y + "" + charList[x]);
                if(disks.get(y + "" + charList[x]).getColor() == d.getColor()) {
                    flag = true;
                    break;
                }
                x++;
            }
            if(flag)
                changDisk(toChange,d.getColor());
            toChange.clear();
        }
        else
            System.out.println("invalid position");
    }

    public boolean validPose(char c,String position){
        if(c == 'w')
            return validWhiteMoves.contains(position);
        else if(c == 'b')
                return validBlackMoves.contains(position);
        else{
            System.out.println("invalid color");
            return false;
        }

    }

    /**
     * A method to print Board each turn
     */
    public void printBoard(){
        for(int i = 1; i < 9; i++){
            System.out.print(i + "");
            for(int j = 0; j < 8;j++ ){
                System.out.print(" | ");
                switch(disks.get(i + "" + charList[j]).getColor())
                {
                    case 'n' : {
                        System.out.print("  ");
                        break;
                    }
                    case 'b' :{
                        System.out.print('\u25CF' + " ");
                        break;
                    }
                    case 'w' :{
                        System.out.print('\u25CB' + " ");
                        break;
                    }
                    default :{
                        System.out.print("Invalid color");
                        break;
                    }
                }

            }
            System.out.println( );
        }
    }
    public void validMoves(){
        String[] directions= {"north","south","east","west","southWest","southEast","northEast","northWest"};
        validBlackMoves.clear();
        validWhiteMoves.clear();
        for(int i = 1; i < 9; i++)
            for(int j = 0; j < 8; j++)
                if(disks.get(i + "" + charList[j]).getColor() == 'n')
                    continue;
                else {
                    for (String s : directions) {
                        if(directionalCheck(s,i,j).equals("false"))
                            continue;
                        else if(disks.get(directionalCheck(s,i,j)).getColor() != 'n')
                            continue;
                        else if(disks.get(i + "" + charList[j]).getColor() == 'b')
                            validBlackMoves.add(directionalCheck(s,i,j));
                        else
                            validWhiteMoves.add(directionalCheck(s,i,j));

                    }

                }
    }
    private String directionalCheck(String direction,int k, int j){
        switch(direction) {
            case ("south"): {
                char color = disks.get(k + "" + charList[j]).getColor();
                int b = 1;
                //flag1 : if it reached the same color,flag2 : if it passed any other color
                boolean flag1 = false;
                k += b;
                while ( j < 8 && k > 0 && k < 9 && disks.get(k  + "" + charList[j]).getColor() != 'n' && disks.get(k  + "" + charList[j]).getColor() != color) {
                    if (disks.get(k  + "" + charList[j]).getColor() != 'n' && disks.get(k  + "" + charList[j]).getColor() != color)
                        flag1 = true;
                    k += b;
                }
                if(k > 8)
                    k--;
                if (flag1)
                    return (k  + "" + charList[j]);
                return "false";
            }
            case ("north"): {
                char color = disks.get(k + "" + charList[j]).getColor();
                int b = -1;
                k += b;
                boolean flag1 = false;//,flag2 = false;
                while ( j < 8 && k > 0 && k < 9 && disks.get(k + "" + charList[j]).getColor() != 'n' && disks.get(k  + "" + charList[j]).getColor() != color) {
                    if (disks.get(k  + "" + charList[j]).getColor() != 'n' && disks.get(k  + "" + charList[j]).getColor() != color)
                        flag1 = true;
                    k+= b;
                }
                if(k < 1)
                    k++;
                if (flag1)
                    return (k  + "" + charList[j]);
                return "false";
            }
            case ("east"): {
                int a = 1;
                boolean flag1 = false;
                char color = disks.get(k + "" + charList[j]).getColor();
                j+= a;
                while ( j < 8 && k > 0 && k < 9 && disks.get(k  + "" + charList[j]).getColor() != 'n' && disks.get(k  + "" + charList[j]).getColor() != color) {
                    if (disks.get(k  + "" + charList[j ]).getColor() != 'n' && disks.get(k  + "" + charList[j]).getColor() != color)
                        flag1 = true;
                    j += a;
                }
                // for the margins
                if(j > 7)
                    j--;
                if (flag1)
                    return (k  + "" + charList[j]);
                return "false";
            }
            case ("west"): {
                boolean flag = false;
                char color = disks.get(k + "" + charList[j]).getColor();
                int a = -1;
                j += a;
                while (j > -1 && j  < 8 && k > 0 && k < 9 && disks.get(k  + "" + charList[j]).getColor() != 'n' && disks.get(k  + "" + charList[j]).getColor() != color) {
                    if (disks.get(k  + "" + charList[j]).getColor() != 'n' && disks.get(k + "" + charList[j]).getColor() != color)
                        flag = true;
                    j += a;
                }
                if(j < 0)
                    j++;
                if (flag)
                    return k  + "" + charList[j];
                return "false";
            }
            case("northEast") : {
                 char color = disks.get(k + "" + charList[j]).getColor();
                int a = 1;
                int b = -1;
                j += a;
                k += b;
                boolean flag = false;
                while ((j > -1 && j < 8 && k > 1 && k < 9 && disks.get(k + "" + charList[j]).getColor() != 'n' && disks.get(k + "" + charList[j]).getColor() != color)) {
                    if (disks.get(k + "" + charList[j]).getColor() != 'n' && disks.get(k  + "" + charList[j]).getColor() != color)
                        flag = true;
                    j += a;
                    k += b;
                }
                if(j > 7)
                    j--;
                if(k < 1)
                   k++;
                if (flag)
                    return k  + "" + charList[j];
                return "false";
            }
            case("southWest") : {
                char color = disks.get(k + "" + charList[j]).getColor();
                int a = -1;
                 int b = 1;
                 j += a;
                 k += b;
                 boolean flag = false;
                 while(j > -1 && j < 8 && k > 0 && k < 9 && disks.get(k +"" + charList[j]).getColor() != 'n' && disks.get(k +"" + charList[j]).getColor() != color){
                     if(disks.get(k +"" + charList[j]).getColor() != 'n' && disks.get(k +"" + charList[j]).getColor() != color)
                         flag = true;
                     j += a;
                     k += b;
                 }
                if(j < 0)
                    j++;
                if(k > 8)
                    k--;
                if(flag)
                    return (k  + "" + charList[j]);
                 return "false";
                }
            case("northWest") : {
                char color = disks.get(k + "" + charList[j]).getColor();
                int a = -1, b = -1;
                j += a;
                k += b;
                boolean flag = false;
                while (j > -1 && j < 8 && k > 0 && k < 9  && disks.get(k + "" + charList[j]).getColor() != 'n' && disks.get(k +"" + charList[j]).getColor() != color) {
                    if (disks.get(k + "" + charList[j]).getColor() != 'n' && disks.get(k + "" + charList[j]).getColor() != color)
                        flag = true;
                    j +=a;
                    k +=b;
                }
                if(j < 0)
                    j++;
                if(k < 1)
                    k++;
                if (flag)
                    return k + "" + charList[j];
                return "false";
            }
            case("southEast") : {
                char color = disks.get(k + "" + charList[j]).getColor();
                int a = 1;
                int b = 1;
                j += a;
                k += b;
                boolean flag = false;
                while(j > -1 && j < 8 && k > 0 && k < 9 && disks.get(k +"" + charList[j]).getColor() != 'n' && disks.get(k +"" + charList[j]).getColor() != color) {
                    if(disks.get(k +"" + charList[j]).getColor() != 'n' && disks.get(k +"" + charList[j]).getColor() != color)
                        flag = true;
                    j +=a;
                    k += b;
                }
                if(j > 7)
                    j--;
                if(k > 8)
                    k--;
                if(flag)
                    return k  + "" + charList[j];
                return "false";
                }
             default:
                 System.out.println("Invalid direction");
                 return "false";
            }
    }
    public ArrayList<String> getValidBlackMoves(){
        return validBlackMoves;
    }
    public ArrayList<String> getValidWhiteMoves(){
        return validWhiteMoves;
    }
    public void scoreCounter(){
        int whiteScore = 0, blackScore = 0;
        for(int i = 0; i < 8; i++)
            for(int j = 1; j < 9; j++){
                if(disks.get(j + "" + charList[i]).getColor() == 'w')
                    whiteScore++;
                else if(disks.get(j + "" + charList[i]).getColor() == 'b')
                    blackScore++;
            }
        System.out.println("White Score : " + whiteScore);
        System.out.println("Black Score : " + blackScore);
        if(whiteScore > blackScore)
            System.out.println("Winner : White!");
        else if(whiteScore == blackScore)
            System.out.println("Tie(Just...How did you manage to get a tie?...)");
        else
            System.out.println("Winner : Black");
    }
    private void changDisk(ArrayList<String> toChange, char color){
        for (String s: toChange) {
            disks.replace(s,disks.get(s),new Disk(color,s));
        }
    }
}
