# 1. Introduction #

Little team machine (LTM) se veut être le pendant de Little Time Machine mais pour une gestion d'équipes.
Ainsi il permet de rajouter à l'existant la création de groupe et d'utilisateur ainsi que la création visualisation des taches afférant aux autres utilisateurs.

# 2. Avertissement #

Ce document est une pré version et peut évoluer fortement au fur et à mesure des versions.

# 3. Installation et configuration #


Serveur d’application : Le serveur d’application doit être installé sur _TODO_.
Il se présente sous la forme d’un fichier ear.

_TODO : configuration de la base de données, des realm, etc_

# 4. Utilisateurs et groupes #

Un utilisateur par défaut est créé dans LTM avec pour informations de connexion :

Login	Admin
MDP	Admin

A la première connexion, le mot de passe sera à changer. Cet utilisateur fait partie du groupe ADMIN. Seuls les utilisateurs de ce groupe peuvent créer d’autres groupes et créer des utilisateurs appartenant à ce groupe.

Un utilisateur n’est rattaché qu’a un seul groupe – c’est une limitation mais à priori cela devrait permettre de répondre à 90 % des cas.

Un groupe peut être lié à d’autres groupes. Cela permet de visualiser les calendriers et les autres tâches des groupes

## 4.1. Création d'un groupe ##

La création d’un groupe ne peut se faire que par un utilisateur appartenant au groupe admin.

Dans l’interface principale de LTM ouvrir configuration > groupe > créer

Pour créer un groupe, il suffit de rentrer son nom dans l’écran ci-dessous et de valider.

On peut sélectionner également les groupes liés à ce groupe.

_TODO : ajouter une image de la création du groupe_



## 4.2. Création d'un utilisateur ##

Pour créer un utilisateur, il suffit de remplir dans l’écran ci-dessous :
  * Le nom de l’utilisateur
  * Son mot de passe
  * Son mail
  * Les droits à créer des utilisateurs.
  * Les droits à visualiser/créer/assigner des taches à tout le groupe
  * Les droits à visualiser/créer/assigner des taches à tous les groupes liés au groupe de l'utilisateur.

_TODO : ajouter une image sur la création d'un utilisateur_

# 5. Fonctionnement général #

Little Team Machine se présente de la même façon que Litlle Time machine

Cependant de nouvelles fonctions apparaissent :

## 5.1. Ecran de visualisation des infos et des alertes ##

Les alertes et les infos sont des informations que reçoit l'utilisateur lorsqu'un autre  effectue des modifications l'impactant.

Les alertes sont notifiés par un popup à l'utilisateur soit à l création si l'utilisateur est connecté soit à la connexion. Tandis que les infos sont seulement notifié à l'utilisateur dans l'écran de visualisation.

Dans l’interface principale de LTM ouvrir configuration > opération >  alertes et infos. L'écran permet de visualiser et d'effacer les alertes et les infos de l'utilisateur.

_TODO : ajouter une image sur l'écran alerte et info_

## 5.2. Ecran de création d'une tâche ##

_TODO : ajouter une image sur la création d'une tâche_

Dans l'écran de création d'une tâche, les modifications sont les suivantes
  * **Assigner une tache :** si l'utilisateur dispose des droits d'assigner des taches, il peut créer une tache à un autre utilisateur. Sinon, cette option n'est pas visible. Lorsqu'une tache est assigné à un utilisateur, ce dernier reçoit une alerte.
  * **Option de création :** Une tâche, une catégorie ou un thème peuvent être réutilisés par l'utilisateur (option U), tout le groupe (option G), ou tous les groupes liés (option O) en fonction des droit de l'utilisateur. Lorsqu'une tache est assigné à un utilisateur, ce dernier reçoit une alerte.

## 5.3. Ecran de visualisation du calendrier ##

Dans l'écran de visualisation du calendrier, l'option permettant de choisir l'utilisateur apparait. La modification des données est conditionnée par les droits de l'utilisateur.

## 5.4. Ecran de statistiques ##

Dans l'écran de statistiques, selon les droits de l'utilisateur, on peut
  * Choisir si les statistiques concernent l'utilisateur, le groupe ou les groupes liés.
  * Visualiser les statistiques en tant qu'un autre utilisateur.

Dans l'extraction Excel, si les statistiques concernent le groupe ou les groupes liés, il y aura une colonne par utilisateur avec un cumule du temps passé sur la période.