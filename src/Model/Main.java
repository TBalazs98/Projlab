package Model;


public class Main {

    public static void main(String[] args) {
	// write your code her
        //System.out.println("anyad");

        //Settler settler = new Settler();
        //settler.Die();

        int input;
        Material m = new Material();
        m.setName(NormalMaterialName.IRON);
        Asteroid a = new Asteroid();
        System.out.println(a.AddMaterial(m));
        Settler s = new Settler();
        s.setAsteroid(a);
        s.Mine();

        s.PlaceMaterial(m);

        Test t = new Test();
        t.menu();               //teszt osztály
    }







}
