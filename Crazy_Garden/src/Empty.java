public class Empty extends Cell{
    Empty(){
        super();
    }
    Empty(boolean toggleState){
        super(toggleState);
    }

    public String getType() {
        return "Empty";
    }
}
