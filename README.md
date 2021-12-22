Trochę z rozpędu postawiłem aplikacje na mavenie (kojarzę że chyba pracujecie na gradle) intellij ma wbudowanego mavena także nie 
powinno być problemu z odpaleniem. Jak coś to daj znać.
Aplikacja startuje standardowo na porcie 8080. Po starcie dostępny jest podgląd bazy pod adresem http://localhost:8080/h2-console/,
oraz swagger ui pod adresem http://localhost:8080/swagger-ui/#/

Odnośnie rzeczy które uprościłem/pominąłem/dodałbym gdybym miał nad aplikacją popracować dłużej to:
1. Baza danych - jako że nie było wymagań co do bazy wybrałem h2 tak żeby była jedynie możliwość sprawdzenia
i testowania działania aplikacji. Na docelowej bazie rozważyłbym już na pewno indeksy na poszczególnych tabelach 
i wykorzystanie np. Liquibase do kontroli schemy.

2. Encje - jako że mamy do czynienia z dosyć wrażliwymi danymi pewnie dodałbym tabele audycyjną żeby śledzić przebieg zmian.
W miarę rozbudowy appki pewnie pojawiła by się potrzeba wykorzystania bardziej zaawansowanego narzędzia do rozbudowanych 
zapytań/filtrowania - ostatnio wykorzystywałem w tym celu QueryDsl. 
LoanProperties też się prosi o skonfigurowanie i założenie na nim cache'a.

3. Konfiguracja - wykorzystałem na maxa wbudowane w spring boot'a funkcjonalności ale docelowo na pewno trzeba by się przyjrzeć:
- serializacji/deserializacji json'ów
- konfiguracji bazy / transakcji / puli połączeń
- wpięciem jakiegoś monitoringu
- Internatiolization jeżeli zachodziłaby taka potrzeba

4. Logowanie i Wyjątki - tutaj poszedłem raczej po najmnieszej linii oporu na pewno logów powinno być więcej. 
Dodałbym też jakiś kontekst dla logów, docelowo byłoby to pewnie jakieś userId itp. Odnośnie wyjątków to wprowadziłbym 
dodatkowe parametry jak timestamp i typ (enum) żeby można było je łatwieć testować.

5. Testy - testów oczywiście też mogłoby być więcej, postawiłbym osobną konfigurację bazy pod testy.

Chyba tyle z rzeczy co w trakcie pisania przyszły mi do głowy :)