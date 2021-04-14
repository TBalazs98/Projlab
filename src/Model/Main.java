package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Arrays;

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
        String materialregex = "[013]|2\\s[012]";
        String asteroidregex ="0\\s\\d\\s\\d\\s0|\\d\\s(\\d,)*\\d\\s[01]\\s\\d{1,2}\\s1\\s\\d\n";
        String tgregex ="\\d\\s[01]\\s\\d\\s[01]\\s[01]";
        String settlerregex ="\\d\\s\\d\\s(\\d,)*\\d\\s[0123]\\s\\d|\\d\\s0\\s0";
        String robotregex ="\\d";
        String uforegex ="\\d\\s0|\\d\\s\\d\\s(\\d,)*\\d\n";



        Scanner in = new Scanner(System.in);
        String[] input={};
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String[] cmd={};
        int meddig=0;
        String line;
        try {

            while ((line = reader.readLine()) != "")
            {

                input = line.split("\\n");
                System.out.println(Arrays.toString(input));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(input[0]);
        System.out.println(input[1]);
        System.out.println(input[2]);

        int[] ObjCounts = {0,0,0,0,0,0}; //Material, Asteroid, TG, Settler, Robot, Ufo
        Material[] materials;
        Asteroid[] asteroids;
        TeleportGate[] teleportgates;
        Settler[] settlers;
        Robot[] robots;
        UFO[] ufos;





//
//        int sum =0;
//        //System.out.println(Pattern.matches("\\W*((?i)createmap(?-i))\\W*\\s(\\d+\\s){5}\\d+","createmap 1 2 3 4 5 6"));
//        System.out.println(Pattern.matches(regex,input));
//        if(Pattern.matches(regex,input)){
//            String[] cmd=input.split(" ");
//            for(int i=1; i<cmd.length;i++){
//                System.out.println(cmd[i]);
//                ObjCounts[i-1]=Integer.parseInt(cmd[i]);
//                sum+=ObjCounts[i-1];
//            }
//
//            System.out.println(sum);
//            for(int i=0; i<ObjCounts.length;i++){
//                System.out.println(ObjCounts[i]);
//            }
//
//        }
//




        //Test t = new Test();
        //t.menu();               //teszt menu meghivasa




    }

    public void beolvas(){


    }







}
