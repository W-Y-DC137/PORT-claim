package com.example.PORTClaimApp.Enums;

import lombok.Getter;

public enum ThemeListe {
    THEME_1("Achat de forfaits, facturation et paiements des factures PORTNET SA"),
    THEME_2("Enlèvement de marchandise"),
    THEME_3("Escales"),
    THEME_4("Licence d'exportation"),
    THEME_5("Paiement en ligne multicanal des autres émetteurs de factures (B. Veritas, TUV, APPLUS, ANP,…)"),
    THEME_6("Préavis d'arrivée conteneurs au Port"),
    THEME_7("Réclamation partenaire reçue par email"),
    THEME_8("Souscription, Accès et Modification des informations clients"),
    THEME_9("Titres d'importation");

    private final String label;

    ThemeListe(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
