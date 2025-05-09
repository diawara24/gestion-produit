
# 📦 API de Gestion de Produits

Une API REST sécurisée construite avec **Spring Boot 3**, **JWT**, **Spring Security**, **Swagger**, **Prometheus**, **Grafana**, et conçue selon les **principes Clean Code & SOLID**.

---

## 🚀 Fonctionnalités

- 🔐 Authentification et autorisation via JWT
- 👥 Gestion des rôles : `SUPER_ADMIN`, `ADMIN`, `COLLABORATEUR`, `LECTEUR`
- 📦 CRUD complet : Produits, Catégories, Utilisateurs
- 📊 Monitoring : Prometheus + Grafana
- 📘 Documentation OpenAPI / Swagger
- ✅ Tests unitaires et d'intégration (JUnit + MockMvc)
- 🧠 Design Patterns : Service Layer, Strategy, Factory
- 🔍 Mapper générique DTO ↔ Entity avec ModelMapper

---

## 🛠️ Technologies

| Technologie      | Utilisation                                  |
|------------------|-----------------------------------------------|
| Spring Boot      | Framework principal de l'API                  |
| Spring Security  | Sécurité et rôles utilisateurs                |
| JWT              | Authentification stateless                    |
| MySQL            | Base de données relationnelle                 |
| Swagger (Springdoc) | Documentation API interactive           |
| Prometheus       | Monitoring des métriques                      |
| Grafana          | Visualisation des métriques                   |



---

## 📁 Structure du projet

```
src/main/java/com/example/gestionproduits
├── config/           # Config JWT, Swagger, Security
├── controller/       # Endpoints REST
├── dto/              # Data Transfer Objects
├── entity/           # Entités JPA
├── exception/        # Gestion d’erreurs
├── repository/       # Accès BDD
├── security/         # Filtre JWT
├── service/          # Couche métier
├── util/             # JWT Util, Mapper, Initialiseur
```

---

## 🔐 Authentification

- Endpoint public : `/api/auth/login`
- Envoie un `username/password` en POST
- Retourne un token JWT à inclure dans `Authorization: Bearer <token>`

---

## 📘 Documentation Swagger

- Accès : [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Affiche tous les endpoints protégés et non protégés
- Possibilité de tester l’API directement dans Swagger

---

## 📊 Monitoring Prometheus + Grafana

### 🔹 Prometheus

- Scrape automatiquement `/actuator/prometheus`
- Docker : `http://localhost:9090`

### 🔹 Grafana

- Accès : [http://localhost:3000](http://localhost:3000)
- Connexion : `admin / admin`
- Dashboard recommandé : ID **4701** (Micrometer JVM Dashboard)

---

## ⚙️ Lancer le projet

### 1. Démarrer l'API Spring Boot

```bash
./mvnw spring-boot:run
```

Ou via IDE (IntelliJ / VSCode)

### 2. Démarrer les outils de monitoring

```bash
docker compose up -d
```

---

## ✅ Tests

Avant d'exécuter les tests: changer le profile en test.

Dans le ficher .env 
```bash
APP_MODE=test
```

### Lancer tous les tests :

```bash
./mvnw test
```

### Voir la couverture :

```bash
open target/site/jacoco/index.html
```
---

## 💡 Exemples d'accès par rôle

| Rôle            | Accès                                          |
|-----------------|------------------------------------------------|
| `SUPER_ADMIN`   | Tout                                           |
| `ADMIN`         | CRUD Produit & Catégorie                       |
| `COLLABORATEUR` | POST / GET Produits                            |
| `LECTEUR`       | GET uniquement                                 |

---

## 🧑‍💻 Auteur

**Ton Nom** – [https://mourtallafatydiawara-dev.fr/](#)  
**Contact** – [mourtallafatydiawara@gmail.com](mailto:email@exemple.com)

