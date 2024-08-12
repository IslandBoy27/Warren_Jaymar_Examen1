public enum TipoPesquero {
    PEZ(10.0),
    CAMARON(20.0),
    LANGOSTA(30.0);

    public final double price;

    TipoPesquero(double price) {
        this.price = price;
    }
}
