package com.connor;

public class SortTest {

	public static void main(String[] args) {

		int[] a = { 6, 1, 2, 3, 9, 7, 4, 4, 5, 10, 8 };

		// insertSort(a);
		quickSort(a, 0, a.length - 1);

		System.out.println(a);
		A aa = new A(); // 实例化了一个父类对象
		B b = (B) aa; // 发生了向下转型关系
		b.fun1();
		b.fun2();
		b.fun3();
	}

	

	public static void quickSort(int[] array, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int index = partition(array, lo, hi);
		quickSort(array, lo, index - 1);
		quickSort(array, index + 1, hi);
	}

	/**
	 * 快速排序的切分
	 * 
	 * @param array
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static int partition(int[] array, int lo, int hi) {
		// 固定的切分方式
		int key = array[lo];
		int index = lo;
		while (hi > lo) {
			while (array[hi] >= key && hi > lo) {// 从后半部分向前扫描
				hi--;
			}
			while (array[lo] <= key && hi > lo) {// 从前半部分向后扫描
				lo++;
			}

			if (hi > lo) {
				int temp = array[lo];
				array[lo] = array[hi];
				array[hi] = temp;
			}
		}
		array[index] = array[lo];
		array[lo] = key;
		return hi;
	}

	/**
	 * 插入排序
	 * 
	 * @param a
	 */
	public static void insertSort(int[] a) {
		int len = a.length;// 单独把数组长度拿出来，提高效率
		int insertNum;// 要插入的数
		for (int i = 0; i < len; i++) {// 因为第一次不用，所以从1开始
			insertNum = a[i];
			int j = i - 1;// 序列元素个数
			while (j > 0 && a[j] > insertNum) {// 从后往前循环，将大于insertNum的数向后移动
				a[j + 1] = a[j];// 元素向后移动
				j--;
			}
			a[j + 1] = insertNum;// 找到位置，插入当前元素
		}
	}
}

class A { // 定义类A
	public void fun1() { // 定义fun1()方法
	}

	public void fun2() {
	}
}

class B extends A {
	public void fun1() { // 此方法被子类覆写了
	}

	public void fun3() {
	}
}
