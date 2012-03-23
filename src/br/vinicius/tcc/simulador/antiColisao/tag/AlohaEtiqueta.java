package br.vinicius.tcc.simulador.antiColisao.tag;

public class AlohaEtiqueta extends Etiqueta{
	
	public AlohaEtiqueta (int initRound){
		String hexCode;
		serialNumber = "E0";
		for (int x = 0; x < 2; x++){
			hexCode = "";
			for (int y = 0; y<4; y++){
				hexCode += (int)(Math.random() * (2));
				serialNumber += tagSNCreator(hexCode);
			}
		}
		serialNumber += "0000";
		for (int x = 0; x < 8; x++){
			hexCode = "";
			for (int y = 0; y < 4; y++){
				hexCode = hexCode + (int)(Math.random() * (2));
				serialNumber += tagSNCreator(hexCode);
			}
		}
		
		contador = (1 + (int)(Math.random() * (initRound)));
	}
}