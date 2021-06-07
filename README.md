# Cosmos DB

![hadoop](./imgs/cosmosDB.png)

### Wykonali

- [Patryk Cebrat](https://github.com/patrykce)
- [Dawid Szczerba](https://github.com/DawidSzczerba)

### Prezentacja 
 - [Dawid Szczerba, Patryk Cebrat - CosmosDB Tutorial](https://github.com/patrykce/CosmosDBTutorial/blob/master/Patryk%20Cebrat%20Dawid%20Szczerba%20-%20CosmosDB.pdf)

### Wymagane narzędzia

- Maven
- Java w wersji 8 lub większej
- Edytor - zalecany [IntelliJ IDEA](https://www.jetbrains.com/idea/)

Program ma skonfigurowane zmienne do bazy danych na chmurze, nie ma potrzeby zakładania konta w azure cosmosDB na potrzeby tutoriala.</br>

Minitutorial został przygotowany w oparciu o oficjalną dokumentację Microsoft cosmosDB. </br>
Zadania zostały przygotowane na podstawie samouczka:

- https://docs.microsoft.com/pl-pl/azure/cosmos-db/create-sql-api-java?tabs=sync&fbclid=IwAR3603TjOkTd6DNqfn23IcuoyuTsUdhxNE-4sRRf7ISZcosk8CitJKKF07o


### Rozwiązania zadań

Zadania do zaimplementowania znajdują się w klasie: `src/main/java/com/azure/cosmos/sample/sync/Task.java`
</br>Oznaczono je komentarzem `TODO`. Poniżej przedstawiono prozycje rozwiązań.

Rozwiązania:

<details><summary>Zadanie 1</summary>
<p>

```sh
String query = "SELECT * FROM Family";
```

</p>
</details>

<details><summary>Zadanie 2</summary>
<p>

```sh
String query = "SELECT * FROM Family f WHERE f.lastName IN ('Andersen', 'Wakefield')";
```

</p>
</details>

<details><summary>Zadanie 3</summary>
<p>

```sh
String query = "SELECT * FROM Family f ORDER BY f.children[0].grade";
```

</p>
</details>

<details><summary>Zadanie 4</summary>
<p>

```sh
String query = "SELECT uniqueLastName FROM ( SELECT * FROM Family f  GROUP BY f.lastName) AS uniqueLastName";
```

</p>
</details>

### Demo
<details><summary>Kliknij tutaj aby krok po kroku móc odtworzyć demo z lekcji + mieć możliwość zrobienia kilka zadań</summary>
<p>


Krótki poradnik jak założyć konto azure i konto cosmos db
https://azure.microsoft.com/pl-pl/free/
https://docs.microsoft.com/pl-pl/azure/cosmos-db/create-cosmosdb-resources-portal
  

Po stworzeniu konta cosmos db w menu po lewej stronie wybieramy **data explorer** a następnie **new container**
Przy tworzeniu kontenera:
Share Throghput across containers - odznaczone (omówienie prezentacja slajdy 17-19 - nie chcemy współdzielonej przepustowości)
 
W demo nazywam tak baze danych, kontener i partition key:
nazwa bazy danych - Products
Container ID (unikalna nazwa kontenera w tej bazie danych) - Clothing  
Partition Key - /productId - to jest klucz partycji, który decyduje o podziale partycji logicznych w naszym kontenerze 
  (slajd 25 prezentacji - praktyki, które należy stosować przy wybieraniu klucza partycji)
 
Container throughput - wybieramy manual - potem możemy wybrać dedykowaną dla kontenera przepustowość - wystarczy 400RU

Plik z elementami do wstawienia i z przykladowymi query:https://github.com/patrykce/CosmosDBTutorial/blob/master/DemoInsertData.txt
W celu stworzenia nowego elementu klikamy: products -> items -> new item 
Kopiujemy element 1 - zapisujemy i to samo robimy dla elementu 2

 
Uruchamiamy przykładowe query z pliku DemoInsertData.txt
Proste zadania:
  1. Napisz query, które dla wszystkich elementów w kontenerze, które posiadają pole weight zwróci wartość weight
  2. Napisz query, które zwróci opis dla elementu o id=1
  3. Napisz query które zwróci dla każdego elementu cene, opis, productId i uporządkuje wyniki rosnąco
Plik z rozwiązaniami: https://github.com/patrykce/CosmosDBTutorial/blob/master/Query%20-%20rozwiazania.txt

W scale&settings możemy ustawić ttl - omawiane w slajdzie 30
W menu po lewej stronie możemy Replicate Data Globally - umożliwia nam to replikacje kontenera do innego data center - omowione w slajdzie 31 



</p>
</details>
