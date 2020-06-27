package ubung4.logistics.materials;

import ubung4.logistics.quantities.IntUnit;

public class ShellBatches implements IntUnit {

    private ShellBatches() {}

    public final static ShellBatches INSTANCE=new ShellBatches();

    @Override
    public String toString(){
        return "batches of 120mm shells";
    }
}
