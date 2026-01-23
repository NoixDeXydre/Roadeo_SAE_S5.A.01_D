// initialisation d'une base de données mongo_db
db = db.getSiblingDB("Roadeo-db");

// TODO Création de la collection utilisateur
db.createCollection("utilisateur");

// TODO Ajout de quelque données tests
db.utilisateur.insertOne({
  _id:'1',
  patronyme:'Marcel Marcenac',
  mdp:'0123',
  adresse_mail:'marcel.marcenaac@le-goat.fr',
  domicile:'72 rue de la BD, Trifoullis-les-oies',
  nom: "Marcenac", 
  prenom: "Marcel", 
  age: 40, 
  niveauEntrainement: "Entraine", 
  morphologie: "Forte"
})

db.utilisateur.insertOne({
  _id:'2',
  patronyme:'Jean-Michel Le Random',
  mdp:'4567',
  adresse_mail:'jm.lerandom@est-eternel.fr',
  domicile:'19 avenue du web, Pétaouchnok',
  nom: "Le Random", 
  prenom: "Jean-Michel", 
  age: 23, 
  niveauEntrainement: "Entraine", 
  morphologie: "Moyenne"
})
db.utilisateur.insertOne({
  _id:'3',
  patronyme:'Un Utilisateur',
  mdp:'8910',
  adresse_mail:'un.utilisateur@normal.com',
  domicile:'47 boulvard des framework, Trou-la-ville',
  nom: "Utilisateur", 
  prenom: "Un", 
  age: 39, 
  niveauEntrainement: "Debutant", 
  morphologie: "Legere"
})

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
  libelle: "Balade insolite d'Aveyron"
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
  idRando: '1', 
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
  idRando: '2', 
  id_utilisateur: '3', 
  libelle_randonnee: "Parcours Découverte", 
  pointInterets: [{libelle : "Rodez", geo:[2.5730260710644473,44.35083409171523]},
          {libelle : "Le Monastère", geo :[2.5787285490859517,44.34252528780783]},
          {libelle : "Olemps", geo :[2.5534796512809237,44.338902416241694]},
          {libelle : "Les bois de Rouillac", geo : [2.536121210333704,44.326827380478704]},
          {libelle : "Castan", geo : [2.4892026498537234,44.34223474120583]},
          {libelle : "Cassarou", geo : [2.4768070240403404,44.33126526040786]},
          {libelle : "Moyrazès", geo : [2.4398242797784917,44.34276725217214]},
          {libelle : "Montès", geo : [2.455742937387953,44.32713060116157]}],
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
    },
    {
      nom: "L'étudiant", 
      prenom: "Marcel", 
      age: 23, 
      niveauEntrainement: "sportif", 
      morphologie: "Legere"
    }
  ]
});

db.parcours.insertOne({
  _id: '3',
  idRando: '2', 
  id_utilisateur: '3', 
  libelle_randonnee: "Décourverte exceptionnelle des bâtiments universitaires", 
  pointInterets: [{libelle : "Entrée IUT", geo:[2.575503749917175, 44.360080320786864]},
          {libelle : "Batiment C", geo :[2.575853338825084, 44.360287526289454]},
          {libelle : "Batiment A", geo :[2.5765978001776375, 44.36019428390435]},
          {libelle : "Batiment B", geo : [ 2.576335155660985,44.35970734903577]}],
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