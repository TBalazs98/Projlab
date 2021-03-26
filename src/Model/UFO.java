package Model;

public class UFO extends Character implements Steppable, mine{
    Inventory inventory;

    public UFO(){
        super();
        inventory=new Inventory();
    }

    public void Explode() {

    }
    public void Step(){

    }
    public void Mine(){}



}
