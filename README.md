# Gestion de pharmacie — Java Swing

Projet pédagogique de programmation orientée objet : gestion d'un catalogue de médicaments en mémoire, avec une interface graphique Swing et une démonstration en console.

## Prérequis et démarrage

Installer un JDK 17 ou supérieur (les sources utilisent les expressions switch modernes). Depuis la racine du dépôt :

```sh
javac -encoding UTF-8 -d out medicaments/*.java
java -cp out medicaments.Test
```

Pour lancer l'interface graphique sur un poste disposant d'un environnement de bureau :

```sh
java -cp out medicaments.PharmacieGUI
```

Aucune bibliothèque externe ni base de données n'est nécessaire.

## Organisation

| Classe | Rôle |
| --- | --- |
| `Medicament` | Classe abstraite : libellé, référence, prix, date de fabrication et calcul de taxe. |
| `Antiboitique`, `AntiInflematoire`, `Hamiopathique` | Sous-classes spécialisées (noms conservés tels quels dans les sources). |
| `Remboursable` | Interface de calcul du remboursement. |
| `Pharmacie` | Catalogue à capacité fixe : ajout, recherche, suppression et affichage par catégorie. |
| `PharmacieGUI` | Interface Swing. |
| `Test` | Démonstration console avec des exemples prédéfinis. |

## Limites et vérifications

- Les données sont perdues à la fermeture ; aucun stockage persistant n'est implémenté.
- La démonstration console ne constitue pas une suite de tests automatisés avec assertions.
- Les saisies numériques invalides ou annulées ne sont pas toutes gérées dans l'interface.
- Le filtrage par catégorie écrit actuellement les résultats dans la console, et non dans le panneau de détails.
- Les taux de taxe et de remboursement sont des exemples pédagogiques, non des règles médicales, fiscales ou réglementaires validées.

Ce prototype n'est pas destiné à la gestion réelle d'une pharmacie.
