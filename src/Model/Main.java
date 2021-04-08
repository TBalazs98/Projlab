package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Bakonyi Klaudia
 * @author Bihari Bence
 * @author Nagy David
 * @author Toth Balazs
 * @author Wagner Reka
 */

public class Main {
    /**
     * Jelenleg a Test nevu tesztosztalyunk segitsegevel ellenorizzuk modellunk helyesseget.
     */
    public static void main(String[] args)  {


        String regex = "\\W*((?i)createmap(?-i))\\W*\\s(\\d+\\s){5}\\d+";
        Scanner in = new Scanner(System.in);

        String input="";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
             input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        int[] cuccosok = {0,0,0,0,0,0}; //Material, Asteroid, TG, Settler, Robot, Ufo

        Material[] material;

        int sum =0;
        //System.out.println(Pattern.matches("\\W*((?i)createmap(?-i))\\W*\\s(\\d+\\s){5}\\d+","createmap 1 2 3 4 5 6"));
        System.out.println(Pattern.matches(regex,input));
        if(Pattern.matches(regex,input)){
            String[] cmd=input.split(" ");
            for(int i=1; i<cmd.length;i++){
                System.out.println(cmd[i]);
                cuccosok[i-1]=Integer.parseInt(cmd[i]);
                sum+=cuccosok[i-1];
            }

            System.out.println(sum);
            for(int i=0; i<cuccosok.length;i++){
                System.out.println(cuccosok[i]);
            }

        }

        ArrayList<Material> materials = new ArrayList<>();
        for (int i = 0; i< cuccosok[0];i++){
            materials.add(new Material());
        }

        for(int i = 0; i< materials.size();i++){
            System.out.println(materials.get(i));
        }












        //Test t = new Test();
        //t.menu();               //teszt menu meghivasa




    }







}
