# MagazinPieseAuto

Aplicație desktop (Java Swing + MySQL) pentru gestionarea unui magazin de piese auto: piese, categorii, vânzări și utilizatori.

## Cerințe

- JDK 17 sau mai nou (`java`, `javac` în `PATH`)
- Docker (pentru baza de date MySQL)
- Fișierul jar MySQL Connector/J 9.4.0 — există deja în `~/.m2/repository` dacă proiectul a fost deschis în NetBeans/Maven; altfel, descarcă-l:
  ```bash
  mkdir -p ~/.m2/repository/com/mysql/mysql-connector-j/9.4.0
  curl -L -o ~/.m2/repository/com/mysql/mysql-connector-j/9.4.0/mysql-connector-j-9.4.0.jar \
    https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/9.4.0/mysql-connector-j-9.4.0.jar
  ```

## 1. Baza de date (Docker, portul 3307)

Aplicația folosește o bază de date MySQL numită `magazin_piese`. Datele de conectare se configurează
în fișierul `db.properties` (vezi secțiunea 2). În comenzile de mai jos, înlocuiește `parola_ta` cu parola aleasă.

Containerul este expus pe portul **3307** al calculatorului, ca să nu intre în conflict cu alte
instanțe MySQL care folosesc deja portul 3306.

```bash
docker run -d --name magazin-mysql -p 3307:3306 \
  -e MYSQL_ROOT_PASSWORD=parola_ta \
  -e MYSQL_DATABASE=magazin_piese \
  mysql:8.0
```

Așteaptă până când baza de date este pregătită:

```bash
until docker exec magazin-mysql mysqladmin -uroot -pparola_ta ping --silent; do sleep 2; done
```

Creează tabelele și datele de test (o singură dată):

```bash
docker exec -i magazin-mysql mysql -uroot -pparola_ta magazin_piese <<'SQL'
CREATE TABLE IF NOT EXISTS users(
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL,
  role VARCHAR(20) NOT NULL            -- 'ADMIN' sau 'EMPLOYEE'
);
CREATE TABLE IF NOT EXISTS categorii(
  id INT AUTO_INCREMENT PRIMARY KEY,
  nume VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS piese_auto(
  id INT AUTO_INCREMENT PRIMARY KEY,
  nume VARCHAR(100) NOT NULL,
  producator VARCHAR(100),
  pret DOUBLE,
  stoc INT,
  categorie_id INT,
  FOREIGN KEY (categorie_id) REFERENCES categorii(id)
);
CREATE TABLE IF NOT EXISTS vanzari(
  id INT AUTO_INCREMENT PRIMARY KEY,
  piesa_id INT,
  cantitate INT,
  data_vanzare DATE,
  FOREIGN KEY (piesa_id) REFERENCES piese_auto(id)
);
INSERT INTO users(username,password,role) VALUES
  ('admin','admin','ADMIN'),
  ('angajat','angajat','EMPLOYEE');
INSERT INTO categorii(nume) VALUES ('Frane'),('Motor'),('Suspensie');
INSERT INTO piese_auto(nume,producator,pret,stoc,categorie_id) VALUES
  ('Placute frana','Bosch',150,20,1),
  ('Filtru ulei','Mann',45.5,50,2),
  ('Amortizor','Sachs',320,8,3);
SQL
```

Comenzi utile:

```bash
docker start magazin-mysql      # pornește din nou containerul (de ex. după o repornire a calculatorului)
docker stop magazin-mysql       # oprește containerul
docker exec -it magazin-mysql mysql -uroot -pparola_ta magazin_piese   # consolă SQL
```

## 2. Configurare

Datele de conectare se citesc din fișierul `db.properties` din directorul rădăcină al proiectului
(clasa `src/main/java/util/DBConnection.java`). Fișierul nu este urcat pe GitHub; creează-l pornind de la exemplu:

```bash
cp db.properties.example db.properties
```

apoi completează-l:

```properties
# portul 3307 pentru Docker, 3306 pentru un MySQL instalat local
db.url=jdbc:mysql://localhost:3307/magazin_piese
db.user=root
db.password=parola_ta
```

## 3. Compilare și rulare

Din directorul rădăcină al proiectului (unde se află `db.properties`):

```bash
JAR=~/.m2/repository/com/mysql/mysql-connector-j/9.4.0/mysql-connector-j-9.4.0.jar
B=/tmp/magazin-build
rm -rf $B && mkdir -p $B/classes

# Compilare
javac -d $B/classes -cp $JAR $(find src/main/java -name '*.java')

# Rulare
java -cp $B/classes:$JAR Main
```

Punctul de intrare este clasa `Main` (deschide fereastra de autentificare).
`com.mycompany.magazinpieseauto.MagazinPieseAuto` este doar șablonul „Hello World” generat de NetBeans.

**NetBeans / Maven:** `pom.xml` cere Java 23 (`maven.compiler.release`), iar acțiunea de rulare
din NetBeans (`nbactions.xml`) folosește `Main` ca clasă principală. Rularea din IDE
necesită JDK 23+ și Maven; comenzile `javac` de mai sus funcționează cu JDK 17.

## 4. Utilizarea aplicației

### Autentificare

| Utilizator | Parolă    | Rol      |
|------------|-----------|----------|
| `admin`    | `admin`   | ADMIN    |
| `angajat`  | `angajat` | EMPLOYEE |

Apasă **Login** sau Enter. Dacă datele sunt greșite, apare mesajul „Date incorecte!”.

### Roluri

- **ADMIN** — vede toate butoanele din meniu, inclusiv **Utilizatori**.
- **EMPLOYEE** — la fel ca administratorul, doar că butonul **Utilizatori** este ascuns.

### Fereastra principală

Meniul din stânga schimbă panoul afișat în dreapta:

- **Piese**
  - Tabel cu toate piesele: ID, nume, producător, preț, stoc, ID categorie.
  - Completează *Nume, Producator, Pret, Stoc, Categorie ID* și apasă **Adauga** pentru a adăuga o piesă.
  - Selectează un rând ca să-i încarci datele în câmpuri, apoi apasă **Modifica** sau **Sterge**.
  - **Cauta**: caută piese după nume (potrivire parțială).
- **Categorii**
  - Afișează, adaugă (**Adauga**), redenumește (**Modifica**) și șterge (**Sterge**) categorii.
  - Numele duplicate sunt respinse („Categoria exista deja!”), fără a ține cont de majuscule.
- **Vanzari**
  - Alege o piesă și introdu *Cantitate*; prețul pe bucată și totalul se calculează automat, iar data este cea de azi.
  - **Inregistreaza vanzare!** salvează vânzarea și scade stocul piesei.
  - Dacă vinzi mai mult decât stocul disponibil, apare mesajul „Stoc insuficient!”.
- **Utilizatori** (doar pentru ADMIN)
  - Afișează utilizatorii și permite adăugarea unora noi (nume de utilizator, parolă, rol ADMIN/EMPLOYEE).
- **Logout** — te întoarce la fereastra de autentificare.

## Observații

- Parolele sunt salvate și comparate în text simplu (necriptate).
- Erorile bazei de date sunt doar afișate în consolă (stack trace), nu și în interfață.
