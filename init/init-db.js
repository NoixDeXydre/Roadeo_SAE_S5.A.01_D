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
  morphologie: "Fort",
  sac: []
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
  morphologie: "Moyen",
  sac: []
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
  morphologie: "Leger",
  sac: []
})

// Création de la collection randonnee

db.createCollection("randonnee");

db.randonnee.insertOne({
  _id: '1', 
  id_utilisateur: '1', 
  libelle: "La montagne Noire",
  participants_max: 2,
  point_depart: {libelle: "depart", geo:[43.408308198518846, 2.4458198213038966]},
  point_arrive: {libelle: "arrive", geo:[43.424080742263676, 2.462710777901859]},
  nombre_jours: 1,
  participants: [
  {
    nom: "Le Marcheur", 
    prenom: "Pierre", 
    age: 61, 
    niveauEntrainement: "Sportif", 
    morphologie: "Moyen",
    sac: []
  }, 
  {
    nom: "Tournepluie", 
    prenom: "M Tournesol", 
    age: 67, 
    niveauEntrainement: "Debutant", 
    morphologie: "Leger",
    sac: []
  }]
});

db.randonnee.insertOne({
  _id: '2', 
  id_utilisateur: '3', 
  libelle: "Le Pacific Crest Trail (pour les nuls)",
  participants_max: 3,
  point_depart: {libelle: "depart", geo:[44.34974473375299, 2.5764601949018444]},
  point_arrive: {libelle: "arrive", geo:[44.34974473375299, 2.5764601949018444]},
  nombre_jours: 2,
  participants: [
    {
      nom: "Le Marcheur", 
      prenom: "Pierre", 
      age: 61, 
      niveauEntrainement: "Sportif", 
      morphologie: "Moyen",
      sac: []
    }, 
    {
      nom: "Tournepluie", 
      prenom: "M Tournesol", 
      age: 67, 
      niveauEntrainement: "Debutant", 
      morphologie: "Leger",
      sac: []
    },
    {
      nom: "L'étudiant", 
      prenom: "Marcel", 
      age: 23, 
      niveauEntrainement: "sportif", 
      morphologie: "Leger",
      sac: []
    }
  ]
});

db.randonnee.insertOne({
  _id: '3',
  id_utilisateur: '3',  
  libelle: "Balade insolite d'Aveyron",
  participants_max: 3,
  point_depart: {libelle: "depart", geo:[44.360123830300076, 2.575580735324156]},
  point_arrive: {libelle: "arrive", geo:[44.360123830300076, 2.575580735324156]},
  nombre_jours: 1,
  participants: [
    {
      nom: "Le Marcheur", 
      prenom: "Pierre", 
      age: 61, 
      niveauEntrainement: "Sportif", 
      morphologie: "Moyen",
      sac: []
    }, 
    {
      nom: "Tournepluie", 
      prenom: "M Tournesol", 
      age: 67, 
      niveauEntrainement: "Debutant", 
      morphologie: "Leger",
      sac: []
    }
  ]
});

// Création de la table parcours
// l'utilisateur est inclut dans la site de participants.

db.createCollection("parcours");

// (Mock)
//etat :
// 1 -> parcours à valider
// 2 -> sac à faire
// 3 -> prêt pour la randonnée

db.parcours.insertOne({
  _id: '1',
  idRando: '1', 
  libelle_randonnee: 'La montagne Noire', 
  etat: 2,
  selection: false,
  date_realisation: new ISODate("2026-01-18T14:10:30.123Z")
});

db.parcours.insertOne({
  _id: '2',
  idRando: '2',
  libelle_randonnee: "Parcours Découverte", 
  etat: 1,
  pointInterets: [{libelle : "Rodez", geo:[2.5730260710644473,44.35083409171523]},
          {libelle : "Le Monastère", geo :[2.5787285490859517,44.34252528780783]},
          {libelle : "Olemps", geo :[2.5534796512809237,44.338902416241694]},
          {libelle : "Les bois de Rouillac", geo : [2.536121210333704,44.326827380478704]},
          {libelle : "Castan", geo : [2.4892026498537234,44.34223474120583]},
          {libelle : "Cassarou", geo : [2.4768070240403404,44.33126526040786]},
          {libelle : "Moyrazès", geo : [2.4398242797784917,44.34276725217214]},
          {libelle : "Montès", geo : [2.455742937387953,44.32713060116157]}],
  selection: true,
  date_realisation: new ISODate("2026-01-22T14:10:30.123Z")
});

db.parcours.insertOne({
  _id: '3',
  idRando: '3', 
  libelle_randonnee: "Décourverte exceptionnelle des bâtiments universitaires",
  etat: 1, 
  pointInterets: [{libelle : "Entrée IUT", geo:[2.575503749917175, 44.360080320786864]},
          {libelle : "Batiment C", geo :[2.575853338825084, 44.360287526289454]},
          {libelle : "Batiment A", geo :[2.5765978001776375, 44.36019428390435]},
          {libelle : "Batiment B", geo : [ 2.576335155660985,44.35970734903577]}],
  selection: false,
  date_realisation: new ISODate("2026-01-22T14:10:30.123Z")
});

