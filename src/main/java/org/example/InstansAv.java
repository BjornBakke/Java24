

public void main() {
    println("=== Basic Pattern Matching ===");

    // Bruk av seed for reproduserbarhet i demo
    Random random = new Random(42);

    // Generer en tilfeldig objekttype
    Object obj = getTilfeldigObjekt(random);
    println("Objektet er: " + obj);

    // Pattern matching med instanceof
    handleObject(obj);

    // Demonstrer pattern matching med en liste av objekter
    println("\n=== Pattern Matching på Liste ===");

    // Bruk List.of() med immutable objekter som ikke er null
    List<Object> objektListe = List.of(
            "Hei verden",
            42,
            3.14,
            Map.of("navn", "Ole", "alder", 30),
            List.of(1, 2, 3),
            Optional.empty()  // Bruk Optional.empty() i stedet for null
    );

    // Gå gjennom og håndter hvert objekt med lambda
    objektListe.forEach(item -> handleObject(item));

    // Demonstrer avansert pattern matching med switch
    println("\n=== Avansert Pattern Matching med Switch ===");
    objektListe.forEach(item -> handleObjectWithSwitch(item));

    // Demonstrer pattern matching med Optional
    println("\n=== Pattern Matching med Optional ===");

    // Lag en liste med forskjellige Optional-verdier
    List<Optional<?>> optionalListe = List.of(
            Optional.of("Java 24"),
            Optional.of(100),
            Optional.empty(),
            Optional.of(Map.of("nøkkel", "verdi"))
    );

    // Demonstrer pattern matching med Optional
    optionalListe.forEach(this::handleOptionalImproved);
}

/**
 * Returnerer et tilfeldig objekt av forskjellige typer
 */
private Object getTilfeldigObjekt(Random random) {
    int valg = random.nextInt(6);
    return switch (valg) {
        case 0 -> "Hei verden";
        case 1 -> 42;
        case 2 -> 3.14;
        case 3 -> List.of("en", "to", "tre");
        case 4 -> Map.of("nøkkel", "verdi");
        case 5 -> Optional.empty();  // Returnerer Optional.empty i stedet for null
        default -> Optional.empty();
    };
}

/**
 * Håndterer et objekt med pattern matching
 */
private void handleObject(Object obj) {
    // Moderne pattern matching med instanceof
    if (obj instanceof String str) {
        println("String: \"" + str + "\" med lengde: " + str.length());
    } else if (obj instanceof Integer tall) {
        println("Integer: " + tall + " (kvadrat: " + (tall * tall) + ")");
    } else if (obj instanceof Double d) {
        println("Double: " + d + " (avrundet: " + Math.round(d) + ")");
    } else if (obj instanceof List<?> liste) {
        println("Liste med " + liste.size() + " elementer: " + liste);
    } else if (obj instanceof Map<?, ?> map) {
        println("Map med nøkler: " + map.keySet());
    } else if (obj instanceof Optional<?> opt) {
        if (opt.isPresent()) {
            println("Optional med verdi: " + opt.get());
        } else {
            println("Optional.empty()");
        }
    } else if (obj == null) {
        println("Objektet er null (dette bør ikke skje med Optional)");
    } else {
        println("Ukjent objekttype: " + obj.getClass().getSimpleName());
    }
}

/**
 * Håndterer et objekt med pattern matching i switch expression
 */
private void handleObjectWithSwitch(Object obj) {
    // Moderne switch expression med pattern matching
    String resultat = switch (obj) {
        case String str -> "String: \"" + str + "\" med lengde: " + str.length();
        case Integer i when i > 0 -> "Positivt tall: " + i;
        case Integer i -> "Ikke-positivt tall: " + i;
        case Double d -> "Double: " + d;
        case List<?> l -> "Liste med " + l.size() + " elementer";
        case Map<?, ?> m -> "Map med " + m.size() + " nøkler";
        case Optional<?> opt when opt.isPresent() -> "Optional med verdi: " + opt.get();
        case Optional<?> opt -> "Optional.empty()";
        case null -> "Objektet er null (dette bør ikke skje med Optional)";
        default -> "Ukjent objekttype: " + obj.getClass().getSimpleName();
    };

    println(resultat);
}

/**
 * Forbedret måte å håndtere Optional på uten problematiske typekastinger
 */
private void handleOptionalImproved(Optional<?> opt) {
    if (opt.isEmpty()) {
        println("Tom Optional");
        return;
    }

    // Få tak i verdien og sjekk typen på den, ikke på Optional<?>
    Object value = opt.get();

    if (value instanceof String str) {
        println("Optional<String>: " + str);
    } else if (value instanceof Integer i) {
        println("Optional<Integer>: " + i);
    } else if (value instanceof Map<?, ?> map) {
        println("Optional<Map> med nøkler: " + map.keySet());
    } else {
        println("Optional med annen type: " + value.getClass().getSimpleName());
    }

    // Bruk Optional's innebygde metoder
    opt.ifPresent(v -> println("   Verdi funnet: " + v));
}
