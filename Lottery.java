import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Random;

public class Lottery
{
    public static boolean bonusGet = false;
    
    public static void main(String[] args)
    {
        System.out.println("=== Normal ===");
        List<Integer> random = GetRandomNums(6);
        List<Integer> user = GetUserNums(6);
        System.out.println("\n=== Bonus ===");
        Integer randBonus = GetRandomNums(1).get(0);
        Integer userBonus = GetUserNums(1).get(0);
        bonusGet = (randBonus == userBonus);
        Integer matches = CheckRef(random, user);
        System.out.println("You won: £" + MoneyWon(matches));
    }
    
    
    public static List<Integer> GetRandomNums(int size)
    {
        List<Integer> random = new ArrayList<Integer>();
        for (int i = 1; i <= size; i++)
        {
            double randNum = 0.0;
            do { randNum = RandNum(1, 49); } while(random.contains((int) randNum));
            random.add((int) randNum);
            //System.out.println("rand num: " + (int) randNum);
        }        
        return random;
    }
    
    
    public static List<Integer> GetUserNums(int size)
    {
        List<Integer> user = new ArrayList<Integer>();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your " + size + " number(s)");
        for (int i = 1; i <= size; i++)
        {
            Integer in = 0;
            boolean error = false;
            do 
            {
                error = false;
                System.out.print(i + ": ");
                in = input.nextInt();
                error = ( ( (in < 1) || (in > 49) ) || (user.contains(in)) );
                if (error) { System.out.println("Invalid number"); }
            } while(error);
            user.add(in);
        }
        return user;
    }
           
    
    public static int CheckRef(List<Integer> random , List<Integer> user)
    {
        int matches = 0;
        int randInt = 0;
        int userInt = 0;
        for (int i = 0; i < random.size(); i++)
        {
            randInt = random.get(i);
            for (int j = 0; j < user.size(); j++)
            {
                if (randInt == user.get(j)) 
                { 
                    matches++; 
                    if (i == 6) { bonusGet = true; }
                }                
            } 
        }
        return matches;
    }
    
    
    public static int MoneyWon(int matches)
    {
        int win = 0;
        //System.out.println("matches: " + matches);
        if (matches == 3) { win = 10; }
        else if (matches == 4) { win = 100; }
        else if (matches == 5) 
        { 
            win = 10000;
            if (bonusGet) { win = 100000; }
        }
        else if (matches == 6) { win = 1000000; }
        return win;
    }
    
    
    public static int RandNum(int min, int max)
    {
        Random rand = new Random();
        int randNum = rand.nextInt((max - min) + 1) + min;
        return randNum;
    }    
}