package a2;



import net.java.games.input.*;

public class FindComponents {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void listControllers()
	{
		System.out.println("JInput version: " + Version.getVersion());
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
		
		Controller[] cs = ce.getControllers();
		
		for (int i =0; i <cs.length; i++)
		{
			System.out.println("\nController #" + i);
			listComponents(cs[i]);
		}
	}
	private void listComponents(Controller ctr)
	{
		System.out.println("Name: " + ctr.getName() + ". Type ID:" + ctr.getType());
		
		Component [] comps = ctr.getComponents();
		for (int i =0; i < comps.length; i++)
		{
			System.out.println(" name: " + comps[i].getName() + " ID: " + comps[i].getIdentifier());
		}
		
		// subcontrollers
		
		Controller[] subCtr = ctr.getControllers();
		
		for (int a = 0; a < subCtr.length; a++)
		{
			System.out.println(" " + ctr.getName() + "sub controller # " + a);
			listComponents(subCtr[a]);
		}
	}

}
