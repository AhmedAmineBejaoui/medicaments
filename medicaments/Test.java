package medicaments;

public class Test {
    public static void main(String[] args) {
        Medicament[] med = new Medicament[5];
        med[0] = new Antiboitique("Clamoxyl 500", 4432, 5910, "18/02/2010", "thermophyle");
        med[1] = new Antiboitique("Augmentin", 4433, 7850, "14/11/2010", "thermophyle");
        med[2] = new AntiInflematoire("Maxilase", 4434, 3880, "28/04/2010", "steroidien", 8);
        med[3] = new Hamiopathique("Oscillococcinum", 4435, 8750, "23/12/2010", "saccharose");
        med[4] = new AntiInflematoire("Slupred", 4436, 13980, "28/04/2010", "steroidien", 6);

        for (Medicament m : med) {
            System.out.println(m.toString() + " Taxe: " + m.calculTaxApplique());
        }

        Pharmacie ph = new Pharmacie(100);
        for (Medicament m : med) {
            ph.ajouter(m);
        }

        ph.affichage("Antiboitique");
        System.out.println("Recherche Réf. 4436: " + ph.recherche(4436));
    }
}