db.createCollection("produits");

db.produits.insertOne({
categorie: "nourriture",
denomination: "Repas lyophilisé - pâtes à la bolognaise - 120g",
nom : "pâte bolognaise",
description : "Notre équipe passionnée de trekking a conçu ce repas pour vos activités physiques (trek) avec un apport énergétique adapté pour un poids minimal : 140 g.",
masse : 140, // en gramme
nutrition: 381, // kcal
prix : 8.49 // euros
})

db.produits.insertOne({
  categorie: "nourriture",
  denomination: "Soupe lyophilisée - Goulash au poulet - 50 g",
  nom: "Goulash",
  description: "Notre équipe passionnée de trekking a conçu cette soupe lyophilisée pour vos activités physiques avec un apport énergétique adapté pour un poids minimal : 70 g.",
  masse: 700,
  nutrition: 190,
  prix: 7.49
})

db.produits.insertOne({
  categorie: "nourriture",
  denomination: "Dessert lyophilisé - Riz au lait à la vanille - 45 g",
  nom: "Riz au lait",
  description: "Notre équipe passionnée de trekking a conçu ce dessert pour vos activités physiques (trek) avec un apport énergétique adapté pour un poids minimal : 57 g.",
  masse: 570,
  nutrition: 191,
  prix: 4.99
})

db.produits.insertOne({
  categorie: "bivoique",
  denomination: "Tente de camping 2 places, MH100",
  nom: "tente",
  description: "Une tente accessible. Sa structure en dôme autoportante vous permet de la déplacer une fois montée pour choisir le meilleur emplacement.",
  masse: 2600,
  nutrition: 0,
  prix: 29.99
})

db.produits.insertOne({
  categorie: "extra",
  denomination: "Appareil photo compact Thomson THR317",
  nom: "appareil photo",
  description: "Ne loupait plus jamais de licorne, grâce à cette appareil performant.",
  masse: 800,
  nutrition: 0,
  prix: 100
})

db.produits.insertOne({
  categorie: "extra",
  denomination: "Raclette Bougie à Revêtement Antiadhésif",
  nom: "appareil à raclette",
  description: "Fini le fil électrique qui gêne pour passer,fini les odeurs de raclette et la chaleur étouffante dans toute la maison. ipow appareil raclette à la bougie est parfait pour une petite raclette en intérieur comme à table, devant télé, au bureau, dans le fourgon aménagé/camping cars/vans; ou en extérieur, tels que dans le jardin, sur la plage, en bivouac, au sommet des pistes après la randonnée en raquettes.",
  masse: 700,
  nutrition: 0,
  prix: 23.56
})

db.produits.insertOne({
  categorie: "extra",
  denomination: "Couteau Suisse 7,5cm 14 fonctions Victorinox CLIMBER",
  nom: "couteau suisse",
  description: "Conçu pour avoir sous la main tous les outils nécessaire pendant la chasse et toutes les activités outdoor",
  masse: 82,
  nutrition: 0,
  prix: 34.99
})

db.produits.insertOne({
  categorie: "bivoique",
  denomination: "Sac de couchage de camping 20°C, Basic",
  nom: "sac de couchage",
  description: "Nos concepteurs campeurs ont conçu ce sac de couchage Arpenaz 20° pour dormir confortablement en camping à des températures proches de 20°C.",
  masse: 750,
  nutrition: 0,
  prix: 13.99
})

db.produits.insertOne({
  categorie: "bivoique",
  denomination: "Matelas gonflable de camping 2 personnes, 190x120 cm, Air basic",
  nom: "matelas gonflable",
  description: "Nos concepteurs campeurs ont conçu ce matelas Air Basic pour deux campeurs recherchant le confort d'un matelas gonflable au prix le plus accessible.",
  masse: 3500,
  nutrition: 0,
  prix: 23.99
})

db.produits.insertOne({
  categorie: "bivoique",
  denomination: "POMPE À PIED POUR LE CAMPING - RECOMMANDÉE POUR LES MATELAS GONFLABLES",
  nom: "pompe à pied",
  description: "Nos concepteurs campeurs ont conçu cette pompe à pied pour gonfler vos matelas de camping.",
  masse: 822,
  nutrition: 0,
  prix: 9.99
})

db.produits.insertOne({
  categorie: "bivoique",
  denomination: "Popote 100 de camping et bivouac en acier inox - 1 personne - 6 éléments.",
  nom: "Popote",
  description: "Nos concepteurs passionnés ont développé une popote simple, complète et astucieuse pour permettre à 1 personne de cuisiner et de manger dehors.",
  masse: 300,
  nutrition: 0,
  prix: 11.99
})

db.produits.insertOne({
  categorie: "bivoique",
  denomination: "Objet très lours pour test",
  nom: "15 000",
  description: "Pas de détail.",
  masse: 15000,
  nutrition: 0,
  prix: 11.99
})
