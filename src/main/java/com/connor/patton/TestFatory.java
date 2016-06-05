package com.connor.patton;

/*
 * 简单工厂模式,静态工厂模式(提供一个静态的方法进行调用)
 * 
 *	1. 接口,实现分离
 *  2. 扩展性差, 静态方法不能继承
 *  3. JDK示例:DateFormat
 */
public class TestFatory {

	public static void main(String[] args) {
		//静态工厂方法
		Creature cat = CreatureStaticFatocy.create(CreatureStaticFatocy.CAT);
		cat.buff();
		//多态工厂方法
		Creature dog = new DogCreatureFactory().create();
		dog.buff();
		//抽象工厂方法
		MulFatory mulFatocy = new ChinaMulFactory();
		Creature people = mulFatocy.create();
		people.buff();
	}
}

interface Creature {
	void eat();
	void buff();
}

class Dog implements Creature {
	public void eat() {
	}
	public void buff() {
		System.out.println("汪汪汪");
	}
}

class Cat implements Creature {
	public void eat() {
	}
	public void buff() {
		System.out.println("喵喵喵");
	}
}

class People implements Creature {
	public void eat() {
	}
	public void buff() {
		System.out.println("哈哈哈");
	}
}

/************************************************************************************************************/
/**
 * 静态工厂
 * 
 * @author connor_zeng
 */
class CreatureStaticFatocy {
	public final static int DOG = 1;
	public final static int CAT = 2;
	public final static int PEOPLE = 3;

	/**
	 * 静态方法进行生产
	 * 
	 * @param creature
	 * @return
	 */
	public static Creature create(int creature) {
		Creature result = null;
		switch (creature) {
		case DOG:// 必须是final
			result = new Dog();
			break;
		case CAT:// 必须是final
			result = new Cat();
			break;
		case PEOPLE:// 必须是final
			result = new People();
			break;
		default:
			break;
		}
		return result;
	}
}

/************************************************************************************************************/
/**
 * 工厂方法(多台工厂)
 * @author connor_zeng
 * 
 */
interface CreatureFatory {
	Creature create();
}
class DogCreatureFactory implements CreatureFatory{
	@Override
	public Creature create() {
		return new Dog();
	}
}
/************************************************************************************************************/
interface VegieeFactory{
}
abstract class MulFatory implements CreatureFatory,VegieeFactory{
}
class ChinaMulFactory extends MulFatory{
	@Override
	public Creature create() {
		return new People();
	}
}
/************************************************************************************************************/




