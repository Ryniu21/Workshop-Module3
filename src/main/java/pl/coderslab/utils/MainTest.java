package pl.coderslab.entity;

import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) {
        //Test dodawania
        //User user = new User("nazwa uzytkownika", "email", "hasło");
        //User user1 = new User("Krzysiek", "krzysiek@rutkowski.com", "jatoja");
        //User user2 = new User("Damian", "damian@damian.com", "damiantodamian");
        //User user3 = new User("Ilona", "ilona@ilona.com", "ilonatoilona");
        //User user4 = new User("Michał", "michal@michal.com", "michałtomichał");
        //User user5 = new User("Kamil", "kamil@kamil.com", "kamiltokamil");
        //User user6 = new User("Zbyszek", "zbyszek@zbyszek.com", "zbyszektozbyszek");

        //UserDao.create(user);
        //UserDao.create(user1);
        //UserDao.create(user2);
        //UserDao.create(user3);
        //UserDao.create(user4);
        //UserDao.create(user5);
        //UserDao.create(user6);


        //Test usuwania
        //User user1 = new User (7);
        //UserDao.remove(user1);

        //Test sczytywania
        //int ajdi = 5;
        //UserDao readDao = new UserDao();
        //System.out.println(readDao.read(ajdi));

        //Test aktualizowania
        //User user = new User(UserDao.read(ajdi).getId(), "sprawdzona nazwa uzytkownika", "sprawdzony email", "2hasło");
        //UserDao.update(user);
        //System.out.println(readDao.read(ajdi));


        //Test sczytywanie całej tabeli
        System.out.println(Arrays.toString(UserDao.findAll2()));
        UserDao.findAll();



    }
}
