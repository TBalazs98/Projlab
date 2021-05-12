package Model;
import Controller.Main;

import java.util.ArrayList;
import java.util.Random;

/**
 *Egy, az aszteroida ovben talalhato aszteroidat reprezentalja.
 */
public class Asteroid implements DestinationObject, Steppable {

    private boolean isEmpty;

    private int layers;

    private boolean isNearSun;

    private Material material;

    private ArrayList<Character> characters;

    private ArrayList<DestinationObject> neighbours;

    public int explodecount = 0, isInsusntorm = 0;

    /**
     * Az Asteroid osztaly publikus alapertelmezett konstruktora.
     */
    public Asteroid(){
        //Logger.getInstance().printCommandCall(this);
        this.layers = 3;
        this.isEmpty = true;
        this.material = null;
        this.isNearSun = false;
        this.neighbours = new ArrayList<DestinationObject>();
        this.characters = new ArrayList<Character>();
        //Logger.getInstance().printReturnCommand();
    }

    /**
     *Konstruktor, amiben beallithajuk az aszteroida tulajdonsagait.
     * @param layer az aszteroida felszinet borito szikla reteg mennyisege
     * @param isempty megmondja ureges-e az aszteroida
     * @param isnearsun megmondja napkozelben van-e az aszteroida
     * @param mat az aszteroidaban talalhato nyersanyag
     */
    public Asteroid(int layer, boolean isempty,boolean isnearsun, Material mat){
        //Object[] p = {layer, isempty, isnearsun, mat.name};
        //Logger.getInstance().printCommandCall(this, p);
        this.layers = layer;
        this.isEmpty = isempty;
        this.isNearSun = isnearsun;
        this.material = mat;
        this.characters = new ArrayList<Character>();
        this.neighbours = new ArrayList<DestinationObject>();
        //Logger.getInstance().printReturnCommand();
    }



