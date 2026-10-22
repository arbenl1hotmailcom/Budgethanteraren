# Budgethanteraren

## Beskrivning

Budgethanteraren är ett konsolprogram skrivet i Java där användaren kan hålla koll på sina inkomster och utgifter.

Programmet använder Maven och innehåller ett generiskt repository för att lagra transaktioner.

## Funktioner

* Lägga till inkomster och utgifter.
* Visa alla transaktioner.
* Beräkna saldo.
* Visa sammanställning per kategori.
* Filtrera inkomster och utgifter.
* Spara och läsa transaktioner från en CSV-fil.

## Köra programmet

Öppna projektet i IntelliJ IDEA och kör `Main.java`.

Använd menyn för att välja önskad funktion.

## Testning

Jag har använt JUnit 5 för att testa programmets funktioner.

Jag har skapat två testklasser:

**RepositoryTest**

* Testar att lägga till objekt.
* Testar att hämta objekt.
* Testar filtrering med lambda.

**BudgetManagerTest**

* Testar beräkning av saldo.
* Testar saldo när budgeten är tom.
* Testar sammanställning per kategori.
* Testar ogiltiga belopp med `assertThrows()`.

Jag har använt Arrange-Act-Assert för att strukturera testerna.

## Felsökning och bugg

Under testningen upptäckte jag en bugg i metoden `addTransaction()`.

Jag skrev ett JUnit-test för att kontrollera att programmet inte accepterade Double.NaN som belopp. Testet blev rött eftersom inget undantag kastades.

För att undersöka felet använde jag IntelliJ Debugger och satte en breakpoint vid kontrollen av beloppet i BudgetManager.java.

Jag upptäckte att villkoret transaction.amount() <= 0 inte fångade NaN, eftersom jämförelsen returnerade false.

Jag rättade felet genom att lägga till Double.isNaN() i villkoret.

Efter ändringen körde jag testet igen och det blev grönt. Jag körde även de övriga testerna för att kontrollera att allt fortfarande fungerade.

## Loggning

Jag har använt Javas inbyggda `Logger` för att skriva varningsmeddelanden när användaren försöker lägga till ett ogiltigt belopp.

Loggningen hjälper till att upptäcka och undersöka fel i programmet.
