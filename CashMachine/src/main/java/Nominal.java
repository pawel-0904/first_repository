
public enum Nominal {
    R100 (100),
    R200 (200),
    R500 (500),
    R1000 (1000),
    R2000 (2000),
    R5000 (5000);

    private final int val;

    Nominal(int nominal){
        this.val = nominal;
    }

    public int getVal() {
        return val;
    }
}

