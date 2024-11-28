package medicaments;

public abstract class Medicament {
    protected String libelle;
    protected int reference;
    protected float prix;
    protected String dateFab;

    public Medicament() {}

    public Medicament(String libelle, int reference, float prix, String dateFab) {
        this.libelle = libelle;
        this.reference = reference;
        this.prix = prix;
        this.dateFab = dateFab;
    }

    @Override
    public String toString() {
        return "Medicaments{" +
                "libelle='" + libelle + '\'' +
                ", reference=" + reference +
                ", prix=" + prix +
                ", dateFab='" + dateFab + '\'' +
                '}';
    }

    public abstract float calculTaxApplique();
}
