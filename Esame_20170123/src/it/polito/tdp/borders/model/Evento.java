package it.polito.tdp.borders.model;

public class Evento implements Comparable<Evento> {
	//c'è solo un tipo di evento, l'ingresso
	private int num; //numero di persone
	private Country country; //in quale stato
	private int t; //in quale istante di tempo
	public Evento(int num, Country country, int t) {
		super();
		this.num = num;
		this.country = country;
		this.t = t;
	}
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	/**
	 * @return the t
	 */
	public int getT() {
		return t;
	}
	/**
	 * @param t the t to set
	 */
	public void setT(int t) {
		this.t = t;
	}
	
	/**
	 * Ordinamento per tempo crescente
	 */
	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.t - o.t;
	}
	
	

}
