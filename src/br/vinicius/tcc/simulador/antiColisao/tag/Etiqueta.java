package br.vinicius.tcc.simulador.antiColisao.tag;

public class Etiqueta {
	protected String serialNumber;
	protected Integer contador;
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}
	
	public String tagSNCreator (String hexCode) {
		String hexValue = "";
		if (hexCode.equals("0000")) {
			hexValue = "0";
		} else if (hexCode.equals("0001")) {
			hexValue = "1";
		} else if (hexCode.equals("0010")) {
			hexValue = "2";
		} else if (hexCode.equals("0011")) {
			hexValue = "3";
		} else if (hexCode.equals("0100")) {
			hexValue = "4";
		} else if (hexCode.equals("0101")) {
			hexValue = "5";
		} else if (hexCode.equals("0110")) {
			hexValue = "6";
		} else if (hexCode.equals("0111")) {
			hexValue = "7";
		} else if (hexCode.equals("1000")) {
			hexValue = "8";
		} else if (hexCode.equals("1001")) {
			hexValue = "9";
		} else if (hexCode.equals("1010")) {
			hexValue = "A";
		} else if (hexCode.equals("1011")) {
			hexValue = "B";
		} else if (hexCode.equals("1100")) {
			hexValue = "C";
		} else if (hexCode.equals("1101")) {
			hexValue = "D";
		} else if (hexCode.equals("1110")) {
			hexValue = "E";
		} else if (hexCode.equals("1111")) {
			hexValue = "F";
		}
		return hexValue;
	}
	
}