public class Today {
    public static void main(String[] args) {
        EventManager manager = EventManager.getInstance();

        EventProvider csvProvider1 = new CSVEventProvider("events1.csv");
        EventProvider csvProvider2 = new CSVEventProvider("events2.csv");

        System.out.println("Adding CSV Provider 1: " + manager.addEventProvider(csvProvider1));
        System.out.println("Adding CSV Provider 2: " + manager.addEventProvider(csvProvider2));
        System.out.println("Adding CSV Provider 1 again (should fail): " + manager.addEventProvider(csvProvider1));

        System.out.println("Event Provider Count: " + manager.getEventProviderCount());
        System.out.println("Identifiers: " + manager.getEventProviderIdentifiers());

        System.out.println("Removing CSV Provider 1: " + manager.removeEventProvider(csvProvider1.getIdentifier()));
        System.out.println("Removing CSV Provider 1 again (should fail): " + manager.removeEventProvider(csvProvider1.getIdentifier()));

        System.out.println("Event Provider Count after removal: " + manager.getEventProviderCount());

        System.out.println("Events from Provider 2: " + csvProvider2.getEvents());
    }
}
