// initialisation d'une base de données mongo_db
db = db.getSiblingDB("Roadeo-db");

// TODO Création de la collection utilisateur
db.createCollection("utilisateur");

// TODO Ajout de quelque données tests
db.utilisateur.insertOne({_id:'1', patronyme:'Marcel Marcenac', mdp:'0123', adresse_mail:'marcel.marcenaac@le-goat.fr', domicile:'72 rue de la BD, Trifoullis-les-oies'})
db.utilisateur.insertOne({_id:'2', patronyme:'Jean-Michel Le Random', mdp:'4567', adresse_mail:'jm.lerandom@est-eternel.fr', domicile:'19 avenue du web, Pétaouchnok'})
db.utilisateur.insertOne({_id:'3', patronyme:'Un Utilisateur', mdp:'8910', adresse_mail:'un.utilisateur@normal.com', domicile:'47 boulvard des framework, Trou-la-ville'})