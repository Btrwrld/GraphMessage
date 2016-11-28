package com.ce.datosi.GraphMessage.Mensajes;

import java.text.BreakIterator;

//import com.itcr.estructurasDeDatos.BTree;

public class Mensaje {
	private String IPemisor;
	private String IPreceptor;
	private String mensajeOriginal;
	//private BTree<Integer,String> arbolPalabras = new BTree<Integer,String>();
	
	public Mensaje(){
	}
	
	public String getIPemisor() {
		return IPemisor;
	}
	public String getIPreceptor() {
		return IPreceptor;
	}
	public String getMensajeOriginal() {
		return mensajeOriginal;
	}
	/*public BTree<Integer, String> getArbolPalabras() {
		return arbolPalabras;
	}*/
	public void setIPemisor(String iPemisor) {
		IPemisor = iPemisor;
	}
	public void setIPreceptor(String iPreceptor) {
		IPreceptor = iPreceptor;
	}
	public void setMensajeOriginal(String mensajeOriginal) {
		armarArbol(mensajeOriginal);
		this.mensajeOriginal = mensajeOriginal;
	}
	
	
	private void armarArbol(String text){
		Integer contador = 0; 
		BreakIterator breakIterator = BreakIterator.getWordInstance();
		breakIterator.setText(text);
		int lastIndex = breakIterator.first();
		while (BreakIterator.DONE != lastIndex) {
			contador ++;
			int firstIndex = lastIndex;
			lastIndex = breakIterator.next();
			if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
				//arbolPalabras.put(contador,text.substring(firstIndex, lastIndex));
			}
		}
	}

}
