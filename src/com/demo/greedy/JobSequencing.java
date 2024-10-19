package com.demo.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Job {

	private String job;
	private int cost;
	private int deadline;

	public Job(String job, int cost, int deadline) {
		this.job = job;
		this.cost = cost;
		this.deadline = deadline;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
}

public class JobSequencing {

	private ArrayList<Job> jobs;

	public JobSequencing() {
		this.jobs = new ArrayList<>();
	}

	public void addJob(String jobName, int cost, int deadline) {
		jobs.add(new Job(jobName, cost, deadline));
	}

	public void sequenceJobs() {
		Collections.sort(jobs, (o1, o2) -> o2.getCost() - o1.getCost());

		int maxDeadline = 0;
		for (Job job : jobs) {
			if (job.getDeadline() > maxDeadline) {
				maxDeadline = job.getDeadline();
			}
		}

		String[] result = new String[maxDeadline];
		boolean[] slotFilled = new boolean[maxDeadline];

		for (int i = 0; i < maxDeadline; i++) {
			slotFilled[i] = false;
		}

		for (Job job : jobs) {
			for (int j = Math.min(maxDeadline, job.getDeadline()) - 1; j >= 0; j--) {
				if (!slotFilled[j]) {
					result[j] = job.getJob();
					slotFilled[j] = true;
					break;
				}
			}
		}

		System.out.println("The job sequence is: ");
		for (int i = 0; i < maxDeadline; i++) {
			if (result[i] != null) {
				System.out.print(result[i] + " ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		JobSequencing jobSequencing = new JobSequencing();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("=============================================================");
			System.out.println("Enter 1 for adding jobs");
			System.out.println("Enter 2 for sequencing");
			System.out.println("Enter 3 for exit");
			System.out.print("Enter your choice: ");
			int choice = Integer.parseInt(in.readLine());
			System.out.println("=============================================================");
			switch (choice) {
			case 1:
				System.out.print("Enter job name: ");
				String jobName = in.readLine();
				System.out.print("Enter job cost: ");
				int cost = Integer.parseInt(in.readLine());
				System.out.print("Enter job deadline: ");
				int deadline = Integer.parseInt(in.readLine());
				jobSequencing.addJob(jobName, cost, deadline);
				break;
			case 2:
				jobSequencing.sequenceJobs();
				break;
			case 3:
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		}
	}
}
