import java.util.Scanner;

/**
 * Mechanical Engineering Calculator
 * A beginner-friendly Java project for mechanical students.
 *
 * Features:
 *  1. Bending stress in a simply supported beam (rectangular section, central point load)
 *  2. Gear ratio and output speed
 *  3. Shaft power / torque conversion
 *  4. Heat conduction through a flat wall (Fourier's law)
 *  5. Factor of safety
 *
 * All inputs use SI units.
 */
public class MechCalculator {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   MECHANICAL ENGINEERING CALCULATOR");
        System.out.println("=====================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    beamBendingStress();
                    break;
                case 2:
                    gearRatio();
                    break;
                case 3:
                    powerTorque();
                    break;
                case 4:
                    heatConduction();
                    break;
                case 5:
                    factorOfSafety();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please pick a number from the menu.");
            }
        }
        input.close();
    }

    // ---------- Menu ----------

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Beam bending stress (simply supported, central load)");
        System.out.println("2. Gear ratio and output speed");
        System.out.println("3. Shaft power <-> torque");
        System.out.println("4. Heat conduction through a flat wall");
        System.out.println("5. Factor of safety");
        System.out.println("0. Exit");
    }

    // ---------- Calculations ----------

    /** Max bending stress = M*y/I, where M = P*L/4 for a central point load. */
    private static void beamBendingStress() {
        System.out.println("\n--- Beam Bending Stress ---");
        double load = readPositive("Central load P (N): ");
        double length = readPositive("Span L (m): ");
        double width = readPositive("Section width b (m): ");
        double depth = readPositive("Section depth h (m): ");

        double moment = load * length / 4.0;                    // N.m
        double inertia = width * Math.pow(depth, 3) / 12.0;     // m^4
        double stress = moment * (depth / 2.0) / inertia;       // Pa

        System.out.printf("Max bending moment : %.2f N.m%n", moment);
        System.out.printf("Moment of inertia  : %.6e m^4%n", inertia);
        System.out.printf("Max bending stress : %.2f MPa%n", stress / 1e6);
    }

    /** Gear ratio = driven teeth / driver teeth. */
    private static void gearRatio() {
        System.out.println("\n--- Gear Ratio ---");
        int driver = (int) readPositive("Teeth on driver gear: ");
        int driven = (int) readPositive("Teeth on driven gear: ");
        double inputRpm = readPositive("Input speed (rpm): ");

        double ratio = (double) driven / driver;
        double outputRpm = inputRpm / ratio;

        System.out.printf("Gear ratio   : %.3f : 1%n", ratio);
        System.out.printf("Output speed : %.2f rpm%n", outputRpm);
        System.out.printf("Output torque is %.3f times the input torque (ignoring losses)%n", ratio);
    }

    /** P = 2*pi*N*T / 60 */
    private static void powerTorque() {
        System.out.println("\n--- Shaft Power / Torque ---");
        System.out.println("1. Find power from torque");
        System.out.println("2. Find torque from power");
        int option = readInt("Choose: ");
        double rpm = readPositive("Speed N (rpm): ");

        if (option == 1) {
            double torque = readPositive("Torque T (N.m): ");
            double power = 2 * Math.PI * rpm * torque / 60.0;
            System.out.printf("Power = %.2f W (%.3f kW)%n", power, power / 1000.0);
        } else if (option == 2) {
            double powerKw = readPositive("Power P (kW): ");
            double torque = powerKw * 1000.0 * 60.0 / (2 * Math.PI * rpm);
            System.out.printf("Torque = %.2f N.m%n", torque);
        } else {
            System.out.println("Invalid option.");
        }
    }

    /** Fourier's law: Q = k*A*(T1 - T2)/L */
    private static void heatConduction() {
        System.out.println("\n--- Heat Conduction (flat wall) ---");
        double k = readPositive("Thermal conductivity k (W/m.K): ");
        double area = readPositive("Area A (m^2): ");
        double thickness = readPositive("Thickness L (m): ");
        double hot = readDouble("Hot side temperature (C): ");
        double cold = readDouble("Cold side temperature (C): ");

        double q = k * area * (hot - cold) / thickness;
        System.out.printf("Heat transfer rate Q = %.2f W%n", q);
    }

    /** FoS = material strength / working stress */
    private static void factorOfSafety() {
        System.out.println("\n--- Factor of Safety ---");
        double strength = readPositive("Material strength (MPa): ");
        double working = readPositive("Working stress (MPa): ");

        double fos = strength / working;
        System.out.printf("Factor of safety = %.2f%n", fos);
        if (fos < 1.0) {
            System.out.println("Result: UNSAFE (the part will fail)");
        } else if (fos < 2.0) {
            System.out.println("Result: Marginal (check your design requirements)");
        } else {
            System.out.println("Result: Safe");
        }
    }

    // ---------- Input helpers (they keep asking until the input is valid) ----------

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double readPositive(String prompt) {
        while (true) {
            double value = readDouble(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Value must be greater than zero.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }
}
