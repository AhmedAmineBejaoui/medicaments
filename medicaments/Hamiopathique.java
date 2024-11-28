package medicaments;

public class Hamiopathique extends Medicament {
    protected String plante;

    public Hamiopathique() {}

    public Hamiopathique(String libelle, int reference, float prix, String dateFab, String plante) {
        super(libelle, reference, prix, dateFab);
        this.plante = plante;
    }

    @Override
    public float calculTaxApplique() {
        return prix * 0.2f;
    }

    @Override
    public String toString() {
        return super.toString() + "Hamiopathique{" + "plante='" + plante + '\'' + '}';
    }
}
