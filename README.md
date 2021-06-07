Rozwiązania zadań:

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