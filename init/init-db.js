// initialisation d'une base de données mongo_db
db = db.getSiblingDB("Roadeo-db");

// TODO Création de la collection utilisateur
db.createCollection("utilisateur");

// TODO Ajout de quelque données tests
db.utilisateur.insertOne({_id:'1', patronyme:'Marcel Marcenac', mdp:'0123', adresse_mail:'marcel.marcenaac@le-goat.fr', domicile:'72 rue de la BD, Trifoullis-les-oies'})
db.utilisateur.insertOne({_id:'2', patronyme:'Jean-Michel Le Random', mdp:'4567', adresse_mail:'jm.lerandom@est-eternel.fr', domicile:'19 avenue du web, Pétaouchnok'})
db.utilisateur.insertOne({_id:'3', patronyme:'Un Utilisateur', mdp:'8910', adresse_mail:'un.utilisateur@normal.com', domicile:'47 boulvard des framework, Trou-la-ville'})

// Création de la collection randonnee
// Incomplete

db.createCollection("randonnee");

db.randonnee.insertOne({
  _id: '1', 
  libelle: "La montagne Noire"
});

db.randonnee.insertOne({
  _id: '2', 
  libelle: "Le Pacific Crest Trail (pour les nuls)"
});

db.randonnee.insertOne({
  _id: '3', 
  libelle: "Balade insolite à l'IUT de Rodez"
});

// Création de la table parcours
// Attention elle n'est pas complète !!!

// TODO point départ
// TODO point arrivée
// TODO date
// TODO points d'intérêts (optionnel)

// TODO voir si l'utilisateur est inclut dans la site de participants.

db.createCollection("parcours");

// (Mock)

db.parcours.insertOne({
  _id: '1', 
  id_utilisateur: '1', 
  libelle_randonnee: 'La montagne Noire', 
  participants: [
    {
      nom: "Marcel Jr", 
      prenom: "Marcenelle", 
      age: 12, 
      niveauEntrainement: "Debutant", 
      morphologie: "Legere"
    }, 
    {
      nom: "Marie Marcel", 
      prenom: "Montrane", 
      age: 39, 
      niveauEntrainement: "Entraine", 
      morphologie: "Moyenne"
    }
  ]
});

db.parcours.insertOne({
  _id: '2', 
  id_utilisateur: '3', 
  libelle_randonnee: "Balade insolite à l'IUT de Rodez", 
  participants: [
    {
      nom: "Le Marcheur", 
      prenom: "Pierre", 
      age: 61, 
      niveauEntrainement: "Sportif", 
      morphologie: "Moyenne"
    }, 
    {
      nom: "Tournepluie", 
      prenom: "M Tournesol", 
      age: 67, 
      niveauEntrainement: "Debutant", 
      morphologie: "Legere"
    }
  ]
});