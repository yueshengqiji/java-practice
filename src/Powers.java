enum Powers {
    FRAGILE(2,"falsh"),
    LOWPOWER(0.5,"ture");
    private final double i;
    Powers(double i, String falsh) {
        this.i=i;
    }
    public double geti(){return i;}
}
