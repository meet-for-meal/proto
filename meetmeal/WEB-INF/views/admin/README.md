backend
=======

Full-ajax backend

## Comportements

### DataType

Si l'API se situe sur le même nom de domaine, les requêtes ajax doivent être effectuée en `json`. Sinon, `jsonp` doit être utilisé du aux restrictions de requêtes cross-domain.

### Utilisateurs

Il doit être possible d'afficher la liste des utilisateurs, afficher un utilisateur (pour pouvoir l'éditer), de le mettre à jour et de le supprimer. La création est nécessaire mais n'a pas à figurer sur le backend.

Les commandes ci-dessous ne doivent pas forcément respecter "l'orthographe" des URL mais doit pouvoir renvoyer *au moins* les données suivantes.

#### GET /users

    [
      {
        "id": 1,
        "firstname": "Rémy",
        "lastname": "Funky",
        "username": "rhannequin",
        "created_at": "2013-06-11T10:00:00.000Z"
      },
      {
        "id": 2,
        "firstname": "Hervé",
        "lastname": "Jagbomb",
        "username": "jagbomb",
        "created_at": "2013-06-11T12:30:00.000Z"
      }
    ]

#### GET /users/1

    {
      "id": 1,
      "firstname": "Rémy",
      "lastname": "Funky",
      "username": "rhannequin",
      "created_at": "2013-06-11T10:00:00.000Z"
    }

#### PUT /users/1

    200 OK

    {
      "status": "ok"
    }

#### DELETE /users/1

    200 OK

    {
      "status": "ok"
    }

### Restaurants partanaires

Il doit être possible d'afficher la liste des restaurants partenaires, afficher un restaurant (pour pouvoir l'éditer), de le mettre à jour et de le supprimer. La création est nécessaire mais n'a pas à figurer sur le backend.

Les commandes ci-dessous ne doivent pas forcément respecter "l'orthographe" des URL mais doit pouvoir renvoyer *au moins* les données suivantes.

#### GET /venues

    [
      {
        "id": 1,
        "foursquare_id": "4cf6ef5d1801a143eedbecd4",
        "partnership": "medium",
        "created_at": "2013-06-11T10:00:00.000Z"
      },
      {
        "id": 2,
        "foursquare_id": "4ba88bdcf964a52078df39e3",
        "partnership": "high",
        "created_at": "2013-06-11T12:30:00.000Z"
      }
    ]

#### GET /venues/1

    {
      "id": 1,
      "foursquare_id": "4cf6ef5d1801a143eedbecd4",
      "partnership": "medium",
      "created_at": "2013-06-11T10:00:00.000Z"
    }

#### PUT /venues/1

    200 OK

    {
      "status": "ok"
    }

#### DELETE /venues/1

    200 OK

    {
      "status": "ok"
    }
