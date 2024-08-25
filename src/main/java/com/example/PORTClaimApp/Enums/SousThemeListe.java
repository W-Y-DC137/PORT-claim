package com.example.PORTClaimApp.Enums;

public enum SousThemeListe {
    //theme : Achat de forfaits, facturation et paiements des factures PORTNET SA
    SOUS_THEME_1("Achat de forfaits"),
    SOUS_THEME_2("Facturation"),
    SOUS_THEME_3("Paiement en ligne multicanal des factures PORTNET"),

    //theme : Enlèvement de marchandise
    SOUS_THEME_4("Bon à délivrer BAD"),
    SOUS_THEME_5("Mainlevée"),
    SOUS_THEME_6("Manifeste MEAD"),
    SOUS_THEME_7("Notification automatique, à l'import, de l'avis d'arrivée des marchandises au port"),
    SOUS_THEME_8("Prise de Rendez-vous"),
    SOUS_THEME_9("Programmation des visites Fiches suiveuses"),
    SOUS_THEME_10("Résultats du contrôle"),

    //theme :Escales
    SOUS_THEME_11("Avis d'arrivée navire"),
    SOUS_THEME_12("Création de navire"),
    SOUS_THEME_13("Déclaration des déchets à une installation portuaire"),
    SOUS_THEME_14("Demande d'Attribution de Poste (DAP)"),
    SOUS_THEME_15("Manifeste Export"),
    SOUS_THEME_16("Manifeste Import"),

    //theme : Licence d'exportation
    SOUS_THEME_17("Imputation douanière"),
    SOUS_THEME_18("Souscription de la licence d'exportation"),
    SOUS_THEME_19("Visa MCE"),

    //theme : Paiement en ligne multicanal des autres émetteurs de factures (B. Veritas, TUV, APPLUS, ANP,…)
    SOUS_THEME_20("Paiement en ligne multicanal des factures ANP"),
    SOUS_THEME_21("Paiement en ligne multicanal des factures APPLUS"),
    SOUS_THEME_22("Paiement en ligne multicanal des factures de B. Veritas"),
    SOUS_THEME_23("Paiement en ligne multicanal des factures MSC"),
    SOUS_THEME_24("Paiement en ligne multicanal des factures TUV"),

    //theme : Préavis d'arrivée conteneurs au Port
    SOUS_THEME_25("Préavis d'arrivée conteneurs au Port"),

    //theme : Réclamation partenaire reçue par email
    SOUS_THEME_26("Réclamation partenaire reçue par email"),

    //theme : Souscription, Accès et Modification des informations clients
    SOUS_THEME_27("Abonnement aux services PORTNET"),
    SOUS_THEME_28("Barid E-Sign (Authentification Forte)"),
    SOUS_THEME_29("Changement de mot de passe"),
    SOUS_THEME_30("Changement ou rajout de profil utilisateur"),
    SOUS_THEME_31("Changement ou rajout de RIB"),
    SOUS_THEME_32("Demande d'autorisation d'accès au port (GAP)"),
    SOUS_THEME_33("Enregistrement en ligne des opérateurs économiques"),
    SOUS_THEME_34("Informations générales"),
    SOUS_THEME_35("Modification des données RC chez la douane"),
    SOUS_THEME_36("Suggestion"),

    //theme :Titres d'importation
    SOUS_THEME_37("Changement de guichet domiciliataire"),
    SOUS_THEME_38("Déclaration douanière (DUM)"),
    SOUS_THEME_39("Demande d'Agrément ANRT"),
    SOUS_THEME_40("DEMANDE DE FRANCHISE DOUANIERE"),
    SOUS_THEME_41("Domiciliation bancaire"),
    SOUS_THEME_42("Gestion des procurations transitaires via PortNet"),
    SOUS_THEME_43("Imputation douanière"),
    SOUS_THEME_44("Instruments de mesure"),
    SOUS_THEME_45("Modification des engagements d'importation non réglés"),
    SOUS_THEME_46("Modification des engagements d'importation réglés"),
    SOUS_THEME_47("Pré-domiciliation"),
    SOUS_THEME_48("Règlement financier"),
    SOUS_THEME_49("Souscription de titre d'importation"),
    SOUS_THEME_50("Visa MCE");
    private final String label;
    
    SousThemeListe(String label) {
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
