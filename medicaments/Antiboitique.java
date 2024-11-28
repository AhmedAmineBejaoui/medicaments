package medicaments;

public class Antiboitique extends Medicament implements Remboursable {
    protected String bacterie;

    public Antiboitique() {}

    public Antiboitique(String libelle, int reference, float prix, String dateFab, String bacterie) {
        super(libelle, reference, prix, dateFab);
        this.bacterie = bacterie;
    }

    @Override
    public float calculTaxApplique() {
        float taux;
        switch (bacterie) {
            case "thermophyle" -> taux = 0.1f;
            case "mesophyle" -> taux = 0.12f;
            default -> taux = 0.15f;
        }
        return prix * taux;
    }

    @Override
    public float remboursement() {
        return prix * 0.9f;
    }

    @Override
    public String toString() {
        return super.toString() + "Antiboitique{" + "bacterie='" + bacterie + '\'' + '}';
    }
}
