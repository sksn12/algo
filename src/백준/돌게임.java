package 백준;

import java.util.Scanner;

public class 돌게임 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        if(Integer.parseInt(sc.next())%2==0) System.out.println("CY");
        else System.out.println("SK");
    }
}
