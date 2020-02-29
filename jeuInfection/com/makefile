CMD = java
COMPILE = javac



compile: 
	@echo "-------------------------------------------------------------------------"
	@echo "compilation des classes fil rouge..."
	@echo "-------------------------------------------------------------------------"
	$(COMPILE) entity/*.java services/*.java test/*.java 
	


start:
	@echo "-------------------------------------------------------------------------"
	@echo "lancement de l'application..."
	@echo "-------------------------------------------------------------------------"
	$(CMD) test.Main
	

	

pipeline: compile start clean  

clean:
	rm -r entity/*.class
	rm -r services/*.class
	rm -r test/*.class

delete:
	rm -rf classes			