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

db.createCollection("randonnee");

db.randonnee.insertOne({
  _id: '1', 
  libelle: "La montagne Noire",
  participants_max: 2,
  point_depart: [43.408308198518846, 2.4458198213038966],
  point_arrive: [43.424080742263676, 2.462710777901859],
  nombre_jours: 1
});

db.randonnee.insertOne({
  _id: '2', 
  libelle: "Le Pacific Crest Trail (pour les nuls)",
  participants_max: 3,
  point_depart: [44.34974473375299, 2.5764601949018444],
  point_arrive: [44.34974473375299, 2.5764601949018444],
  nombre_jours: 2
});

db.randonnee.insertOne({
  _id: '3', 
  libelle: "Balade insolite d'Aveyron",
  participants_max: 3,
  point_depart: [44.360123830300076, 2.575580735324156],
  point_arrive: [44.360123830300076, 2.575580735324156],
  nombre_jours: 1
});

// Création de la table parcours
// l'utilisateur est inclut dans la site de participants.

db.createCollection("parcours");

// (Mock)

db.parcours.insertOne({
  _id: '1',
  idRando: '1', 
  id_utilisateur: '1', 
  libelle_randonnee: 'La montagne Noire',
  date_realisation: new ISODate("2026-01-18T14:10:30.123Z"),
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
  pointInterets: [{libelle : "Rodez", geo:[44.35083409171523,2.5730260710644473]},
          {libelle : "Le Monastère", geo :[44.34252528780783,2.5787285490859517]},
          {libelle : "Olemps", geo :[44.338902416241694,2.5534796512809237]},
          {libelle : "Les bois de Rouillac", geo : [44.326827380478704,2.536121210333704]},
          {libelle : "Castan", geo : [44.34223474120583,2.4892026498537234]},
          {libelle : "Cassarou", geo : [44.33126526040786,2.4768070240403404]},
          {libelle : "Moyrazès", geo : [44.34276725217214,2.4398242797784917]},
          {libelle : "Montès", geo : [44.32713060116157,2.455742937387953]}],
  date_realisation: new ISODate("2026-01-20T14:10:30.123Z"),
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
  idRando: '3', 
  id_utilisateur: '3', 
  libelle_randonnee: "Décourverte exceptionnelle des bâtiments universitaires", 
  pointInterets: [{libelle : "Entrée IUT", geo:[44.360080320786864, 2.575503749917175]},
          {libelle : "Batiment C", geo :[44.360287526289454, 2.575853338825084]},
          {libelle : "Batiment A", geo :[44.36019428390435, 2.5765978001776375]},
          {libelle : "Batiment B", geo : [44.35970734903577, 2.576335155660985]}],
  date_realisation: new ISODate("2026-01-22T14:10:30.123Z"),
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