import static java.text.MessageFormat.format;

public void main() {
    Object navn = "Kåre Kanon";
    Object antall = 3;
    double saldo = 599.99;
    LocalDate bestillingsDato = LocalDate.now();
    List<String> varer = List.of("Bærbar PC", "Hodetelefoner", "Tastatur");

    String melding1 = format("Hei, {0}! Du har {1} nye {1,choice,0#meldinger|1#melding|1<meldinger}.",
            navn, antall);
    println("MessageFormat: " + melding1);

    StringBuilder sb = new StringBuilder();
    sb.append("Hei, ").append(navn).append("!\n");
    int ordreNr = 12345;
    sb.append("Ordre #").append(ordreNr)
            .append(" plassert ")
            .append(bestillingsDato.format(DateTimeFormatter.ISO_LOCAL_DATE))
            .append(" er sendt.\n");

    StringJoiner vareKobler = new StringJoiner(", ", "Varer: ", "");
    varer.forEach(vareKobler::add);
    sb.append(vareKobler);

    melding1 = sb.toString();
    println("\nStringBuilder + StringJoiner: \n" + melding1);


    String melding2 = String.format("Hei, %s! Din saldo er %.2f kr", navn, saldo);
    println("\nString.format: " + melding2);

    String melding3 = "Hei, %s! Din saldo er %.2f kr".formatted(navn, saldo);
    println("\nString.formatted: " + melding3);

    String html = """
            <html>
                <body>
                    <h1>Velkommen, %s!</h1>
                    <p>Din kontosaldo: <strong>%.2f kr</strong></p>
                    <p>Du har %d vare(r) i handlekurven din.</p>
                </body>
            </html>
            """.formatted(navn, saldo, varer.size());
    println("\nText Block med formatering: \n" + html);
}