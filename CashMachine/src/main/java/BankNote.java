import java.util.ArrayList;

public class BankNote {
    //Банковская ячейка
    public int count;
    public Nominal nominal;

    public BankNote(Nominal nominal, int count) {
        this.nominal = nominal;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /*public int getCount() {
        return count;
    }

    public Nominal getNominal() {
        return nominal;
    }

    public int getNominalVal() {
        return nominal.getVal();
    }*/

}
