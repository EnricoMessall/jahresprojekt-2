package de.hhbk.jahresprojekt.help;

import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.UserRepository;
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
        /**
            Um auf Daten zugreifen zu können gibt es das sog. Repository.
            Repositories müssen zuerst über den RepositoryContainer registriert werden.
            Danach kann das Repository jederzeit über {@link RepositoryContainer#get(Class)} geholt werden
            Der Datenaccess erfolgt so:
         **/
        void readme() throws Exception {
            //User Repository anlegen
            RepositoryContainer.registerRepository(UserRepository.class);
            UserRepository userRepository = RepositoryContainer.get(UserRepository.class);

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
