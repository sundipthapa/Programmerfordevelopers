package Question5;

class Solutionb {
    public int batteryReplacement(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        // Initialize the number of battery replacements to 0
        int replacements = 0;
        // Initialize the remaining miles the car can travel to the starting charge capacity
        int remainingMiles = startChargeCapacity;
        // Loop through all service centers
        for (int i = 0; i < serviceCenters.length; i++) {
            // Calculate the miles to reach the current service center
            int milesToService = (i > 0) ? serviceCenters[i][0] - serviceCenters[i - 1][0] : serviceCenters[i][0];
            // If the remaining miles are not enough to reach the current service center
            if (remainingMiles < milesToService) {
                // Increment the number of battery replacements
                replacements++;
                // Update the remaining miles to the capacity of the new battery
                remainingMiles = serviceCenters[i][1];
            }
            // Update the remaining miles with the miles traveled
            remainingMiles -= milesToService;
            // If the remaining miles are enough to reach the destination
            if (remainingMiles >= targetMiles - serviceCenters[i][0]) {
                // Return the number of battery replacements
                return replacements;
            }
        }
        // Return the number of battery replacements
        return replacements;
    }
    public static void main(String[] args) {
        int[][] serviceCenters = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        int targetMiles = 100;
        int startChargeCapacity = 10;
        Solutionb solution = new Solutionb();
        int result = solution.batteryReplacement(serviceCenters, targetMiles, startChargeCapacity);
        System.out.println("Result: " + result);
    }

}