    /**
     * Karakter beallitasa
     * @param c karakter, akit rateszunk az aszteroidara
     */
    public void setCharacter(Character c){
        //Object[] p = {c.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);
        characters.add(c);
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Parameterben megadott objektum felvetele a szomszedok koze.
     * @param d objektum amit fel akarunk venni
     */
    public void setNeighbour(DestinationObject d){
        //Object[] p = {d.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);
        neighbours.add(d);
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Visszater a sziklaretegek szamaval.
     * @return sziklareteg vastagsaga
     */
    public int getLayers() {
        //Logger.getInstance().printCommandCall(this);
        //Logger.getInstance().printReturnCommand(this.layers);
        return this.layers;
    }

    /**
     * Visszater a magban talalhato nyersanyaggal.
     * @return nyersanyag, ha ures null-al ter vissza
     */
    public Material getMaterial() {
        //Logger.getInstance().printCommandCall(this);
        //Logger.getInstance().printReturnCommand(this.material.name);
        return this.material;
    }

    /**
     * Karakter elfogadasa az aszteroidara lepeskor.
     * @param c karakter, aki ralep
     * @return visszater sajat magaval, egy Destination Object-el
     */

    public DestinationObject Accept(Character c) {
        //Object[] p = {c.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);
        characters.add(c);
        //Logger.getInstance().printReturnCommand(this.getClass().getSimpleName());
        return this;
    }

    /**
     * Karakter eltavolitasa az aszteroidarol.
     * @param c karakter akit eltavolitunk
     */
    public void Remove(Character c) {
        //Object[] p = {c.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);
        characters.remove(c);
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Az aszteroida egy kore, itt allitja be, hogy az egyes aszteroidak napkozelben, illetve naptavolban legyenek.
     */
    public void Step() {
        //Logger.getInstance().printCommandCall(this);
        this.SetSunProximityAuto();
        if((layers == 0) && (!isEmpty) && (material !=null))
            this.material.Hit(this);

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A karakter furas metodusa utan hivodik meg, es csokkenti a sziklareteget.
     */
    public void Drilled() {
        //Logger.getInstance().printCommandCall(this);

        //fuggveny lefutasa felhasznaloi beavatkozassal
       /* System.out.print("? How many layers does the asteroid have after drilling?\t");
        Scanner sc = new Scanner(System.in);
        //InputStreamReader br = new InputStreamReader(System.in);
        char ch = ' ';
        int layer = 0;
        try {
            layer = sc.nextInt();
            System.out.print("? Is the asteroid empty?\t(Y)es / (N)o\t");
            InputStreamReader cr = new InputStreamReader(System.in);
            ch = (char)cr.read();
            //br.close();
            //cr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if((ch=='n' || ch=='N' ) && layer == 0) {
            material.Hit(this);d
        }*/

        //fuggveny lefutasa tagvaltozo lekerdezesevel
        if (this.layers > 0){
            this.layers--;
            if(!(this.isEmpty) && (this.layers == 0) && (material != null)) {
                this.material.Hit(this);
            }
        }


        //Logger.getInstance().printReturnCommand();
    }

    /**
     * A telepes banyaszas utan kiveszi az aszteroidabol a nyersanyagot, visszater vele.
     * @return a benne talalhato nyersanyag, ha nincs null
     */
    public Material Mined() {
        //Logger.getInstance().printCommandCall(this);
        if(!isEmpty && layers == 0 && material != null) {
            material.Hit(this);
            isEmpty = true;
            if(material == null) {
                //Logger.getInstance().printReturnCommand();
                return null;
            }
            //Logger.getInstance().printReturnCommand(material.getName());
            Material m = material;
            material = null;
            return m;
        }
        //Logger.getInstance().printReturnCommand();
        return null;
    }

    /**
     * Destination Object hozzaadasa a szomszedokhoz.
     * @param d a hozzaadott Destination Object
     */
    public void AddNeighbour(DestinationObject d) {
        //Object[] p = {d.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);
        if(!neighbours.contains(d))
            this.neighbours.add(d);
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Parameterul kapott objektum eltavolitasa a szomszedok kozul.
     * @param d a kapott szomszed eltavolitasa
     */
    public void RemoveNeighbour(DestinationObject d) {
        //Object[] p = {d.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);
        if(neighbours.contains(d))
            this.neighbours.remove(d);
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Nyersanyag visszahelyezese az aszteroida magjaba.
     * @param m nyersanyag
     * @return visszater egy logikai ertekkel, hogy sikeres volt-e
     */
    public boolean AddMaterial(Material m) {
        //Object[] p = {m.getName()};
        //Logger.getInstance().printCommandCall(this, p);
        if((this.isEmpty) && (this.layers == 0)) {
            isEmpty = false;
            this.material = m;
            m.Hit(this);
            //Logger.getInstance().printReturnCommand(true);
            return true;
        }
        //Logger.getInstance().printReturnCommand(false);
        return false;
    }

    /**
     * Beallitjuk az aszteroia nyersanyagat
     * @param m a nyersanyag, melyet az aszteroida nyersanyagava allitunk
     */
    public void SetMaterial(Material m) {
        isEmpty = false;
        this.material = m;
    }

    /**
     * Nyersanyag eltavolitsa az aszteroida magjabol.
     * @param m adott nyersanyagot tavolitja el
     */
    public void RemoveMaterial(Material m) {
        //Object[] p = {m.getName()};
        //Logger.getInstance().printCommandCall(this, p);
        if(!isEmpty) {
            this.material = null;
            isEmpty = true;
        }
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Parameterul kapott ID szerinti aszteroidat adja vissza.
     * @param id a kapott id
     * @return visszater az objektummal
     */
    public DestinationObject GetNeighbour(int id) {
        //Object[] p = {id};
        //Logger.getInstance().printCommandCall(this, p);
        if((id > neighbours.size()) || (id < 0)) {
            //Logger.getInstance().printReturnCommand();
            return null;
        }
        DestinationObject dob = neighbours.get(id);
        //Logger.getInstance().printReturnCommand(dob.getClass().getSimpleName());
        return dob;
    }

    /**
     * Visszaadjuk hanyadik indexen talalthato a jelenlegi aszteroida szomszedsagi tombjeben a parameterul adott szomszedos objektum
     * @param obj a szomszedos objektum
     * @return a szomszedok tombben a parameterul adott objektum indexe
     */
    public int GetNeighbourIndex(DestinationObject obj){
        return  neighbours.indexOf(obj);
    }

    /**
     * Random szomszedot ad vissza.
     * @return egy random szomszed
     */
    public int GetRandNeighbour() {
        //Logger.getInstance().printCommandCall(this);
        Random rand = new Random();
        if(neighbours.size() == 0)
            return -1;
        int n = rand.nextInt(neighbours.size());
        //Logger.getInstance().printReturnCommand(n);
        return n;
    }

    /**
     * Lekerjuk az adott aszteroida szomszed-listajat
     * @return visszaadjuk az aszteroida szomszedjainak listajat
     */
    public ArrayList<DestinationObject> GetNeighbours(){
        return neighbours;
    }

    /**
     * Aszteroida napkozel/naptavol automatikus/rendszeren beluli allitasa.
     */
    public void SetSunProximityAuto(){
        //Logger.getInstance().printCommandCall(this);
        Random rand = new Random();
        int rand_int = rand.nextInt(2);
        switch(rand_int){
            case 0 : {
                isNearSun = false;
                break;
            }
            case 1 : {
                isNearSun = true;
                break;
            }
        }
        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Beallitjuk manualisan az aszteroida naptol valo tavolsagat (kozeli/tavoli)
     * @param isnear igaz ha kozel van az aszteroida a naphoz
     */
    public void SetSunProximityManual(boolean isnear){
        this.isNearSun = isnear;
        //élesben törlendő
        if((layers == 0) && (!isEmpty) && (material !=null))
            this.material.Hit(this);
    }

    /**
     * Visszaadja az aszteroida naphoz viszonyitott elhelyezkedeset.
     * @return napkozelben/naptavolban
     */
    public boolean GetSunProximity() {
        //Logger.getInstance().printCommandCall(this);
        //Logger.getInstance().printReturnCommand(isNearSun);
        return isNearSun;
    }

    public boolean GetisEmpty() {
        //Logger.getInstance().printCommandCall(this);
        //Logger.getInstance().printReturnCommand(isEmpty);
        return isEmpty;
    }

    /**
     *Az aszteroidat napvihar eri el.
     */
    public void HitBySunstorm() {
        Game.getInstance().c.g.getAsteroidViewByAsteroid(this).setInSunstorm();
        Game.getInstance().c.g.gamespace.repaint();
        Game.getInstance().c.g.gamespace.validate();
        //Logger.getInstance().printCommandCall(this);
        if((layers != 0) || (!isEmpty)) {
            int n = characters.size();
            for(int i = n - 1; i >= 0; i--) {
                characters.get(i).Die();
            }
//            for(int i = 0; i < characters.size(); i++) {
//                this.characters.get(i).Die();
//            }
            for(TeleportGate t: Main.teleportgates) {
                if(t.GetAsteroid() == this) {
                    t.HitBySunstorm();
                }
            }
        }
        Game.getInstance().c.g.gamespace.repaint();
        Game.getInstance().c.g.gamespace.validate();
//
    }

    /**
     * Az aszteroida szomszedai kozul a parameterul kapott aszteroida felrobban.
     * @param a az egyik  szomszedja, akit el kell tavolitani a szomszedok listajabol
     */
    public void HitByExplosion(Asteroid a) {
        //Object[] p = {a.getClass().getSimpleName()};
        //Logger.getInstance().printCommandCall(this, p);

        this.neighbours.remove(a);

        //Logger.getInstance().printReturnCommand();
    }

    /**
     *Az aszteroida felrobban, errol ertesiti a szomszedait, illetve a rajta levo karakterek ennek megfeleloen viselkednek.
     */
    public void Explode() {

        //Logger.getInstance().printCommandCall(this);;
        this.material = null;


        int n = characters.size();
        for(int i = n - 1; i >= 0; i--) {
            characters.get(i).Explode();
        }
        for(DestinationObject o : this.neighbours) {
            if(o!=null)
            o.HitByExplosion(this);
        }
        Game.getInstance().c.g.getAsteroidViewByAsteroid(this).setExploding();
        Game.getInstance().c.g.gamespace.repaint();
        Game.getInstance().c.g.gamespace.validate();
        //this.neighbours.forEach(n -> n.HitByExplosion(this));
        //this.characters.forEach(c -> c.Explode());

//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //Logger.getInstance().printReturnCommand();
        Game.getInstance().c.g.gamespace.repaint();
        Game.getInstance().c.g.gamespace.validate();
    }

    /**
     * Beallitja a parameterul kapott erteket az aszterodia sziklaretegenek.
     * @param layer parameterul kapott ertek
     */
    public void setLayer(int layer){
        //Object[] p = {layer};
        //Logger.getInstance().printCommandCall(this, p);

        this.layers = layer;

        //Logger.getInstance().printReturnCommand();
    }

    /**
     * Az aszteroida uressegenek beallitasa
     * @param emptiness igaz erteku, ha uresre allitjuk az aszteroidat
     */
    public void SetEmpty(boolean emptiness){
        this.isEmpty = emptiness;
    }

    /**
     * Az aszteroida szomszedjainak szamanak lekerdezese
     * @return aszteroida szomszedjainak szama
     */
    public int GetNeighbourCount(){
        return  neighbours.size();
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }
    

}