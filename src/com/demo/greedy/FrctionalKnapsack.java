package com.demo.greedy;

class Item {
	int weight;
	int value;
	double ratio;

	public Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
		this.ratio = (double) value / weight;
	}
}

class FractionalKnapsack {

	public static double getMaxValue(int W, Item[] items) {
		for (int i = 0; i < items.length - 1; i++) {
			for (int j = 0; j < items.length - i - 1; j++) {
				if (items[j].ratio < items[j + 1].ratio) {
					Item temp = items[j];
					items[j] = items[j + 1];
					items[j + 1] = temp;
				}
			}
		}

		int currentWeight = 0;
		double totalValue = 0.0;

		for (Item item : items) {
			if (currentWeight + item.weight <= W) {
				currentWeight += item.weight;
				totalValue += item.value;
			} else {
				int remain = W - currentWeight;
				totalValue += item.value * ((double) remain / item.weight);
				break;
			}
		}
		return totalValue;
	}

	public static void main(String[] args) {
		Item[] items = { new Item(10, 60), new Item(20, 100), new Item(30, 120) };
		int W = 50;
		double maxValue = getMaxValue(W, items);
		System.out.println("Maximum value in knapsack = " + maxValue);
	}
}
