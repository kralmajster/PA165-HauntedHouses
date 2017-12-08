package fi.muni.pa165.hauntedhouses;

public abstract class ApiContract {
    public static final String REST = "/rest";

    public static final class Ability {
        public static final String BASE = "/ability";
        public static final String ID = "/{id}";
        public static final String PATH_ID = "id";
    }

    public static final class Ghost {
        public static final String BASE = "/ghost";
        public static final String ID = "/{id}";
        public static final String PATH_ID = "id";
    }

    public static final class House {
        public static final String BASE = "/house";
    }

    public static final class Person {
        public static final String BASE = "/person";
        public static final String ID = "/{id}";
    }

}