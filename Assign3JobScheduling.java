class Job {
    char id;      // Job ID
    int deadline; // Deadline
    int profit;   // Profit

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class Assign3JobScheduling {

    public static void jobSchedule(Job[] jobs) {
        int n = jobs.length;

        // Sort jobs in descending order of profit
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (jobs[i].profit < jobs[j].profit) {
                    Job temp = jobs[i];
                    jobs[i] = jobs[j];
                    jobs[j] = temp;
                }
            }
        }

        // Find max deadline
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            if (jobs[i].deadline > maxDeadline)
                maxDeadline = jobs[i].deadline;
        }

        // Create a result slot for each time slot
        char[] result = new char[maxDeadline];
        boolean[] slot = new boolean[maxDeadline];


        int totalProfit = 0;

        // Assign jobs to slots greedily
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(maxDeadline - 1, jobs[i].deadline - 1); j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = jobs[i].id;
                    slot[j] = true;
                    totalProfit += jobs[i].profit;
                    break;
                }
            }
        }

        System.out.print("Scheduled Jobs: ");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i])
                System.out.print(result[i] + " ");
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job('A', 2, 100),
            new Job('B', 1, 19),
            new Job('C', 2, 27),
            new Job('D', 1, 25),
            new Job('E', 3, 15)
        };

        jobSchedule(jobs);
    }
}
