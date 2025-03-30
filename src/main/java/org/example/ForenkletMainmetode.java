// Definert som statisk konstant for bedre organisering
private static final Function<String, String> hilsen = navn ->
        "Hei, " + Optional.ofNullable(navn).orElse("ukjent") + "!";

        void main() {
            demoFunksjonelProgrammering();
            demoTextBlocks();
            demoOptional();
            demoStreamAPI();
        }

        private static void demoFunksjonelProgrammering() {
            println("\n=== Funksjonell Programmering ===");

            // Bruk av Function-interface
            println("Hilsen: " + hilsen.apply("Java 24"));

            // Demo av mer avansert Function-bruk med compose og andThen
            Function<String, String> tilStorBokstav = String::toUpperCase;
            Function<String, String> leggTilUtropstegn = s -> s + "!";

            // Komponering av funksjoner
            var kombinertFunksjon = hilsen.andThen(tilStorBokstav);
            println("Kombinert: " + kombinertFunksjon.apply("verden"));
        }

        private static void demoTextBlocks() {
            println("\n=== Text Blocks (JSON) ===");

            // Text block med JSON - enklere formatering
            String json = """
                    {
                        "name": "Ola Nordmann",
                        "age": 30,
                        "address": {
                            "street": "Storgata 1",
                            "city": "Oslo"
                        },
                        "hobbies": ["ski", "fjelltur", "programmering"]
                    }
                    """;
            println(json);

            // Demonstrerer hvordan man kan bruke String-formatering i text blocks
            String formatertJson = """
                    {
                        "name": "%s",
                        "age": %d
                    }
                    """.formatted("Kari Nordmann", 28);
            println("\nFormatert JSON: " + formatertJson);
        }

        private static void demoOptional() {
            println("\n=== Optional API ===");

            // Diverse måter å jobbe med Optional på
            Optional<String> tomOptional = Optional.empty();
            println("Tom optional: " +
                    tomOptional.map(n -> "Hei, " + n).orElse("Ukjent navn"));

            // Bedre håndtering med ifPresentOrElse
            Optional<String> navnOptional = Optional.of("Ola");
            navnOptional.ifPresentOrElse(
                    navn -> println("Fant navn: " + navn),
                    () -> println("Ingen navn funnet")
            );

            // Demo av filter og flatMap
            Optional<String> resultat = Optional.of("Java 24")
                    .filter(s -> s.contains("Java"))
                    .map(String::toLowerCase)
                    .flatMap(s -> Optional.of(s + " er fantastisk"));

            println("Resultat: " + resultat.orElse("Ingen match"));
        }

        private static void demoStreamAPI() {
            println("\n=== Stream API ===");

            // Demonstrer bruk av streams med en liste av navn
            List<String> navn = List.of("Ole", "Kari", "Per", "Lise", "Jan");

            // Filtrer og transformer navn
            String resultat = navn.stream()
                    .filter(n -> n.length() > 3)
                    .map(String::toUpperCase)
                    .sorted()
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Ingen navn funnet");

            System.out.println("Filtrerte og transformerte navn: " + resultat);

            // Demonstrer map-operasjoner med records
            record Person(String navn, int alder) {
            }

            Map<String, Integer> personMap = Map.of(
                    "Ole", 25,
                    "Kari", 30,
                    "Per", 22
            );

            // Transformer map til liste av Person-records
            List<Person> personer = personMap.entrySet().stream()
                    .map(entry -> new Person(entry.getKey(), entry.getValue()))
                    .toList();

            println("\nPersoner fra map:");
            personer.forEach(System.out::println);
        }
