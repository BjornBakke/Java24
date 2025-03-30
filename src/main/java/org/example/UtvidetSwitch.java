import static java.lang.System.out;

void main() {
    // Test ukedagsmelding
    test("mandag");
    test("fredag");
    test("lørdag");
    test("tirsdag");

    // Test Person record
    out.println("\nPerson-testing:");
    println(getString(new Person("Ole", 25)));
    println(getString(new Person("Kari", 16)));
    println(getString(null));
}

private static void test(String dag) {
    println("For " + dag + ": " + melding.apply(dag));
}

private static final Function<String, String> melding = ukedag -> switch (ukedag) {
    case "mandag" -> "Ny uke!";
    case "fredag" -> "Snart helg!";
    case "lørdag", "søndag" -> "Helg!";
    default -> "Vanlig ukedag: " + ukedag;
};

record Person(String navn, int alder) {
    // Validering i kompakt konstruktør
    Person {
        if (navn == null || navn.isBlank()) {
            throw new IllegalArgumentException("Navn kan ikke være tomt");
        }
        if (alder < 0) {
            throw new IllegalArgumentException("Alder kan ikke være negativ");
        }
    }

    public boolean erMyndig() {
        return alder >= 18;
    }

    @Override
    public String toString() {
        return "%s (%d år)".formatted(navn, alder);
    }
}

private static String getString(Person person) {
    return switch (person) {
        case null -> "Ukjent person";
        case Person p when p.erMyndig() -> p.navn() + " er voksen";
        case Person p -> p.navn() + " er ungdom";
    };
}
