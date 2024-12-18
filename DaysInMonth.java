/*
Purpose: Program to show the number of days in a month for a year.
Course: CMIS 115 Introductory Programming
Date: November 4, 2023
*/

import java.util.Scanner; 

public class DaysInMonth {
    
  public static void main(String[] args) {
        
       int m,y,days=0;
       
	Scanner sc= new Scanner(System.in);
      System.out.println("Enter a number for the month: ");
	m=sc.nextInt();
	if(m<=12 && m>=1)
        {
	System.out.println("Enter a year: ");
	y=sc.nextInt();
	if(m==1 || m==3 ||m==5 ||m==7||m==8||m==10||m==12)
	{
		days=31;	 
	}
	else 
	       if(m==4||m==6||m==9||m==11)
	       {
		days=30;	  
	       }
	       else 
	       {
		if(m==2)
	       	{
		  if(y%400==0&&y%100==0)
		  {
			days=29;
		  }
		  else  
		  {
		      if(y%4==0&&y%100!=0)
		        {
			days=29;
		        }
		        else
			days=28;
		   }
		}       	
		}  
        if (m == 1) {
            System.out.println("January " + y + " has " + days + " days.");
        }
        else if (m == 2) {
            System.out.println("February " + y + " has " + days + " days.");
        }
        else if  (m == 3) {
            System.out.println("March " + y + " has " + days + " days.");
        }
        else if  (m == 4) {
            System.out.println("April " + y + " has " + days + " days.");
        }
        else if  (m == 5) {
            System.out.println("May " + y + " has " + days + " days.");
        }
        else if  (m == 6) {
            System.out.println("June " + y + " has " + days + " days.");
        }
        else if  (m == 7) {
            System.out.println("July " + y + " has " + days + " days.");
        }
        else if  (m == 8) {
            System.out.println("August " + y + " has " + days + " days.");
        }
        else if  (m == 9) {
            System.out.println("September " + y + " has " + days + " days.");
        }
        else if  (m == 10) {
            System.out.println("October " + y + " has " + days + " days.");
        }
        else if  (m == 11) {
            System.out.println("November " + y + " has " + days + " days.");
        }
        else if  (m == 12) {
            System.out.println("December " + y + " has " + days + " days.");
        }
     }
     else {
	   System.out.println("Invalid entry.");
	}
}
}

