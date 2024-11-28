package medicaments;

public class AntiInflematoire extends Medicament implements Remboursable {
    protected String moleculaire;
    protected int acidite;

    public AntiInflematoire() {}

    public AntiInflematoire(String libelle, int reference, float prix, String dateFab, String molecule, int acidite) {
        super(libelle, reference, prix, dateFab);
        this.moleculaire = molecule;
        this.acidite = acidite;
    }

    @Override
    public float calculTaxApplique() {
        float taux = moleculaire.equals("steroidien") ? 0.1f : 0.15f;
        return prix * taux;
    }

    @Override
    public float remboursement() {
        return prix * 0.8f;
    }

    @Override
    public String toString() {
        return super.toString() + "AntiInflematoire{" + "moleculaire='" + moleculaire + '\'' + ", acidite=" + acidite + '}';
    }
}
