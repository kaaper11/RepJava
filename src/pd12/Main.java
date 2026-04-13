package pd12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<UserAccountV1> userAccounts = new HashSet<>();
        UserAccountV1 user1 = new UserAccountV1(1L, "user1", "user1");
        UserAccountV1 user2 = new UserAccountV1(1L, "user1", "user1");
        System.out.println(user1.equals(user2));

        userAccounts.add(user1);
        userAccounts.add(user2);
        System.out.println(userAccounts.size());

        //Eksperyment A
        // equals() nie został nadpisany, więc używana jest implementacja z klasy Object,
        // która porównuje referencje (czyli sprawdza, czy to dokładnie ten sam obiekt w pamięci).
        // Mimo że obiekty mają takie same dane, są różnymi instancjami, więc equals() zwraca false.
        // HashSet korzysta zarówno z hashCode(), jak i equals().
        // Ponieważ hashCode() też nie jest nadpisany, każdy obiekt ma inny hash i są traktowane jako różne elementy.
        // Dlatego rozmiar seta wynosi 2.

        Set<UserAccountV2> userAccountsV2 = new HashSet<>();
        UserAccountV2 user3 = new UserAccountV2(1L, "user1", "user1");
        UserAccountV2 user4 = new UserAccountV2(1L, "user1", "user1");
        userAccountsV2.add(user3);
        userAccountsV2.add(user4);
        System.out.println(userAccountsV2.size());
        System.out.println(userAccountsV2.contains(new UserAccountV2(1L, "user1", "user1")));

        //Eksperyment B
        // Ponieważ hashCode() też nie jest nadpisany, każdy obiekt ma inny hash i są traktowane jako różne elementy.
        // Dlatego rozmiar seta wynosi 2 oraz metoda contains(), która sparwdza czy dany element znajduje sie w secie
        // również traktuje obiekt porównywalny jako element unikalny.


        Set<UserAccountV3> userAccountsV3 = new HashSet<>();
        UserAccountV3 user5 = new UserAccountV3(1L, "user1", "user1");
        UserAccountV3 user6 = new UserAccountV3(1L, "user1", "user1");
        userAccountsV3.add(user5);
        userAccountsV3.add(user6);
        System.out.println(userAccountsV3.contains(new UserAccountV3(1L, "user1", "user1")));

        HashMap<UserAccountV3, Integer> accountV3HashMap = new HashMap<>();
        accountV3HashMap.put(user5, 1);
        System.out.println(accountV3HashMap.get(user6));
        System.out.println(accountV3HashMap.containsKey(user6));

        // equals() został nadpisany tak, aby porównywać zawartość obiektów, a nie ich referencje.
        // hashCode() również został nadpisany i jest zgodny z equals(),
        // czyli dwa obiekty uznane za równe mają ten sam hashCode.
        // Dzięki temu HashSet rozpoznaje, że oba obiekty są sobie równe
        // i nie dodaje duplikatu dlatego rozmiar wynosi 1.
        // HashMap używa hashCode() do znalezienia odpowiedniego hashu,
        // a następnie equals() do porównania kluczy.
        // Dlatego możliwe jest pobranie wartości za pomocą innego obiektu,
        // który ma te same dane, jak ten zanjdujący się w mapie.


        System.out.println();
        HashSet<UserAccountV4> userAccountsV4 = new HashSet<>();
        UserAccountV4 userAccountV4 = new UserAccountV4(1L, "user", "user", "user");
        userAccountsV4.add(userAccountV4);
        System.out.println(userAccountsV4.contains(userAccountV4));
        userAccountV4.setStatus("user1");
        System.out.println(userAccountsV4.contains(userAccountV4));
        System.out.println(userAccountsV4.remove(userAccountV4));

        // Eksperyment D
        // Po zmianie statusu zmienił sie hash obiektu, set szuka elemntu na podstaiwe obecnego hasha, który jest inny
        // niż ten pierwotny. Więc kolekcja nie może znależć obiektu mimo, że nadal się w niej znajduje. Wniosek z tego taki,
        // że pola do hashu powinny być final.
    }
}
