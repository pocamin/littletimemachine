Petit programme écrit au coin d'une table ayant pour but de faciliter la gestion de son temps au cours d'une journée.
Il permet de planifier ses taches et de sauvegarder en continu sur quel sujet on travaille. l'idée est de réduire au maximum le temps de configuration et de création de tâches.

Une autre version pour le travail en équipe est prévue http://code.google.com/p/littletimemachine/wiki/LittleTeamMachine.

# Actualité #

## 09/06/2008 : Little team machine en gestation ##

Une pré-version de little team machine sera bientôt disponible sur le SVN. Etant donnée les exigences techniques de cette version, j'en ai profité pour faire évoluer l'architecture de l'application avec du Spring. Cela permettra à terme de mieux pouvoir ajuster l'application aux exigences. (création de pool et compagnie) De plus j'ai implementé un joli modèle mvc avec des notification de modification du modèle sur une queue jms. Ca me semble assez propre.

Cette version s'installe sur un tomcat (tester sur une verion 6). Il faudra voir aussi pour un deploiement automatique via maven parce que construire le jar a la miminue ça va pas me botter longtemps...

Bien sûr, little team machine restera en GNU/GPL3 même si ce n'est pas forcement ma licence libre coup de coeur. Pour un petit projet elle me semble assez adaptée.

# Installation #

~~== Installation complète ==~~
~~Installer java > 1.5.~~

~~télécharger http://littletimemachine.googlecode.com/svn/trunk/livraison/ltimemachine-bin-0.1b.zip~~

~~lancer le bat~~

## via java web start ##

Installer java > 1.5.

Télécharger ce fichier dans un répertoire de votre choix :
http://littletimemachine.googlecode.com/svn/trunk/jws/launch.jnlp
Click droit > enregistrer sous ...

Double cliquez dessus et patientez

# Présentation #

## Ecran principal ##
Lors de l'ouverture du programme, les têches du jour sont affichées. Si on clique sur un bouton ce dernier passe en vert, la tâche est en cours.

![http://littletimemachine.googlecode.com/files/img1.jpg](http://littletimemachine.googlecode.com/files/img1.jpg)

## Création de tâche ##

Pour créer une nouvelle tâche, l'écran se veut très simple. **Il n'y a pour le moment pas de contrôles sur les champs !**
Le temps est toujours désigné en minute.

http://littletimemachine.googlecode.com/files/img4.JPG

## Le calendrier ##

Il permet de visualiser les tâches sur la semaine. Il permet également de modifier les informations sur les taches en cliquant sur ces dernières.

http://littletimemachine.googlecode.com/files/img2.JPG

## Le module de stat ##

Permet de visualiser rapidement le temps passées sur une période donnée. Il permet également dans les dernières versions d'extraire un fichier excel sur la période.

http://littletimemachine.googlecode.com/files/img3.JPG


# Si vous aimez ce projet #

Envoyez moi un mail pour me le dire !