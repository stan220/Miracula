package com.miracula.main;

public class Unit {

	// Базовые параметры
	private int strength = 0;
	private int agility = 0;
	private int intellegence = 0;
	
	private int base_str = 0;
	private int base_agi = 0;
	private int base_int = 0;
	
	private int add_str = 0;
	private int add_agi = 0;
	private int add_int = 0;
	
	//
	private int life = 1;
	private int life_max = 1;
	
	// min=1 max=60
	private int level = 1;
	
	public Unit()
	{
		
	}
	
	private void MathState()
	{
		life_max = (level-1) * 25 + 50;
		//1 - 50hp, 11 - 300hp
				
		base_str = 2*level + 5;
		base_agi = 2*level + 5;
		base_int = 2*level + 5;
		
		strength = base_str + add_str;
		agility = base_agi + add_agi;
		intellegence = base_int + add_int;
		
	}
	
	
}
