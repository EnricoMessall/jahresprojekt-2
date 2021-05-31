package de.hhbk.jahresprojekt.help;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.User;

import java.util.List;

public class ReadMe {

    //todo Datenbank
        /*
            Das Programm benötigt eine mariadb Datenbank.
                - Datenbankname: immo
                - User: root
                - Password: xwrqavrtyqrhgsre

            Wenn ihr Docker installiert habt geht ihr einfach mit der commandline in den Git-Root Ordner (jahresprojekt/)
            und führt 'docker-compose up' aus.
            Dann mach docker den Rest und hostet die Datenbank, bis ihr den Vorgang mit Ctrl-C beendet.
            (Solltet ihr die Datenbank neu aufsetzten wollen ist der command 'docker rm database'

            Wenn ihr Docker nicht installieren wollte müsst ihr die Datenbank selber hosten. Mit XAMPP könnte es u.U.
            Probleme geben, weil wir den MariaDB Dialect nutzen sollen.
         */

    //todo Model
        /*
            Um auf Daten zugreifen zu können gibt es das sog. Repository.
            Der Datenaccess erfolgt so:
         */
        void readme(){
            //User Repository anlegen
            Repository<User> userRepository = new Repository<>(User.class);

            //User oder Userlist holen
            User user = userRepository.findById(0);
            List<User> userList = userRepository.findAll();

            //User updaten oder erstellen
            user.setPassword("megasicher");
            userRepository.save(user);

            //User löschen
            userRepository.delete(user);
        }
}
