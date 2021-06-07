# Cosmos DB

![hadoop](./imgs/cosmosDB.png)

### Wykonali

- [Patryk Cebrat](https://github.com/patrykce)
- [Dawid Szczerba](https://github.com/DawidSzczerba)

### Wymagane narzędzia

- Maven
- Java w wersji 8 lub większej
- Edytor - zalecany [IntelliJ IDEA](https://www.jetbrains.com/idea/)

Program ma skonfigurowane zmienne do bazy danych na chmurze, nie ma potrzeby zakładania konta w azure cosmosDB na potrzeby tutoriala.</br>

Minitutorial został przygotowany w oparciu o oficjalną dokumentację Microsoft cosmosDB. </br>
Zadania zostały przygotowane na podstawie samouczka:

- https://docs.microsoft.com/pl-pl/azure/cosmos-db/create-sql-api-java?tabs=sync&fbclid=IwAR3603TjOkTd6DNqfn23IcuoyuTsUdhxNE-4sRRf7ISZcosk8CitJKKF07o

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