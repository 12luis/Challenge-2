package clips;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import net.sf.clipsrules.jni.*;


public class ClipsAgent extends Agent 
{
  Environment clips;
  protected void setup() 
  {

    System.out.println("Agent " + getLocalName() + " starting");

    clips = new Environment();
    addBehaviour(new TellBehaviour());
    addBehaviour(new AskBehaviour());
  } 

  private class TellBehaviour extends Behaviour 
  {
    boolean isTellBehaviourDone = false; 

    public void action() 
    {
      System.out.println("metodo TellBehaviour comenzo a ejecutarse"); 
        try
        { 
          clips.load("C:/Users/Octavio Lopez/Downloads/clips_jni_640/CLIPSJNI/java/src/load-persons.clp");
          clips.load("C:/Users/Octavio Lopez/Downloads/clips_jni_640/CLIPSJNI/java/src/load-persons-rules.clp");
          clips.reset(); 
          
        }
        catch(Exception e){}
        System.out.println("try de tell");
        isTellBehaviourDone = true;
    } 
    
    public boolean done() 
    {
        if (isTellBehaviourDone) return true;
        return false;
    } 

  }
  
  private class AskBehaviour extends Behaviour 
 {
    
    boolean isAskBehaviourDone = false; 
    public void action() 
    {
      System.out.println("metodo AskBehaviour comenzo a ejecutarse");
        try
        {
          clips.eval("(facts)"); 
          clips.eval("(rules)");
          clips.run();
        }
        catch(Exception e){}
        isAskBehaviourDone = true;
    } 
    
    public boolean done() 
    {
        if (isAskBehaviourDone) return true;
	    return false;
    }
   
    public int onEnd() 
    {
      System.out.println("Agent " + getLocalName() + " Finished");
      myAgent.doDelete();
      return super.onEnd();
    } 
  }
}