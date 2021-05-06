import Controller.InputManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class TestcaseTester {

    private ArrayList<String> actual_output;

 public void Testing(String input, String output) {

     try {
         actual_output = InputManager.GenerateOutput(input);
         File currentfile = InputManager.getFile("Files", "Output", output);
         BufferedReader reader = new BufferedReader(new FileReader(currentfile));
         String expected_line = "";
         int counter = 0;
         System.out.println("[Expected output]");
         while(true) {
            expected_line = reader.readLine();
            if(expected_line == null) {
                break;
            }
            Assert.assertEquals(expected_line, actual_output.get(counter));
             System.out.println(expected_line);
            counter++;
         }
     }
     catch (IOException IOE){
     System.out.print("File not exist!");
    }finally {
         actual_output.clear();
         InputManager.zero();
     }
 }

    @Test
    public void Test1()  {
        Testing("1in","1out");
    }
    @Test
    public void Test2()  {
       Testing("2in","2out");
    }
    @Test
    public void Test3()  {
        Testing("3in","3out");
    }
    @Test
    public void Test4() {
        Testing("4in","4out");
    }
    @Test
    public void Test5()  {
        Testing("5in","5out");
    }
    @Test
    public void Test6()  {
        Testing("6in","6out");
    }
    @Test
    public void Test7()   {
        Testing("7in","7out");
    }
    @Test
    public void Test8()   {
        Testing("8in","8out");
    }
    @Test
    public void Test9()   {
        Testing("9in","9out");
    }
    @Test
    public void Test10()   {
        Testing("10in","10out");
    }
    @Test
    public void Test11()   {
        Testing("11in","11out");
    }
    @Test
    public void Test12()   {
        Testing("12in","12out");
    }
    @Test
    public void Test13()   {
        Testing("13in","13out");
    }
    @Test
    public void Test14()   {
        Testing("14in","14out");
    }
    @Test
    public void Test15()   {
        Testing("15in","15out");
    }
    @Test
    public void Test16()   {
        Testing("16in","16out");
    }
    @Test
    public void Test17() { Testing("17in", "17out"); }
    @Test
    public void Test18()   {
        Testing("18in","18out");
    }
    @Test
    public void Test19()   {
        Testing("19in","19out");
    }
    @Test
    public void Test20()   {
        Testing("20in","20out");
    }
    @Test
    public void Test21()   {
        Testing("21in","21out");
    }
    @Test
    public void Test22()   {
        Testing("22in","22out");
    }
    @Test
    public void Test23()   {
        Testing("23in","23out");
    }
    @Test
    public void Test24()   {
        Testing("24in","24out");
    }
    @Test
    public void Test25()   {
        Testing("25in","25out");
    }
    @Test
    public void Test26()   {
        Testing("26in","26out");
    }
    @Test
    public void Test27()   {
        Testing("27in","27out");
    }
    @Test
    public void Test28()   {
        Testing("28in","28out");
    }
    @Test
    public void Test29()   {
        Testing("29in","29out");
    }
    @Test
    public void Test30()   {
        Testing("30in","30out");
    }
    @Test
    public void Test31()   {
        Testing("31in","31out");
    }
    @Test
    public void Test32()   {
        Testing("32in","32out");
    }
    @Test
    public void Test33()   {
        Testing("33in","33out");
    }
    @Test
    public void Test34()   {
        Testing("34in","34out");
    }
    @Test
    public void Test35()   {
        Testing("35in","35out");
    }
    @Test
    public void Test36()   {
        Testing("36in","36out");
    }
}