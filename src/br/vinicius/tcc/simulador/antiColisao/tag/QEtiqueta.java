package br.vinicius.tcc.simulador.antiColisao.tag;

public class QEtiqueta extends Etiqueta{
	private String sn = "";
	
	public QEtiqueta (int initRound){
		for (int x = 0; x < 4; x++){
			hexCode = "";
			for (int y = 0; y < 4; y++){
				hexCode += (int)(Math.random() * (2));
				sn += tagSNCreator(hexCode);
 			}
		}
		
		setSerialNumber(sn);
		setContador((int)(Math.random()*(initRound)));
	}
}