package application.controller;

public abstract class RulerDecorator implements Ruler{
	
	Ruler ruler;

	public RulerDecorator(Ruler ruler) {
		this.ruler = ruler;
	}


	

}
