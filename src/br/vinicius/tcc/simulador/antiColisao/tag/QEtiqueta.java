package br.vinicius.tcc.simulador.antiColisao.tag;

public class QEtiqueta extends Etiqueta{
	
	public QEtiqueta (int initRound){
		String hexCode;
		serialNumber = "";
		for (int x = 0; x < 4; x++){
			hexCode = "";
			for (int y = 0; y < 4; y++){
				hexCode += (int)(Math.random() * (2));
				serialNumber += tagSNCreator(hexCode);
 			}
		}
		
		contador = ((int)(Math.random()*(initRound)));
	}
}