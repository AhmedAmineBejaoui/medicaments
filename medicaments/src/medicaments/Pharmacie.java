package medicaments;

import java.util.Arrays;

public class Pharmacie {
    private final Medicament[] lesMedicaments;
    private int nbMedicaments;

    public Pharmacie(int taille) {
        lesMedicaments = new Medicament[taille];
        nbMedicaments = 0;
    }

    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < nbMedicaments; i++) {
            resultat.append(lesMedicaments[i].toString()).append("\n");
        }
        return resultat.toString();
    }

    public void ajouter(Medicament medicament) {
        if (nbMedicaments < lesMedicaments.length) {
            lesMedicaments[nbMedicaments++] = medicament;
            System.out.println("Ajout effectué avec succès.");
        } else {
            System.out.println("Erreur : L'ajout est impossible...");
        }
    }

    public int recherche(int reference) {
        for (int i = 0; i < nbMedicaments; i++) {
            if (lesMedicaments[i].reference == reference) return i;
        }
        return -1;
    }

    public void supprimer(int reference) {
        int indice = recherche(reference);
        if (indice != -1) {
            System.arraycopy(lesMedicaments, indice + 1, lesMedicaments, indice, nbMedicaments - indice - 1);
            nbMedicaments--;
            System.out.println("Suppression effectuée avec succès.");
        } else {
            System.out.println("Erreur : Référence introuvable.");
        }
    }

    public void affichage(String categorie) {
        System.out.println("Liste des médicaments de catégorie " + categorie + ":");
        for (int i = 0; i < nbMedicaments; i++) {
            if (lesMedicaments[i].getClass().getSimpleName().equals(categorie)) {
                System.out.println(lesMedicaments[i].toString());
            }
        }
    }

    // Méthode ajoutée pour récupérer les médicaments
    public Medicament[] getMedicaments() {
        return Arrays.copyOf(lesMedicaments, nbMedicaments);
    }
}
