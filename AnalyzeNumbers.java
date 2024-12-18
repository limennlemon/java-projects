import java.util.Scanner;

public class AnalyzeNumbers {
    
 public static void main(String[] args) {
     
        Scanner in = new Scanner(System.in);

        int[] userNumbers = new int[100];
        int userValues;
        int counts = 0;
        System.out.print("Enter the integers between 1 and 100: ");
        do
        {
            userValues = in.nextInt();
            userNumbers[counts] = userValues;
            counts += 1;
        }
        while (userValues != 0);
        countsNums(userNumbers);
    }

    public static void countsNums(int[] numsArray) {
        for (int i = 1; i <= 100; i++) {
            int counts = 0;
            for (int j = 0; j < numsArray.length - 1; j++) {
                if (numsArray[j] == i)
                    counts += 1;
            }
            if (counts != 0)
                System.out.printf("%d occurs %d %s%n", 
                    i, counts, counts > 1 ? "times" : "time");
        } 
    }
}
