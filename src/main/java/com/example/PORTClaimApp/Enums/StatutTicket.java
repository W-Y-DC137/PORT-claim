package com.example.PORTClaimApp.Enums;

public enum StatutTicket {
    Nouveau,//Le ticket n'a pas encors été affecté à un agent , et son traitement n'a pas commencé .
    Affecte,// Le ticket a été assigné à un agent, et le traitement a débuté.
    En_Attente,//L'agent a demandé des informations ou des documents supplémentaires au client et attend sa réponse.
    Repondu,// l'agent à proposer une solution  au client et il attent sa confirmation .
    Reouvert,//l'agent à proposer une solution  au client , mais le problème persiste.
    Resolu,//le client à exprimer son acceptation de la solution.
    Cloture//Un ticket est clôturé s'il reste plus de 7 jours dans l'état "En attente" ,"Répondu" ou "Résolu".

}
