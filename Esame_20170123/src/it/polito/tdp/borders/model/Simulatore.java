package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Simulatore {
	
	//parametri di simulazione
	private int INIZIALI = 1000;
	private double PERC_STANZIALI = 0.5;
	
	//informazioni che descrivono lo stato del sistema
	private Map<Country,Integer> stanziali; //numero di persone stanziali nello stato, per ciascuna nazione,quanti stanziali?
	private UndirectedGraph<Country,DefaultEdge> graph;
	//misure in uscita
	private int passi;
	
	//coda degli eventi
	private PriorityQueue<Evento> coda;
	
	public Simulatore(UndirectedGraph<Country,DefaultEdge> graph){
		this.graph = graph;
		this.stanziali = new HashMap<Country,Integer>();
		for(Country c : graph.vertexSet()){
			this.stanziali.put(c, 0);
		}
		this.coda = new PriorityQueue<Evento>();		
	}
	
	
	public void inserisci(Country c){
		Evento e = new Evento(INIZIALI,c,1);
		coda.add(e);
	}
	
	
	public void run(){
		this.passi = 0;
		while(!coda.isEmpty()){
			Evento e = coda.poll();
			//this.passi++; cos� calcolo eventi simulati
			this.passi = e.getT(); //invece il testo mi chiede il T max
			//stanziali
			int stanz = (int) (e.getNum()*PERC_STANZIALI);
			
			//nomadi
			int confinanti = Graphs.neighborListOf(graph, e.getCountry()).size();
			int nomadi = (e.getNum()-stanz)/confinanti;
			stanz = e.getNum()-nomadi*confinanti;
			
			//aggiornare il modello del mondo
			//contabilizzare stanziali
			stanziali.put(e.getCountry(), stanziali.get(e.getCountry())+stanz);
			
			if(nomadi>0){
			//schedulare eventi futuri 
			//inserire la destinazione dei nomadi
			for(Country c : Graphs.neighborListOf(graph, e.getCountry())){
				Evento e2 = new Evento(nomadi,c,e.getT()+1);
				coda.add(e2);
			}
			}
			
		}
	}
	
	public int getPassi(){
		return passi;
	}
	
	public List<CountryAndNum> getPresenti(){
		List<CountryAndNum> lista = new ArrayList<CountryAndNum>();
		for(Country c : stanziali.keySet()){
			if(stanziali.get(c)>0){
				lista.add(new CountryAndNum(c,stanziali.get(c)));
			}
		}
		Collections.sort(lista);
		return lista;
		
	}

}
