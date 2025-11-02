package problems.RentARide;

public enum CarModel {
    sedan,
    hatchback,
    five_seater,
    suv,
    unknown;

    public static CarModel from(String s) {
        if (s == null) return unknown;
        return switch (s.trim().toLowerCase()) {
            case "sedan" -> sedan;
            case "hatchback" -> hatchback;
            case "5 seater", "5seater", "five seater", "five_seater" -> five_seater;
            case "suv" -> suv;
            default -> unknown;
        };
    }
}