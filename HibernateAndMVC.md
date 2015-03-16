# Introduction #

Cet article s'intéresse à la modélisation de la liaison modèle/vue d'une interface graphique lourde (swing par exemple) et des problématiques de chargement "lazy" d'Hibernate.

# Problématiques #

## Notification des changements - ##

Le modèle MVC propose que lorsqu'un changement intervient dans le modèle, la vue en soit informé afin d'appliquer les modifications ad hoc.

De façon trivial les constats suivants sont posés :
  * Les vues n'ont pas toutes une gestion temps réel et les informations présentées à l'utilisateur n'ont pas toujours besoin d'être actualisées en cas de changement.
  * Seule une partie des informations modifiées dans le modèle impactera l'utilisateur.
  * L'experience utilisateur, notamment la réactivité de l'application, ne doit pas être sacrifier au profit du rechargement des données.

Afin de répondre a ces différentes problématiques nous proposons l'architecture suivante :