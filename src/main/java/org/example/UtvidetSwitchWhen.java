record Person(String navn, int alder) {
}

public void main() {
    var a = new Person("Per Person", 100);
    var b = new Person("Peter Person", 10);
    println(hentKlassifiering(a));
    println(hentKlassifiering(b));
}


private static String hentKlassifiering(Person person) {
    return switch (person) {
        case Person(var navn, var alder) when alder > 18 -> navn + " er voksen";
        case Person(var navn, _) -> navn + " er ungdom";
        default -> "Ukjent person";
    };
}